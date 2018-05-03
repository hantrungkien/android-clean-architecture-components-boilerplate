package com.kienht.presentation.features.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kienht.domain.model.Employee;
import com.kienht.domain.usecase.employee_list.GetEmployeeListUseCase;
import com.kienht.presentation.data.Resource;
import com.kienht.presentation.data.ResourceState;
import com.kienht.presentation.mapper.employee.EmployeeMapper;
import com.kienht.presentation.model.EmployeeView;

import java.util.List;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Note:
 * Created by kienht on 5/1/18.
 */
public class HomeViewModel extends ViewModel {

    private GetEmployeeListUseCase getEmployeeListUseCase;
    private EmployeeMapper employeeMapper;

    private MutableLiveData<Resource<List<EmployeeView>>> employeesLiveData = new MutableLiveData<>();

    public HomeViewModel(GetEmployeeListUseCase getEmployeeListUseCase, EmployeeMapper employeeMapper) {
        this.getEmployeeListUseCase = getEmployeeListUseCase;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public void onCleared() {
        getEmployeeListUseCase.dispose();
        super.onCleared();
    }

    public LiveData<Resource<List<EmployeeView>>> getEmployees() {
        fetchEmployees();
        return employeesLiveData;
    }

    public void fetchEmployees() {
        employeesLiveData.postValue(new Resource<>(ResourceState.LOADING, null, null));
        getEmployeeListUseCase.execute(new EmployeeSubscriber());
    }

    private class EmployeeSubscriber extends DisposableSubscriber<List<Employee>> {

        @Override
        public void onNext(List<Employee> employees) {
            List<EmployeeView> employeeViews = Observable.fromIterable(employees)
                    .map(employee -> employeeMapper.mapToView(employee))
                    .toList()
                    .blockingGet();
            employeesLiveData.postValue(new Resource<>(ResourceState.SUCCESS, employeeViews, null));
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
