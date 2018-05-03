package com.kienht.presentation.features.home;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.kienht.domain.usecase.employee_list.EmployeeListUseCase;
import com.kienht.presentation.mapper.employee.EmployeePresentMapper;

/**
 * Note:
 * Created by kienht on 5/1/18.
 */

public class HomeViewModelFactory implements ViewModelProvider.Factory {

    private EmployeeListUseCase employeeListUseCase;
    private EmployeePresentMapper EmployeePresentMapper;

    public HomeViewModelFactory(EmployeeListUseCase employeeListUseCase, EmployeePresentMapper EmployeePresentMapper) {
        this.employeeListUseCase = employeeListUseCase;
        this.EmployeePresentMapper = EmployeePresentMapper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(employeeListUseCase, EmployeePresentMapper);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
