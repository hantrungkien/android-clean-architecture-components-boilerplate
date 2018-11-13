package com.kienht.cache.mapper.employee;

import com.kienht.cache.mapper.Mapper;
import com.kienht.cache.model.EmployeeCached;
import com.kienht.data.model.EmployeeEntity;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeCacheMapper implements Mapper<EmployeeCached, EmployeeEntity> {

    @Inject
    public EmployeeCacheMapper() {
    }

    @Override
    public EmployeeEntity mapFromCached(EmployeeCached type) {
        if (type == null) {
            return null;
        }
        return new EmployeeEntity(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeCached mapToCached(EmployeeEntity type) {
        if (type == null) {
            return null;
        }
        return new EmployeeCached(type.getId(), type.getName(), type.getImgUrl());
    }
}
