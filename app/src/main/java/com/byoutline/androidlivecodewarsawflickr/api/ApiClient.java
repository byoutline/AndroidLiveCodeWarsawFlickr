package com.byoutline.androidlivecodewarsawflickr.api;

import com.byoutline.androidlivecodewarsawflickr.model.FlickrResponse;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Sebastian Kacprzak on 10.01.15.
 */
public interface ApiClient {
    @GET("/?method=flickr.photos.getRecent")
    FlickrResponse getRandom(@Query("extras") String extras);
}
