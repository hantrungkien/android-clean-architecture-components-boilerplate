package com.kienht.androidcleanarchitectureboilerplate.features.employee_list.module;

import android.app.Activity;

import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.module.BaseActivityModule;
import com.kienht.androidcleanarchitectureboilerplate.features.employee_list.EmployeeListActivity;

import dagger.Binds;
import dagger.Module;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */

@Module(includes = BaseActivityModule.class)
public abstract class EmployeeListActivityModule {
    @Binds
    @PerActivity
    abstract Activity activity(EmployeeListActivity employeeListActivity);

}
