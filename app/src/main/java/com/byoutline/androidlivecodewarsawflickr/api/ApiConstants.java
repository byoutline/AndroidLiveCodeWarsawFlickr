package com.byoutline.androidlivecodewarsawflickr.api;

/**
 * Created by nait on 10.01.15.
 */
public class ApiConstants {
    private static final String REAL_HOST = "https://api.flickr.com/services/rest/";
    private static final String MOCK_HOST = "http://localhost:8099";
    public static final boolean MOCK_ENABLED = true;
    public static final String API_KEY = FlickrKey.SECRET;

    public static String getApiPath() {
//        if(BuildConfig.DEBUG) {
//            return MOCK_ENABLED ? MOCK_HOST : REAL_HOST;
//        }
        return REAL_HOST;
    }
}
