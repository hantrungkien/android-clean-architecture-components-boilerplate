package com.kienht.data.source;

import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeDataStore;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeDataStoreFactory {

    private EmployeeCache employeeCache;
    private EmployeeCacheDataStore employeeCacheDataStore;
    private EmployeeRemoteDataStore employeeRemoteDataStore;

    @Inject
    public EmployeeDataStoreFactory(EmployeeCache employeeCache, EmployeeCacheDataStore employeeCacheDataStore, EmployeeRemoteDataStore employeeRemoteDataStore) {
        this.employeeCache = employeeCache;
        this.employeeCacheDataStore = employeeCacheDataStore;
        this.employeeRemoteDataStore = employeeRemoteDataStore;
    }

    public EmployeeDataStore retrieveDataStore(boolean isCache) {
        if (isCache && !employeeCache.isExpired()) {
            return retrieveCacheDataStore();
        }
        return retrieveRemoteDataStore();
    }

    public EmployeeDataStore retrieveCacheDataStore() {
        return employeeCacheDataStore;
    }

    public EmployeeDataStore retrieveRemoteDataStore() {
        return employeeRemoteDataStore;
    }
}
