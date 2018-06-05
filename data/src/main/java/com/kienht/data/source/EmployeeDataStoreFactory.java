package com.kienht.data.source;

import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Singleton
public class EmployeeDataStoreFactory {

    @Inject
    EmployeeCache employeeCache;

    @Inject
    EmployeeCacheDataStore employeeCacheDataStore;

    @Inject
    EmployeeRemoteDataStore employeeRemoteDataStore;

    @Inject
    public EmployeeDataStoreFactory(){
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
