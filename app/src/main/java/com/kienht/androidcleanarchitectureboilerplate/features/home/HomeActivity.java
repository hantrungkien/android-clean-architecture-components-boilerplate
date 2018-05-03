package com.kienht.androidcleanarchitectureboilerplate.features.home;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;

import com.kienht.androidcleanarchitectureboilerplate.R;
import com.kienht.androidcleanarchitectureboilerplate.features.base.BaseActivity;
import com.kienht.data.EmployeeRepositoryImpl;
import com.kienht.data.repository.employee.EmployeeCache;
import com.kienht.data.repository.employee.EmployeeRemote;
import com.kienht.domain.repository.EmployeeRepository;
import com.kienht.presentation.features.home.HomeViewModel;
import com.kienht.presentation.features.home.HomeViewModelFactory;
import com.kienht.remote.mapper.employee.EmployeeMapper;

import javax.inject.Inject;
import javax.inject.Named;

public class HomeActivity extends BaseActivity {

    public static final String TAG = HomeActivity.class.getSimpleName();

    @Inject
    HomeViewModelFactory homeViewModelFactory;

    private HomeViewModel homeViewModel;

    @Override
    public int getLayoutRes() {
        return R.layout.home_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this, homeViewModelFactory).get(HomeViewModel.class);

        homeViewModel.getEmployees().observe(this, listResource -> {
            if (listResource != null) {
                switch (listResource.getStatus()) {
                    case LOADING:
                        Log.e(TAG, "onChanged: LOADING");
                        break;
                    case SUCCESS:
                        Log.e(TAG, "onChanged: SUCCESS" + listResource.getData());
                        break;
                    case ERROR:
                        Log.e(TAG, "onChanged: LOADING" + listResource.getStatus());
                        break;
                }
            }
        });

    }
}
