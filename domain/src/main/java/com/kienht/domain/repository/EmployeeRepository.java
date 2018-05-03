package com.kienht.domain.repository;

import com.kienht.domain.model.EmployeeDomain;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public interface EmployeeRepository {

    Completable saveEmployees(List<EmployeeDomain> employeeDomains);

    Flowable<List<EmployeeDomain>> getEmployeeList();

}
