package com.kienht.androidcleanarchitectureboilerplate.di;

import com.kienht.cache.features.employee_list.EmployeeCacheImpl;
import com.kienht.data.EmployeeRepositoryImpl;
import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.remote.features.employee_list.EmployeeRemoteImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract EmployeeRepository employeeRepository(EmployeeRepositoryImpl employeeRepositoryImpl);

    @Singleton
    @Binds
    abstract EmployeeCache employeeCache(EmployeeCacheImpl employeeCacheImpl);

    @Singleton
    @Binds
    abstract EmployeeRemote employeeRemote(EmployeeRemoteImpl employeeRemoteImpl);
}
