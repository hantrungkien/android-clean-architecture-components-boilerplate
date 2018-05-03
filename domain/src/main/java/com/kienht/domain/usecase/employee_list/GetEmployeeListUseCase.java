package com.kienht.domain.usecase.employee_list;

import com.kienht.domain.di.scheduler.RunOn;
import com.kienht.domain.di.scheduler.SchedulerType;
import com.kienht.domain.model.Employee;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.domain.usecase.FlowableUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */
public class GetEmployeeListUseCase extends FlowableUseCase<List<Employee>> {

    private EmployeeRepository employeeRepository;
    private Scheduler schedulerIO;
    private Scheduler schedulerUI;

    @Inject
    public GetEmployeeListUseCase(EmployeeRepository employeeRepository,
                                  @RunOn(SchedulerType.IO) Scheduler schedulerIO,
                                  @RunOn(SchedulerType.UI) Scheduler schedulerUI) {
        super(schedulerIO, schedulerUI);
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected Flowable<List<Employee>> buildUseCaseObservable() {
        return employeeRepository.getEmployeeList();
    }
}
