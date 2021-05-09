package com.fatihbaser.javameals.network;



import com.fatihbaser.javameals.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("Fatih-Baser/mealsjson/master/db.json")
    Call<List<MovieModel>> getMovieList();
}
