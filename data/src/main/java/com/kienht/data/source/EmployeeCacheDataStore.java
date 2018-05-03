package com.kienht.data.source;

import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeDataStore;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeCacheDataStore implements EmployeeDataStore {

    private EmployeeCache employeeCache;

    @Inject
    public EmployeeCacheDataStore(EmployeeCache employeeCache) {
        this.employeeCache = employeeCache;
    }

    @Override
    public Completable saveEmployees(List<EmployeeEntity> employees) {
        return employeeCache.saveEmployees(employees)
                .doOnComplete(() -> employeeCache.setLastCacheTime(System.currentTimeMillis()));
    }

    @Override
    public Flowable<List<EmployeeEntity>> getEmployees() {
        return employeeCache.getEmployees();
    }

    @Override
    public Single<Boolean> isCached() {
        return employeeCache.isCache();
    }
}
