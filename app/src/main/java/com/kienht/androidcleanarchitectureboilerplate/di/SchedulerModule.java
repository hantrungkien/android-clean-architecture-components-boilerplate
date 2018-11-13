package com.kienht.androidcleanarchitectureboilerplate.di;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Note:
 * Created by kienht on 5/2/18.
 */

@Module
abstract class SchedulerModule {

    @Singleton
    @Provides
    @Named("SchedulerType.IO")
    static Scheduler schedulerIO() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @Named("SchedulerType.COMPUTATION")
    static Scheduler schedulerComputation() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @Named("SchedulerType.UI")
    static Scheduler schedulerUI() {
        return AndroidSchedulers.mainThread();
    }
}
