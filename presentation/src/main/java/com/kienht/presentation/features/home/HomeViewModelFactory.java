package com.kienht.presentation.features.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.kienht.domain.usecase.employee_list.GetEmployeeListUseCase;
import com.kienht.presentation.mapper.employee.EmployeeMapper;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Note:
 * Created by kienht on 5/1/18.
 */

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private GetEmployeeListUseCase getEmployeeListUseCase;
    private EmployeeMapper employeeMapper;

    public HomeViewModelFactory(GetEmployeeListUseCase getEmployeeListUseCase, EmployeeMapper employeeMapper) {
        this.getEmployeeListUseCase = getEmployeeListUseCase;
        this.employeeMapper = employeeMapper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(getEmployeeListUseCase, employeeMapper);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
