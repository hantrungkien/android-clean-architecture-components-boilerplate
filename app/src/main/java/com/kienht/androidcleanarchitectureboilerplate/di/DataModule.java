package com.kienht.androidcleanarchitectureboilerplate.di;

import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import com.kienht.androidcleanarchitectureboilerplate.OICApplication;
import com.kienht.cache.database.RoomDB;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

import static com.kienht.cache.database.constants.DBConstants.DATABASE_NAME;

/**
 * Note:
 * Created by kienht on 4/30/18.
 */

@Module(includes = AndroidInjectionModule.class)
abstract class DataModule {

    @Singleton
    @Provides
    @NonNull
    static RoomDB roomDB(OICApplication demoApplication) {
        return Room.databaseBuilder(demoApplication, RoomDB.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
