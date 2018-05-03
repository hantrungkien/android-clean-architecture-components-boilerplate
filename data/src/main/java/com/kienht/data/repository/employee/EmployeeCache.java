package com.kienht.data.repository.employee;

import com.kienht.data.model.EmployeeEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface EmployeeCache {

    Completable clearEmployees();

    Completable saveEmployees(List<EmployeeEntity> employees);

    Flowable<List<EmployeeEntity>> getEmployees();

    Single<Boolean> isCache();

    void setLastCacheTime(long lastCacheTime);

    boolean isExpired();

}
