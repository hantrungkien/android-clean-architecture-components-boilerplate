package com.kienht.data;

import com.kienht.data.mapper.employee.EmployeeMapper;
import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.source.EmployeeDataStoreFactory;
import com.kienht.domain.model.Employee;
import com.kienht.domain.repository.EmployeeRepository;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private EmployeeDataStoreFactory employeeDataStoreFactory;
    private EmployeeMapper employeeMapper;

    @Inject
    public EmployeeRepositoryImpl(EmployeeDataStoreFactory employeeDataStoreFactory, EmployeeMapper employeeMapper) {
        this.employeeDataStoreFactory = employeeDataStoreFactory;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Completable saveEmployees(List<Employee> employees) {
        List<EmployeeEntity> list = Observable.fromIterable(employees)
                .map(employee -> employeeMapper.mapFromEntity(employee))
                .toList()
                .blockingGet();
        return employeeDataStoreFactory.retrieveCacheDataStore().saveEmployees(list);
    }

    @Override
    public Flowable<List<Employee>> getEmployeeList() {
        return employeeDataStoreFactory.retrieveCacheDataStore().isCached()
                .flatMapPublisher((Function<Boolean, Publisher<List<EmployeeEntity>>>) isCache ->
                        employeeDataStoreFactory.retrieveDataStore(isCache).getEmployees())
                .flatMap((Function<List<EmployeeEntity>, Publisher<List<Employee>>>) employeeEntities ->
                        Flowable.fromIterable(employeeEntities)
                                .map(employeeEntity -> employeeMapper.mapToEntity(employeeEntity))
                                .toList()
                                .toFlowable())
                .flatMap(employees -> saveEmployees(employees).toSingle(() -> employees).toFlowable());
    }
}
