package com.kienht.remote.mapper.employee;

import com.kienht.data.model.EmployeeEntity;
import com.kienht.remote.mapper.Mapper;
import com.kienht.remote.model.EmployeeModel;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeRemoteMapper implements Mapper<EmployeeModel, EmployeeEntity> {

    @Inject
    public EmployeeRemoteMapper() {
    }

    @Override
    public EmployeeEntity mapToEntity(EmployeeModel type) {
        return new EmployeeEntity(type.getId(), type.getName(), type.getImgUrl());
    }
}
