package com.kienht.androidcleanarchitectureboilerplate.di;

import com.kienht.androidcleanarchitectureboilerplate.BuildConfig;
import com.kienht.remote.OICService;
import com.kienht.remote.OICServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */

@Module
abstract class NetworkModule {

    @Singleton
    @Provides
    static OICService oicService() {
        return new OICServiceFactory().makeOICService(BuildConfig.DEBUG);
    }

}
