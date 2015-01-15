package com.byoutline.androidlivecodewarsawflickr.managers;

import com.byoutline.androidlivecodewarsawflickr.api.ApiClient;
import com.byoutline.androidlivecodewarsawflickr.events.PhotoFetchFailedEvent;
import com.byoutline.androidlivecodewarsawflickr.events.RecentPhotosFetchedEvent;
import com.byoutline.androidlivecodewarsawflickr.model.FlickrResponse;
import com.byoutline.cachedfield.CachedField;
import com.byoutline.ottocachedfield.OttoCachedField;

import javax.inject.Provider;

/**
 * Created by Sebastian Kacprzak on 10.01.15.
 */
public class PhotoManager {
    private final ApiClient apiClient;

    public final CachedField<FlickrResponse> photos = new OttoCachedField<>(new Provider<FlickrResponse>() {
        @Override
        public FlickrResponse get() {
            return apiClient.getRandom("url_z, url_o, url_s");
        }
    }, new RecentPhotosFetchedEvent(), new PhotoFetchFailedEvent());

    public PhotoManager(ApiClient apiClient) {
        this.apiClient = apiClient;
    }
}
