package com.kienht.androidcleanarchitectureboilerplate.model.employee;

import com.kienht.androidcleanarchitectureboilerplate.model.Mapper;
import com.kienht.presentation.model.EmployeePresent;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeMapper implements Mapper<EmployeePresent, Employee> {

    @Inject
    public EmployeeMapper() {
    }

    @Override
    public Employee mapToViewModel(EmployeePresent type) {
        return new Employee(type.getId(), type.getName(), type.getImgUrl());
    }
}
