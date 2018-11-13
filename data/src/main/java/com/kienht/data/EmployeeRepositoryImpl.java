package com.kienht.data;

import com.kienht.data.mapper.employee.EmployeeEntityMapper;
import com.kienht.data.model.EmployeeEntity;
import com.kienht.data.source.EmployeeDataStoreFactory;
import com.kienht.domain.model.Employee;
import com.kienht.domain.repository.EmployeeRepository;

import org.reactivestreams.Publisher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

@Singleton
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Inject
    EmployeeDataStoreFactory employeeDataStoreFactory;

    @Inject
    EmployeeEntityMapper employeeEntityMapper;

    @Inject
    public EmployeeRepositoryImpl() {
    }

    @Override
    public Completable saveEmployees(List<Employee> employees) {
        List<EmployeeEntity> list = Observable.fromIterable(employees)
                .map(employee -> employeeEntityMapper.mapFromEntity(employee))
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
                                .map(employeeEntity -> employeeEntityMapper.mapToEntity(employeeEntity))
                                .toList()
                                .toFlowable())
                .flatMap(employees -> saveEmployees(employees).toSingle(() -> employees).toFlowable());
    }
}
