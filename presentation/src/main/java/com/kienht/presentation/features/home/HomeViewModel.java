package com.kienht.presentation.features.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kienht.domain.model.EmployeeDomain;
import com.kienht.domain.usecase.employee_list.EmployeeListUseCase;
import com.kienht.presentation.data.Resource;
import com.kienht.presentation.data.ResourceState;
import com.kienht.presentation.mapper.employee.EmployeePresentMapper;
import com.kienht.presentation.model.EmployeePresent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Note:
 * Created by kienht on 5/1/18.
 */
public class HomeViewModel extends ViewModel {

    private EmployeeListUseCase employeeListUseCase;
    private EmployeePresentMapper EmployeePresentMapper;

    private MutableLiveData<Resource<List<EmployeePresent>>> employeesLiveData = new MutableLiveData<>();

    @Inject
    public HomeViewModel(EmployeeListUseCase employeeListUseCase, EmployeePresentMapper EmployeePresentMapper) {
        this.employeeListUseCase = employeeListUseCase;
        this.EmployeePresentMapper = EmployeePresentMapper;
    }

    @Override
    public void onCleared() {
        employeeListUseCase.dispose();
        super.onCleared();
    }

    public LiveData<Resource<List<EmployeePresent>>> getEmployees() {
        fetchEmployees();
        return employeesLiveData;
    }

    public void fetchEmployees() {
        employeesLiveData.postValue(new Resource<>(ResourceState.LOADING, null, null));
        employeeListUseCase.execute(new EmployeeSubscriber());
    }

    private class EmployeeSubscriber extends DisposableSubscriber<List<EmployeeDomain>> {

        @Override
        public void onNext(List<EmployeeDomain> employeeDomains) {
            List<EmployeePresent> employeePresents = Observable.fromIterable(employeeDomains)
                    .map(employee -> EmployeePresentMapper.mapToView(employee))
                    .toList()
                    .blockingGet();
            employeesLiveData.postValue(new Resource<>(ResourceState.SUCCESS, employeePresents, null));
        }

        @Override
        public void onError(Throwable t) {
            employeesLiveData.postValue(new Resource<>(ResourceState.ERROR, null, t.getMessage()));
        }

        @Override
        public void onComplete() {

        }
    }
}
