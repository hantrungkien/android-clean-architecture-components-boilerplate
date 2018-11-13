package com.kienht.data.mapper.employee;

import com.kienht.data.mapper.Mapper;
import com.kienht.data.model.EmployeeEntity;
import com.kienht.domain.model.Employee;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Singleton
public class EmployeeEntityMapper implements Mapper<EmployeeEntity, Employee> {

    @Inject
    public EmployeeEntityMapper() {
    }

    @Override
    public Employee mapToEntity(EmployeeEntity type) {
        if (type == null) {
            return null;
        }
        return new Employee(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeEntity mapFromEntity(Employee type) {
        if (type == null) {
            return null;
        }
        return new EmployeeEntity(type.getId(), type.getName(), type.getImgUrl());
    }
}
