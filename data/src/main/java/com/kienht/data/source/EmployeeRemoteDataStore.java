package com.kienht.data.source;

import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.repository.employee.EmployeeDataStore;
import com.kienht.data.repository.employee.EmployeeRemote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Singleton
public class EmployeeRemoteDataStore implements EmployeeDataStore {

    @Inject
    EmployeeRemote employeeRemote;

    @Inject
    public EmployeeRemoteDataStore() {
    }

    @Override
    public Completable saveEmployees(List<EmployeeEntity> employees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flowable<List<EmployeeEntity>> getEmployees() {
        return employeeRemote.getEmployees();
    }

    @Override
    public Single<Boolean> isCached() {
        throw new UnsupportedOperationException();
    }
}
