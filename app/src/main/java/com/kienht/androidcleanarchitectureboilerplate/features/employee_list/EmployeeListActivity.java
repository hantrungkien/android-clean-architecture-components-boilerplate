package com.kienht.androidcleanarchitectureboilerplate.features.employee_list;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.features.base.BaseActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.base.listener.OnClickEmployeeItemListener;
import com.kienht.androidcleanarchitectureboilerplate.features.employee_list.adapter.EmployeeAdapter;
import com.kienht.presentation.data.ResourceState;
import com.kienht.presentation.features.employee_list.EmployeeListViewModel;
import com.kienht.presentation.model.EmployeeViewData;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class EmployeeListActivity extends BaseActivity implements OnClickEmployeeItemListener {

    public static final String TAG = EmployeeListActivity.class.getSimpleName();

    @BindView(R.id.list_employee)
    RecyclerView mRcvEmployee;

    @Inject
    EmployeeAdapter employeeAdapter;

    private EmployeeListViewModel employeeListViewModel;

    @Override
    public int getLayoutRes() {
        return R.layout.employee_list_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        employeeListViewModel = ViewModelProviders.of(this, viewModelFactory).get(EmployeeListViewModel.class);

        initRcvEmployees();
    }

    @Override
    protected void onStart() {
        super.onStart();
        employeeListViewModel.getEmployees().observe(this, listResource -> {
            if (listResource != null) {
                handleDataState(listResource.getStatus(), listResource.getData(), listResource.getMessage());
            }
        });
    }

    @Override
    public void onClickEmployee(EmployeeViewData employeeViewData) {
        Toast.makeText(this, employeeViewData.getName(), Toast.LENGTH_SHORT).show();
    }

    private void initRcvEmployees() {
        mRcvEmployee.setLayoutManager(new LinearLayoutManager(this));
        mRcvEmployee.setHasFixedSize(true);
        mRcvEmployee.setItemAnimator(null);
        mRcvEmployee.setAdapter(employeeAdapter);
    }

    private void handleDataState(ResourceState resourceState, List<EmployeeViewData> data, String message) {
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

    private void setupScreenForSuccess(List<EmployeeViewData> data) {
        Log.e(TAG, "setupScreenForSuccess: SUCCESS");
        employeeAdapter.swapData(data);
    }

    private void setupScreenForError(String message) {
        Log.e(TAG, "setupScreenForError" + message);
    }
}
