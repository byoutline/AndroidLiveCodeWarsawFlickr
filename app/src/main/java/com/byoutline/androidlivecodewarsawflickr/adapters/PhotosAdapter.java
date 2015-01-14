package com.byoutline.androidlivecodewarsawflickr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.byoutline.androidlivecodewarsawflickr.R;
import com.byoutline.androidlivecodewarsawflickr.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by nait on 11.01.15.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private final Provider<Context> contextProvider;
    final List<Photo> data = new ArrayList<>();

    public PhotosAdapter(Provider<Context> contextProvider) {
        this.contextProvider = contextProvider;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        synchronized (this.data) {
            Photo photo = data.get(position);
            Picasso.with(contextProvider.get()).load(photo.url_z).placeholder(R.drawable.placeholder).into(viewHolder.photoIv);
        }
    }

    @Override
    public int getItemCount() {
        synchronized (this.data) {
            return data.size();
        }
    }

    public void setData(List<Photo> data) {
        synchronized (this.data) {
            this.data.clear();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.photo_iv)
        ImageView photoIv;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
