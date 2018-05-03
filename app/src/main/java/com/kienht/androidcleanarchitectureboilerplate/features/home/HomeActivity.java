package com.kienht.androidcleanarchitectureboilerplate.features.home;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.features.base.BaseActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.home.adapter.EmployeesAdapter;
import com.kienht.androidcleanarchitectureboilerplate.model.employee.EmployeeViewModel;
import com.kienht.cache.database.RoomDB;
import com.kienht.cache.model.EmployeeCached;
import com.kienht.data.EmployeeRepositoryImpl;
import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.presentation.data.ResourceState;
import com.kienht.presentation.features.home.HomeViewModel;
import com.kienht.presentation.features.home.HomeViewModelFactory;
import com.kienht.presentation.model.EmployeeView;
import com.kienht.remote.mapper.employee.EmployeeMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.kienht.presentation.data.ResourceState.ERROR;
import static com.kienht.presentation.data.ResourceState.LOADING;
import static com.kienht.presentation.data.ResourceState.SUCCESS;

public class HomeActivity extends BaseActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();

    @BindView(R.id.list_employee)
    RecyclerView mRcvEmployee;

    @Inject
    HomeViewModelFactory homeViewModelFactory;

    @Inject
    EmployeesAdapter employeesAdapter;

    @Inject
    RoomDB roomDB;

    @Inject
    com.kienht.androidcleanarchitectureboilerplate.model.employee.EmployeeMapper employeeMapper;

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
        mRcvEmployee.setAdapter(employeesAdapter);
    }

    private void handleDataState(ResourceState resourceState, List<EmployeeView> data, String message) {
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

    private void setupScreenForSuccess(List<EmployeeView> data) {
        Log.e(TAG, "setupScreenForSuccess: SUCCESS");
        List<EmployeeViewModel> list = Observable.fromIterable(data)
                .map(employeeView -> employeeMapper.mapToViewModel(employeeView))
                .toList()
                .blockingGet();
        employeesAdapter.swapData(list);
    }

    private void setupScreenForError(String message) {
        Log.e(TAG, "setupScreenForError" + message);
    }

}
