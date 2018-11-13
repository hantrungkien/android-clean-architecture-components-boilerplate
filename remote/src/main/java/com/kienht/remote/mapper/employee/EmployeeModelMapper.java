package com.kienht.remote.mapper.employee;

import com.kienht.data.model.EmployeeEntity;
import com.kienht.remote.mapper.Mapper;
import com.kienht.remote.model.EmployeeModel;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Singleton
public class EmployeeModelMapper implements Mapper<EmployeeModel, EmployeeEntity> {

    @Inject
    public EmployeeModelMapper() {
    }

    @Override
    public EmployeeEntity mapToEntity(EmployeeModel type) {
        if (type == null) {
            return null;
        }
        return new EmployeeEntity(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeModel mapToModel(EmployeeEntity type) {
        if (type == null) {
            return null;
        }
        return new EmployeeModel(type.getId(), type.getName(), type.getImgUrl());
    }
}
