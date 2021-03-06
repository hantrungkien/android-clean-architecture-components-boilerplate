package com.kienht.data.repository.employee;

import com.kienht.data.model.EmployeeEntity;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface EmployeeRemote {

    Flowable<List<EmployeeEntity>> getEmployees();

}
