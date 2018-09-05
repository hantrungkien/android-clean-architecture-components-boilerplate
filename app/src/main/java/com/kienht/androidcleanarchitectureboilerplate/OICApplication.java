package com.kienht.androidcleanarchitectureboilerplate;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.kienht.androidcleanarchitectureboilerplate.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */
public class OICApplication extends Application implements HasActivityInjector {

    public static final String TAG = OICApplication.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppLifecycleListener());

        DaggerAppComponent.builder().create(this).inject(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    static class AppLifecycleListener implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onMoveToForeground() {
            Log.e(TAG, "Returning to foreground…");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onMoveToBackground() {
            Log.e(TAG, "Moving to background…");
        }
    }
}
