package com.kienht.presentation.mapper.employee;

import com.kienht.domain.model.EmployeeDomain;
import com.kienht.presentation.mapper.Mapper;
import com.kienht.presentation.model.EmployeePresent;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeePresentMapper implements Mapper<EmployeeDomain, EmployeePresent> {

    @Inject
    public EmployeePresentMapper() {
    }

    @Override
    public EmployeePresent mapToView(EmployeeDomain type) {
        return new EmployeePresent(type.getId(), type.getName(), type.getImgUrl());
    }
}
