package com.kienht.cache.mapper.employee;

import com.kienht.cache.mapper.Mapper;
import com.kienht.cache.model.EmployeeCached;
import com.kienht.data.model.EmployeeEntity;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeMapper implements Mapper<EmployeeCached, EmployeeEntity> {

    public static final String EMPLOYEE_MAPPER_CACHE = "EMPLOYEE_MAPPER_CACHE";

    @Inject
    public EmployeeMapper() {
    }

    @Override
    public EmployeeEntity mapFromCached(EmployeeCached type) {
        return new EmployeeEntity(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeCached mapToCached(EmployeeEntity type) {
        return new EmployeeCached(type.getId(), type.getName(), type.getImgUrl());
    }
}
