package com.byoutline.androidlivecodewarsawflickr;

import android.content.Context;

import com.byoutline.androidlivecodewarsawflickr.adapters.PhotosAdapter;
import com.byoutline.androidlivecodewarsawflickr.api.FlickrService;
import com.byoutline.androidlivecodewarsawflickr.api.ApiConstants;
import com.byoutline.androidlivecodewarsawflickr.api.FlickrRequestInterceptor;
import com.byoutline.androidlivecodewarsawflickr.fragments.PlaceholderFragment;
import com.byoutline.androidlivecodewarsawflickr.managers.PhotoManager;
import com.byoutline.ottoeventcallback.PostFromAnyThreadBus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;

import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Sebastian Kacprzak on 10.01.15.
 */
@Module(injects = {
        App.class,
        PlaceholderFragment.class
})
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    FlickrService providesApiClient(FlickrRequestInterceptor requestInterceptor, GsonConverter gsonConverter) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint(ApiConstants.getApiPath());

        OkHttpClient clientHttp = new OkHttpClient();
        OkClient client = new OkClient(clientHttp);

        builder.setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setConverter(gsonConverter)
                .setClient(client)
                .setRequestInterceptor(requestInterceptor);
        return builder.build().create(FlickrService.class);
    }

    @Singleton
    @Provides
    FlickrRequestInterceptor providesFlickrRequestInterceptor() {
        return new FlickrRequestInterceptor();
    }

    @Singleton
    @Provides
    PhotoManager providesPhotosManager(FlickrService flickrService) {
        return new PhotoManager(flickrService);
    }

    @Provides
    @Singleton
    public PostFromAnyThreadBus provideBus() {
        return new PostFromAnyThreadBus();
    }

    @Provides
    @Singleton
    public Bus provideBus(PostFromAnyThreadBus bus) {
        return bus;
    }

    @Provides
    @Named("sessionIdProvider")
    public String providesSessionId() {
        return "stub";
    }

    @Provides
    @Singleton
    public GsonConverter providesGsonConverter() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd").create();//'T'HH:mm:ss
        return new GsonConverter(gson);
    }

    @Provides
    @Singleton
    public Context providesApplicationContext() {
        return context;
    }

    @Provides
    public PhotosAdapter providesPhotosAdapter(Provider<Context> contextProvider) {
        return new PhotosAdapter(contextProvider);
    }
}
