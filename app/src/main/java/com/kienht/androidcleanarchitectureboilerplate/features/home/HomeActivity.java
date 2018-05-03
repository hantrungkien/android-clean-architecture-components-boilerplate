package com.kienht.androidcleanarchitectureboilerplate.features.home;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.features.base.BaseActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.home.adapter.EmployeeAdapter;
import com.kienht.androidcleanarchitectureboilerplate.model.employee.EmployeeMapper;
import com.kienht.androidcleanarchitectureboilerplate.model.employee.Employee;
import com.kienht.cache.database.RoomDB;
import com.kienht.presentation.data.ResourceState;
import com.kienht.presentation.features.home.HomeViewModel;
import com.kienht.presentation.features.home.HomeViewModelFactory;
import com.kienht.presentation.model.EmployeePresent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;

public class HomeActivity extends BaseActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.list_employee)
    RecyclerView mRcvEmployee;

    @Inject
    HomeViewModelFactory homeViewModelFactory;

    @Inject
    EmployeeAdapter employeeAdapter;

    @Inject
    RoomDB roomDB;

    @Inject
    EmployeeMapper employeeMapper;

    private HomeViewModel homeViewModel;

    @Override
    public int getLayoutRes() {
        return R.layout.home_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel.class);

        initRcvEmployees();
    }

    @Override
    protected void onStart() {
        super.onStart();
        homeViewModel.getEmployees().observe(this, listResource -> {
            if (listResource != null) {
                handleDataState(listResource.getStatus(), listResource.getData(), listResource.getMessage());
            }
        });
    }

    private void initRcvEmployees() {
        mRcvEmployee.setLayoutManager(new LinearLayoutManager(this));
        mRcvEmployee.setHasFixedSize(true);
        mRcvEmployee.setItemAnimator(null);
        mRcvEmployee.setAdapter(employeeAdapter);
    }

    private void handleDataState(ResourceState resourceState, List<EmployeePresent> data, String message) {
        switch (resourceState) {
            case LOADING:
                setupScreenForLoadingState();
                break;
            case SUCCESS:
                setupScreenForSuccess(data);
                break;
            case ERROR:
                setupScreenForError(message);
                break;
        }
    }

    private void setupScreenForLoadingState() {
        Log.e(TAG, "setupScreenForLoadingState: LOADING");
    }

    private void setupScreenForSuccess(List<EmployeePresent> data) {
        Log.e(TAG, "setupScreenForSuccess: SUCCESS");
        List<Employee> list = Observable.fromIterable(data)
                .map(employeePresent -> employeeMapper.mapToViewModel(employeePresent))
                .toList()
                .blockingGet();
        employeeAdapter.swapData(list);
    }

    private void setupScreenForError(String message) {
        Log.e(TAG, "setupScreenForError" + message);
    }

}
