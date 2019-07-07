package com.example.rxjavanetworkingproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavanetworkingproject.R;
import com.example.rxjavanetworkingproject.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.data.setText(movies.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviesLayout = itemView.findViewById(R.id.movies_layout);
            data = itemView.findViewById(R.id.subtitle);
            movieTitle = itemView.findViewById(R.id.title);
            movieDescription = itemView.findViewById(R.id.description);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
