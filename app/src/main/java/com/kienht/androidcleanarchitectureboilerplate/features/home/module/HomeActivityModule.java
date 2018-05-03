package com.kienht.androidcleanarchitectureboilerplate.features.home.module;

import android.app.Activity;

import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.module.BaseActivityModule;
import com.kienht.androidcleanarchitectureboilerplate.features.home.HomeActivity;
import com.kienht.domain.usecase.employee_list.EmployeeListUseCase;
import com.kienht.presentation.features.home.HomeViewModelFactory;
import com.kienht.presentation.mapper.employee.EmployeePresentMapper;

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
    static HomeViewModelFactory homeViewModelFactory(EmployeeListUseCase employeeListUseCase, EmployeePresentMapper EmployeePresentMapper) {
        return new HomeViewModelFactory(employeeListUseCase, EmployeePresentMapper);
    }
}
