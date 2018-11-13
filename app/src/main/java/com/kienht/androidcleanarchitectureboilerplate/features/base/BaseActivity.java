package com.kienht.androidcleanarchitectureboilerplate.features.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kienht.androidcleanarchitectureboilerplate.features.base.module.BaseActivityModule;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */
public abstract class BaseActivity extends AppCompatActivity implements HasFragmentInjector {

    public static final String TAG = BaseActivity.class.getSimpleName();

    @Inject
    @Named(BaseActivityModule.ACTIVITY_FRAGMENT_MANAGER)
    public FragmentManager fragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());
        ButterKnife.bind(this);
    }

    @Override
    public final AndroidInjector<Fragment> fragmentInjector() {
        return fragmentInjector;
    }
}
