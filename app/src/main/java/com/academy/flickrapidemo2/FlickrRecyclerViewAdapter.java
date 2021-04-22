package com.academy.flickrapidemo2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrRecyclerViewAdapter.FlickrImageViewHolder>
      implements AdapterView.OnItemClickListener    {
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private List<Photo> mPhotoList;
    private Context mContext;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext,"Title : " + mPhotoList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
    }

    public FlickrRecyclerViewAdapter(List<Photo> mPhotoList, Context mContext) {
        this.mPhotoList = mPhotoList;
        this.mContext =  mContext;
    }

    static public class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickrImageViewHolder";
        private final TextView tvTitle;
        private final ImageView ivThumb;

        public FlickrImageViewHolder(View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.title);
            this.ivThumb = itemView.findViewById(R.id.thumbnail);
        }
    }
    @NonNull
    @Override
    public FlickrImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.browse, parent, false);
        FlickrRecyclerViewAdapter.FlickrImageViewHolder viewHolder = new
                FlickrRecyclerViewAdapter.FlickrImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.academy.flickrapidemo2.FlickrRecyclerViewAdapter.FlickrImageViewHolder holder, int position) {
        Photo photo = mPhotoList.get(position);
        holder.tvTitle.setText(photo.getTitle());
        String photoLink = photo.getImage();
        Picasso.with(mContext).load(photo.getImage())
                              .error(R.drawable.placeholder)
                              .placeholder(R.drawable.placeholder)
                              .into(holder.ivThumb);
    }
    void loadNewData(List<Photo> newPhotos) {
        this.mPhotoList = newPhotos;
        notifyDataSetChanged();
    }
    public Photo getPhoto(int position) {
        return (mPhotoList != null && mPhotoList.size() != 0)?mPhotoList.get(position):null;
    }
    @Override
    public int getItemCount() {
        return (mPhotoList != null && mPhotoList.size() != 0) ? (mPhotoList.size()):0;
    }
}
