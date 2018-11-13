package com.kienht.androidcleanarchitectureboilerplate.features.employee_list.module;

import android.app.Activity;

import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.listener.OnClickEmployeeItemListener;
import com.kienht.androidcleanarchitectureboilerplate.features.base.module.BaseActivityModule;
import com.kienht.androidcleanarchitectureboilerplate.features.employee_list.EmployeeListActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.employee_list.adapter.EmployeeAdapter;

import javax.inject.Named;

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

    @Binds
    @PerActivity
    @Named(EmployeeAdapter.TAG)
    abstract OnClickEmployeeItemListener onClickEmployeeItemListener(EmployeeListActivity employeeListActivity);

}
