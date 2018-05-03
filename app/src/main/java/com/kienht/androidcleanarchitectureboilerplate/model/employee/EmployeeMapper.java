package com.kienht.androidcleanarchitectureboilerplate.model.employee;

import com.kienht.androidcleanarchitectureboilerplate.model.Mapper;
import com.kienht.presentation.model.EmployeeView;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeMapper implements Mapper<EmployeeView, EmployeeViewModel> {

    @Inject
    public EmployeeMapper() {
    }

    @Override
    public EmployeeViewModel mapToViewModel(EmployeeView type) {
        return new EmployeeViewModel(type.getId(), type.getName(), type.getImgUrl());
    }
}
