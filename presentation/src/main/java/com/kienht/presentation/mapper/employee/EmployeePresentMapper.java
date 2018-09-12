package com.kienht.presentation.mapper.employee;

import com.kienht.domain.model.Employee;
import com.kienht.presentation.mapper.Mapper;
import com.kienht.presentation.model.EmployeeViewData;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeePresentMapper implements Mapper<Employee, EmployeeViewData> {

    @Inject
    public EmployeePresentMapper() {
    }

    @Override
    public EmployeeViewData mapToView(Employee type) {
        return new EmployeeViewData(type.getId(), type.getName(), type.getImgUrl());
    }
}
