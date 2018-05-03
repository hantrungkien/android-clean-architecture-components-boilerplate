package com.kienht.androidcleanarchitectureboilerplate.features.base.module;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;


import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule {

    public static final String ACTIVITY_FRAGMENT_MANAGER = "BaseActivityModule.activityFragmentManager";

    @Binds
    @PerActivity
    abstract Context activityContext(Activity activity);

    @Provides
    @Named(ACTIVITY_FRAGMENT_MANAGER)
    @PerActivity
    static FragmentManager activityFragmentManager(Activity activity) {
        return activity.getFragmentManager();
    }
}
