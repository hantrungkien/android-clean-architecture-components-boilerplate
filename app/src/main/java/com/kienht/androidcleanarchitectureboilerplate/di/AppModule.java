/*
 * Copyright 2018 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kienht.androidcleanarchitectureboilerplate.di;

import android.app.Application;

import com.kienht.androidcleanarchitectureboilerplate.features.home.HomeActivity;
import com.kienht.androidcleanarchitectureboilerplate.OICApplication;
import com.kienht.androidcleanarchitectureboilerplate.di.scope.PerActivity;
import com.kienht.androidcleanarchitectureboilerplate.features.home.module.HomeActivityModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */

@Module(includes = AndroidInjectionModule.class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract Application application(OICApplication app);

    @PerActivity
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivityInjector();



}
