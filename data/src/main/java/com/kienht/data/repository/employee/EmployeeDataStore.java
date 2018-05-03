package com.kienht.data.repository.employee;

import com.kienht.data.model.EmployeeData;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface EmployeeDataStore {

    Completable saveEmployees(List<EmployeeData> employees);

    Flowable<List<EmployeeData>> getEmployees();

    Single<Boolean> isCached();

}
