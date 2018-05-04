package com.kienht.androidcleanarchitectureboilerplate.features.employee_list.module;

import android.app.Activity;

import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.module.BaseActivityModule;
import com.kienht.androidcleanarchitectureboilerplate.features.employee_list.EmployeeListActivity;
import com.kienht.domain.usecase.employee_list.EmployeeListUseCase;
import com.kienht.presentation.features.employee_list.EmployeeListViewModelFactory;
import com.kienht.presentation.mapper.employee.EmployeePresentMapper;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class EmployeeListActivityModule {
    @Binds
    @PerActivity
    abstract Activity activity(EmployeeListActivity employeeListActivity);

    @Provides
    @PerActivity
    static EmployeeListViewModelFactory homeViewModelFactory(EmployeeListUseCase employeeListUseCase, EmployeePresentMapper EmployeePresentMapper) {
        return new EmployeeListViewModelFactory(employeeListUseCase, EmployeePresentMapper);
    }
}
