package com.byoutline.androidlivecodewarsawflickr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.byoutline.androidlivecodewarsawflickr.App;
import com.byoutline.androidlivecodewarsawflickr.R;
import com.byoutline.androidlivecodewarsawflickr.adapters.PhotosAdapter;
import com.byoutline.androidlivecodewarsawflickr.events.PhotoFetchFailedEvent;
import com.byoutline.androidlivecodewarsawflickr.events.RecentPhotosFetchedEvent;
import com.byoutline.androidlivecodewarsawflickr.managers.PhotoManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    @Inject
    PhotoManager photoManager;
    @Inject
    Bus bus;
    @Inject
    PhotosAdapter photosAdapter;

    @InjectView(R.id.photos_list_view)
    RecyclerView photoRV;
    @InjectView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        App.doDaggerInject(this);
        ButterKnife.inject(this, rootView);
        setUpAdapters(container.getContext());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                photoManager.photos.refresh();
            }
        });
        return rootView;
    }

    private void setUpAdapters(Context context) {
        photoRV.setLayoutManager(new LinearLayoutManager(context));
        photoRV.setHasFixedSize(true);
        photoRV.setAdapter(photosAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        bus.register(this);
        photoManager.photos.postValue();
    }

    @Override
    public void onPause() {
        bus.unregister(this);
        super.onPause();
    }

    @Subscribe
    public void onPhotosFetched(RecentPhotosFetchedEvent event) {
        photosAdapter.setData(event.getResponse().photos.photo);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe
    public void onPhotosFetchFailed(PhotoFetchFailedEvent event) {
        swipeRefreshLayout.setRefreshing(false);
    }
}
