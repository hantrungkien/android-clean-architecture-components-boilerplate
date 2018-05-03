package com.kienht.androidcleanarchitectureboilerplate.features.home.module;

import android.app.Activity;

import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.module.BaseActivityModule;
import com.kienht.androidcleanarchitectureboilerplate.features.home.HomeActivity;
import com.kienht.domain.usecase.employee_list.GetEmployeeListUseCase;
import com.kienht.presentation.features.home.HomeViewModelFactory;
import com.kienht.presentation.mapper.employee.EmployeeMapper;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class HomeActivityModule {
    @Binds
    @PerActivity
    abstract Activity activity(HomeActivity homeActivity);

    @Provides
    @PerActivity
    static HomeViewModelFactory homeViewModelFactory(GetEmployeeListUseCase employeeListUseCase, EmployeeMapper employeeMapper) {
        return new HomeViewModelFactory(employeeListUseCase, employeeMapper);
    }
}
