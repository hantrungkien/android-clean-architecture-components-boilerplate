package com.kienht.cache.mapper.employee;

import com.kienht.cache.mapper.Mapper;
import com.kienht.cache.model.EmployeeCache;
import com.kienht.data.model.EmployeeData;

import javax.inject.Inject;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeCacheMapper implements Mapper<EmployeeCache, EmployeeData> {

    public static final String EMPLOYEE_MAPPER_CACHE = "EMPLOYEE_MAPPER_CACHE";

    @Inject
    public EmployeeCacheMapper() {
    }

    @Override
    public EmployeeData mapFromCached(EmployeeCache type) {
        return new EmployeeData(type.getId(), type.getName(), type.getImgUrl());
    }

    @Override
    public EmployeeCache mapToCached(EmployeeData type) {
        return new EmployeeCache(type.getId(), type.getName(), type.getImgUrl());
    }
}
