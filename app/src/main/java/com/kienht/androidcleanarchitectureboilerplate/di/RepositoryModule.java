package com.kienht.androidcleanarchitectureboilerplate.di;

import com.kienht.cache.PrefUtils;
import com.kienht.cache.database.RoomDB;
import com.kienht.cache.features.employee.EmployeeCacheImpl;
import com.kienht.data.EmployeeRepositoryImpl;
import com.kienht.data.mapper.employee.EmployeeMapper;
import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.data.source.EmployeeDataStoreFactory;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.remote.OICService;
import com.kienht.remote.features.employee.EmployeeRemoteImpl;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import io.reactivex.Scheduler;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Module(includes = AndroidInjectionModule.class)
abstract class RepositoryModule {

    @Singleton
    @Provides
    static EmployeeRepository oicEmployeeRepository(EmployeeDataStoreFactory employeeDataStoreFactory, EmployeeMapper employeeMapper) {
        return new EmployeeRepositoryImpl(employeeDataStoreFactory, employeeMapper);
    }

    @Singleton
    @Provides
    static EmployeeCache employeeCache(RoomDB roomDB,
                                       com.kienht.cache.mapper.employee.EmployeeMapper mapper,
                                       PrefUtils prefUtils,
                                       @Named("SchedulerType.COMPUTATION") Scheduler schedulerComputation) {
        return new EmployeeCacheImpl(roomDB, mapper, prefUtils, schedulerComputation);
    }

    @Singleton
    @Provides
    static EmployeeRemote employeeRemote(OICService oicService,
                                         com.kienht.remote.mapper.employee.EmployeeMapper employeeMapper) {
        return new EmployeeRemoteImpl(oicService, employeeMapper);
    }
}
