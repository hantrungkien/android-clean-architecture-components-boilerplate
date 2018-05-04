package com.kienht.data;

import com.kienht.data.mapper.employee.EmployeeDataMapper;
import com.kienht.data.model.EmployeeData;
import com.kienht.data.source.EmployeeDataStoreFactory;
import com.kienht.domain.model.EmployeeDomain;
import com.kienht.domain.repository.EmployeeRepository;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EmployeeDataStoreFactory employeeDataStoreFactory;
    private EmployeeDataMapper employeeDataMapper;

    @Inject
    public EmployeeRepositoryImpl(EmployeeDataStoreFactory employeeDataStoreFactory, EmployeeDataMapper employeeDataMapper) {
        this.employeeDataStoreFactory = employeeDataStoreFactory;
        this.employeeDataMapper = employeeDataMapper;
    }

    @Override
    public Completable saveEmployees(List<EmployeeDomain> employeeDomains) {
        List<EmployeeData> list = Observable.fromIterable(employeeDomains)
                .map(employee -> employeeDataMapper.mapFromEntity(employee))
                .toList()
                .blockingGet();
        return employeeDataStoreFactory.retrieveCacheDataStore().saveEmployees(list);
    }

    @Override
    public Flowable<List<EmployeeDomain>> getEmployeeList() {
        return employeeDataStoreFactory.retrieveCacheDataStore().isCached()
                .flatMapPublisher((Function<Boolean, Publisher<List<EmployeeData>>>) isCache ->
                        employeeDataStoreFactory.retrieveDataStore(isCache).getEmployees())
                .flatMap((Function<List<EmployeeData>, Publisher<List<EmployeeDomain>>>) employeeEntities ->
                        Flowable.fromIterable(employeeEntities)
                                .map(employeeEntity -> employeeDataMapper.mapToEntity(employeeEntity))
                                .toList()
                                .toFlowable())
                .flatMap(employees -> saveEmployees(employees).toSingle(() -> employees).toFlowable());
    }
}
