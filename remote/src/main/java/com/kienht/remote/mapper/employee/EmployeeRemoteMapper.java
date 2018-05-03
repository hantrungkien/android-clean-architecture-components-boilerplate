package com.kienht.remote.mapper.employee;

import com.kienht.data.model.EmployeeData;
import com.kienht.remote.mapper.Mapper;
import com.kienht.remote.model.EmployeeRemote;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeRemoteMapper implements Mapper<EmployeeRemote, EmployeeData> {

    @Inject
    public EmployeeRemoteMapper() {
    }

    @Override
    public EmployeeData mapToEntity(EmployeeRemote type) {
        return new EmployeeData(type.getId(), type.getName(), type.getImgUrl());
    }
}
