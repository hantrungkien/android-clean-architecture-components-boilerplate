package com.kienht.androidcleanarchitectureboilerplate.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.kienht.androidcleanarchitectureboilerplate.di.key.ViewModelKey;
import com.kienht.presentation.features.OICViewModelFactory;
import com.kienht.presentation.features.employee_list.EmployeeListViewModel;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Note:
 * Created by kienht on 5/30/18.
 */

@Module
abstract class ViewModelModule {

    @Binds
    @Singleton
    abstract ViewModelProvider.Factory bindViewModelFactory(OICViewModelFactory OICViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeListViewModel.class)
    abstract ViewModel bindEmployeeListViewModel(EmployeeListViewModel employeeListViewModel);

}
