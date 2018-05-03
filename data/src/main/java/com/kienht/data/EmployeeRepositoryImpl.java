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
    private EmployeeDataMapper EmployeeDataMapper;

    @Inject
    public EmployeeRepositoryImpl(EmployeeDataStoreFactory employeeDataStoreFactory, EmployeeDataMapper EmployeeDataMapper) {
        this.employeeDataStoreFactory = employeeDataStoreFactory;
        this.EmployeeDataMapper = EmployeeDataMapper;
    }

    @Override
    public Completable saveEmployees(List<EmployeeDomain> employeeDomains) {
        List<EmployeeData> list = Observable.fromIterable(employeeDomains)
                .map(employee -> EmployeeDataMapper.mapFromEntity(employee))
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
                                .map(employeeEntity -> EmployeeDataMapper.mapToEntity(employeeEntity))
                                .toList()
                                .toFlowable())
                .flatMap(employees -> saveEmployees(employees).toSingle(() -> employees).toFlowable());
    }
}
