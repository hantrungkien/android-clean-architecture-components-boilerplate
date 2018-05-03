package com.kienht.domain.usecase.employee_list;

import com.kienht.domain.model.EmployeeDomain;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.domain.usecase.FlowableUseCase;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class EmployeeListUseCase extends FlowableUseCase<List<EmployeeDomain>> {

    private EmployeeRepository employeeRepository;

    @Inject
    public EmployeeListUseCase(EmployeeRepository employeeRepository,
                               @Named("SchedulerType.IO") Scheduler schedulerIO,
                               @Named("SchedulerType.UI") Scheduler schedulerUI) {
        super(schedulerIO, schedulerUI);
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected Flowable<List<EmployeeDomain>> buildUseCaseObservable() {
        return employeeRepository.getEmployeeList();
    }
}
