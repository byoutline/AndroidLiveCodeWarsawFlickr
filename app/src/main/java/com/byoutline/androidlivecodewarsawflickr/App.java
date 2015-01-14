package com.byoutline.androidlivecodewarsawflickr;

import android.app.Application;

import timber.log.Timber;

import static timber.log.Timber.plant;

/**
 * Created by nait on 10.01.15.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            plant(new Timber.DebugTree());
        }
    }
}
