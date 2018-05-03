package com.kienht.data.mapper.employee;

import com.kienht.data.mapper.Mapper;
import com.kienht.data.model.EmployeeEntity;
import com.kienht.domain.model.Employee;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeMapper implements Mapper<EmployeeEntity, Employee> {

    @Inject
    public EmployeeMapper() {
    }

    @Override
    public Employee mapToEntity(EmployeeEntity type) {
        return new Employee(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeEntity mapFromEntity(Employee type) {
        return new EmployeeEntity(type.getId(), type.getName(), type.getImgUrl());
    }
}
