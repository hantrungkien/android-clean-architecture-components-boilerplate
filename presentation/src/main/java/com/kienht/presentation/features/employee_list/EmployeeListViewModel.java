package com.kienht.presentation.features.employee_list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.kienht.domain.model.Employee;
import com.kienht.domain.usecase.employee_list.EmployeeListUseCase;
import com.kienht.presentation.data.Resource;
import com.kienht.presentation.data.ResourceState;
import com.kienht.presentation.mapper.employee.EmployeeViewDataMapper;
import com.kienht.presentation.model.EmployeeViewData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Note:
 * Created by kienht on 5/1/18.
 */

public class EmployeeListViewModel extends ViewModel {

    @Inject
    EmployeeListUseCase employeeListUseCase;

    @Inject
    EmployeeViewDataMapper EmployeeViewDataMapper;

    private CompositeDisposable compositeDisposable;

    private MutableLiveData<Resource<List<EmployeeViewData>>> employeesLiveData = new MutableLiveData<>();

    @Inject
    public EmployeeListViewModel() {
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCleared() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        super.onCleared();
    }

    public LiveData<Resource<List<EmployeeViewData>>> getEmployees() {
        fetchEmployees();
        return employeesLiveData;
    }

    public void fetchEmployees() {
        employeesLiveData.postValue(new Resource<>(ResourceState.LOADING, null, null));
        Disposable disposable = employeeListUseCase.execute(new EmployeeSubscriber());
        compositeDisposable.add(disposable);
    }

    private class EmployeeSubscriber extends DisposableSubscriber<List<Employee>> {

        @Override
        public void onNext(List<Employee> employees) {
            List<EmployeeViewData> employeeViewData = Observable.fromIterable(employees)
                    .map(employee -> EmployeeViewDataMapper.mapToView(employee))
                    .toList()
                    .blockingGet();
            employeesLiveData.postValue(new Resource<>(ResourceState.SUCCESS, employeeViewData, null));
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
