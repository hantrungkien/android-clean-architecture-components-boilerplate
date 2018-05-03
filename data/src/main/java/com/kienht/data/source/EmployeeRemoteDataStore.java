package com.kienht.data.source;

import com.kienht.data.model.EmployeeData;
import com.kienht.data.repository.employee.EmployeeDataStore;
import com.kienht.data.repository.employee.EmployeeRemote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeRemoteDataStore implements EmployeeDataStore {

    private EmployeeRemote employeeRemote;

    @Inject
    public EmployeeRemoteDataStore(EmployeeRemote employeeRemote) {
        this.employeeRemote = employeeRemote;
    }

    @Override
    public Completable saveEmployees(List<EmployeeData> employees) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Flowable<List<EmployeeData>> getEmployees() {
        return employeeRemote.getEmployees();
    }

    @Override
    public Single<Boolean> isCached() {
        throw new UnsupportedOperationException();
    }
}
