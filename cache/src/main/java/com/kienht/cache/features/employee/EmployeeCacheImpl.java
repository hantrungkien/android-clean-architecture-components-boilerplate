package com.kienht.cache.features.employee;

import com.kienht.cache.PrefUtils;
import com.kienht.cache.database.RoomDB;
import com.kienht.cache.mapper.employee.EmployeeMapper;
import com.kienht.cache.model.EmployeeCached;
import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.repository.employee.EmployeeCache;

import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeCacheImpl implements EmployeeCache {

    private RoomDB roomDB;
    private EmployeeMapper mapper;
    private PrefUtils prefUtils;

    @Inject
    public EmployeeCacheImpl(RoomDB roomDB, @Named(EmployeeMapper.EMPLOYEE_MAPPER_CACHE) EmployeeMapper mapper,
                             PrefUtils prefUtils) {
        this.roomDB = roomDB;
        this.mapper = mapper;
        this.prefUtils = prefUtils;
    }

    @Override
    public Completable clearEmployees() {
        return null;
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
                });
    }

    @Override
    public Flowable<List<EmployeeEntity>> getEmployees() {
        return null;
    }

    @Override
    public Single<Boolean> isCache() {
        return Single.create(e -> e.onSuccess(false));
    }

    @Override
    public void setLastCacheTime(long lastCacheTime) {

    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
