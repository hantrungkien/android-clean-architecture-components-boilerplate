package com.kienht.androidcleanarchitectureboilerplate.di;


import com.kienht.domain.di.scheduler.RunOn;
import com.kienht.domain.di.scheduler.SchedulerType;

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

@Module(includes = AndroidInjectionModule.class)
abstract class SchedulerModule {

    @Singleton
    @Provides
    @RunOn(value = SchedulerType.IO)
    static Scheduler schedulerIO() {
        return Schedulers.io();
    }

    @Singleton
    @Provides
    @RunOn(value = SchedulerType.COMPUTATION)
    static Scheduler schedulerComputation() {
        return Schedulers.computation();
    }

    @Singleton
    @Provides
    @RunOn(value = SchedulerType.UI)
    static Scheduler schedulerUI() {
        return AndroidSchedulers.mainThread();
    }
}
