package com.kienht.data.mapper.employee;

import com.kienht.data.mapper.Mapper;
import com.kienht.data.model.EmployeeData;
import com.kienht.domain.model.EmployeeDomain;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeDataMapper implements Mapper<EmployeeData, EmployeeDomain> {

    @Inject
    public EmployeeDataMapper() {
    }

    @Override
    public EmployeeDomain mapToEntity(EmployeeData type) {
        return new EmployeeDomain(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeData mapFromEntity(EmployeeDomain type) {
        return new EmployeeData(type.getId(), type.getName(), type.getImgUrl());
    }
}
