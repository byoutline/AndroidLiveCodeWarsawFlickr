package com.byoutline.androidlivecodewarsawflickr;

import android.app.Application;

import com.byoutline.androidstubserver.AndroidStubServer;
import com.byoutline.mockserver.NetworkType;
import com.byoutline.ottocachedfield.OttoCachedField;
import com.squareup.otto.Bus;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import dagger.ObjectGraph;
import timber.log.Timber;

import static timber.log.Timber.plant;

/**
 * Created by Sebastian Kacprzak on 10.01.15.
 */
public class App extends Application {
    private static ObjectGraph objectGraph;
    @Inject
    Bus bus;
    @Inject
    @Named("sessionIdProvider")
    Provider<String> sessionIdProvider;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            AndroidStubServer.start(this, NetworkType.EDGE);
        }

        objectGraph = ObjectGraph.create(new AppModule(this));
        if (BuildConfig.DEBUG) {
            plant(new Timber.DebugTree());
        }
        doDaggerInject(this);
        OttoCachedField.init(sessionIdProvider, bus);
    }

    public static void doDaggerInject(Object o) {
        objectGraph.inject(o);
    }
}
