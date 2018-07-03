package com.kienht.cache.features.employee_list;

import com.kienht.cache.PrefUtils;
import com.kienht.cache.database.RoomDB;
import com.kienht.cache.mapper.employee.EmployeeCacheMapper;
import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.repository.employee.EmployeeCache;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Singleton
public class EmployeeCacheImpl implements EmployeeCache {

    public static final String TAG = EmployeeCacheImpl.class.getSimpleName();

    private final long EXPIRATION_TIME = 60 * 10 * 1000;

    @Inject
    RoomDB roomDB;

    @Inject
    EmployeeCacheMapper mapper;

    @Inject
    PrefUtils prefUtils;

    @Inject
    @Named("SchedulerType.COMPUTATION")
    Scheduler schedulerComputation;

    @Inject
    public EmployeeCacheImpl() {
    }

    @Override
    public Completable saveEmployees(List<EmployeeEntity> employees) {
        return Observable.fromIterable(employees)
                .map(employeeEntity -> mapper.mapToCached(employeeEntity))
                .toList()
                .toFlowable()
                .flatMapCompletable(employeeCacheds -> {
                    roomDB.employeeDAO().insert(employeeCacheds);
                    return Completable.complete();
                })
                .subscribeOn(schedulerComputation);
    }

    @Override
    public Flowable<List<EmployeeEntity>> getEmployees() {
        return roomDB.employeeDAO().getEmployees()
                .flatMapPublisher(employeeCacheds -> Flowable.fromIterable(employeeCacheds)
                        .map(employeeCached -> mapper.mapFromCached(employeeCached))
                        .toList()
                        .toFlowable())
                .subscribeOn(schedulerComputation);
    }

    @Override
    public Single<Boolean> isCache() {
        return Single.defer(() -> Single.just(!roomDB.employeeDAO().getEmployees().isEmpty().blockingGet()))
                .subscribeOn(schedulerComputation);
    }

    @Override
    public void setLastCacheTime(long lastCacheTime) {
        prefUtils.set(PrefUtils.PREF_KEY.PREF_KEY_LAST_CACHE, lastCacheTime);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastCacheUpdateTime = prefUtils.get(PrefUtils.PREF_KEY.PREF_KEY_LAST_CACHE, 0L);
        return currentTime - lastCacheUpdateTime > EXPIRATION_TIME;
    }
}
