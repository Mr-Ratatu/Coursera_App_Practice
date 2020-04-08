package com.coursera.retrofit.practice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coursera.retrofit.practice.R;
import com.coursera.retrofit.practice.data.model.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.coursera.retrofit.practice.Constant.*;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.FlickrViewHolder> {

    private List<Result> list;

    public MovieAdapter(List<Result> list) {
        this.list = list;
    }

    public void setList(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlickrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flickr_item, parent, false);
        return new FlickrViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlickrViewHolder holder, int position) {
        Result result = list.get(position);

        holder.bind(result);
    }

    @Override
    public int getItemCount() {
        return list.size(); //Here error
    }

    class FlickrViewHolder extends RecyclerView.ViewHolder {

        private ImageView photoFlickr;
        private TextView title;

        public FlickrViewHolder(@NonNull View itemView) {
            super(itemView);

            photoFlickr = itemView.findViewById(R.id.photo_flickr);
            title = itemView.findViewById(R.id.title_flickr);
        }

        public void bind(Result result) {
            Glide.with(itemView.getContext()).load(BASE_URL_IMG + POSTER_SIZE + result.getPosterPath()).into(photoFlickr);
            title.setText(result.getTitle());
        }

    }
}
