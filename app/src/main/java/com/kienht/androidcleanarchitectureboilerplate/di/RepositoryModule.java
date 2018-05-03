package com.kienht.androidcleanarchitectureboilerplate.di;

import com.kienht.cache.PrefUtils;
import com.kienht.cache.database.RoomDB;
import com.kienht.cache.features.employee.EmployeeCacheImpl;
import com.kienht.cache.mapper.employee.EmployeeCacheMapper;
import com.kienht.data.EmployeeRepositoryImpl;
import com.kienht.data.mapper.employee.EmployeeDataMapper;
import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.data.source.EmployeeDataStoreFactory;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.remote.OICService;
import com.kienht.remote.features.employee.EmployeeRemoteImpl;
import com.kienht.remote.mapper.employee.EmployeeRemoteMapper;

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
    static EmployeeRepository oicEmployeeRepository(EmployeeDataStoreFactory employeeDataStoreFactory, EmployeeDataMapper EmployeeDataMapper) {
        return new EmployeeRepositoryImpl(employeeDataStoreFactory, EmployeeDataMapper);
    }

    @Singleton
    @Provides
    static EmployeeCache employeeCache(RoomDB roomDB,
                                       EmployeeCacheMapper mapper,
                                       PrefUtils prefUtils,
                                       @Named("SchedulerType.COMPUTATION") Scheduler schedulerComputation) {
        return new EmployeeCacheImpl(roomDB, mapper, prefUtils, schedulerComputation);
    }

    @Singleton
    @Provides
    static EmployeeRemote employeeRemote(OICService oicService,
                                         EmployeeRemoteMapper EmployeeRemoteMapper) {
        return new EmployeeRemoteImpl(oicService, EmployeeRemoteMapper);
    }
}
