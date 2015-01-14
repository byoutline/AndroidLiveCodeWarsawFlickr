package com.byoutline.androidlivecodewarsawflickr.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nait on 11.01.15.
 */
public class Photos {
    public Integer page;
    public Integer pages;
    public Integer perpage;
    public Integer total;
    public List<Photo> photo = new ArrayList<Photo>();
}
