package com.fatihbaser.javameals.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.fatihbaser.javameals.DetailFragment;
import com.fatihbaser.javameals.FeedFragment;
import com.fatihbaser.javameals.R;
import com.fatihbaser.javameals.model.MovieModel;

import java.util.List;

public class MovieListAdapter  extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private Context context;
    private List<MovieModel> movieList;
    private ItemClickListener clickListener;

    private  LayoutInflater layoutInflater;
    public MovieListAdapter(Context context, List<MovieModel> movieList, ItemClickListener clickListener) {
        this.context = context;
        this.movieList = movieList;
        this.clickListener = clickListener;
    }

    public void setMovieList(List<MovieModel> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater=LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                clickListener.onMovieClick(movieList.get(position));

            }
        });
        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(this.movieList != null) {
            return this.movieList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.titleView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);

        }
    }


    public interface ItemClickListener{
        public void onMovieClick(MovieModel movie);
    }
}

