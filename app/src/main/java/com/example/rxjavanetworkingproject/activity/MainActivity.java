package com.example.rxjavanetworkingproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.rxjavanetworkingproject.R;
import com.example.rxjavanetworkingproject.adapter.MoviesAdapter;
import com.example.rxjavanetworkingproject.model.Movie;
import com.example.rxjavanetworkingproject.model.MovieResponse;
import com.example.rxjavanetworkingproject.rest.ApiClient;
import com.example.rxjavanetworkingproject.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String API_KEY = "deec65e29ee2d92397b3084ca4871fa5";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMoview(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Number of movies received:" + movies.size());
                recyclerView.setAdapter(new MoviesAdapter(movies,R.layout.list_item_movie,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
