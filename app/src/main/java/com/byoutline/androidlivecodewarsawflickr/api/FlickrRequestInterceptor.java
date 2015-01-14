package com.byoutline.androidlivecodewarsawflickr.api;

import retrofit.RequestInterceptor;

/**
 * Created by nait on 10.01.15.
 */
public class FlickrRequestInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("format", "json");
        request.addQueryParam("api_key", ApiConstants.API_KEY);
        request.addQueryParam("nojsoncallback", "1");
    }
}
