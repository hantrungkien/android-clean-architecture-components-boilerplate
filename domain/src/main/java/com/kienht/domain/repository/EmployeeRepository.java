package com.kienht.domain.repository;

import com.kienht.domain.model.Employee;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface EmployeeRepository {

    Completable saveEmployees(List<Employee> employees);

    Flowable<List<Employee>> getEmployeeList();

}
