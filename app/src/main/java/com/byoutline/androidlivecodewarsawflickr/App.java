package com.byoutline.androidlivecodewarsawflickr;

import android.app.Application;

import dagger.ObjectGraph;
import timber.log.Timber;

import static timber.log.Timber.plant;

/**
 * Created by nait on 10.01.15.
 */
public class App extends Application {


    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new AppModule());
        if (BuildConfig.DEBUG) {
            plant(new Timber.DebugTree());
        }
    }

    public static void doDaggerInject(Object o) {
        objectGraph.inject(o);
    }
}
