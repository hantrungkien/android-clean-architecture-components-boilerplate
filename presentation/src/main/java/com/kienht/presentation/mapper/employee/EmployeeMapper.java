package com.kienht.presentation.mapper.employee;

import com.kienht.domain.model.Employee;
import com.kienht.presentation.mapper.Mapper;
import com.kienht.presentation.model.EmployeeView;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeMapper implements Mapper<Employee, EmployeeView> {

    @Inject
    public EmployeeMapper() {
    }

    @Override
    public EmployeeView mapToView(Employee type) {
        return new EmployeeView(type.getId(), type.getName(), type.getImgUrl());
    }
}
