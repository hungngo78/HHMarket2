/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hhmarket.mobile;

import android.app.Application;
import android.util.Log;

import com.hhmarket.mobile.db.AppDatabase;
import com.hhmarket.mobile.db.repository.UserDBRepository;
import com.hhmarket.mobile.di.ComponentInjector;

/**
 * Android Application class. Used for accessing singletons.
 */
public class HHMarketApp extends Application {

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i("HHMarketApp","--------------------------------  App Start ---------------------");

        //mAppExecutors = new AppExecutors();

        ComponentInjector.init();
    }


    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }


    public UserDBRepository getRepository() {
        return UserDBRepository.getInstance(getDatabase());
    }
}
