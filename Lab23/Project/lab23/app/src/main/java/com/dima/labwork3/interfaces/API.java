package com.dima.labwork3.interfaces;

import com.dima.labwork3.pojo.MovieDetails;
import com.dima.labwork3.pojo.MoviePageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("/?type=movie")
    Call<MoviePageModel> getPopularMovies(@Query("apikey") String key,@Query("s") String param,
                                          @Query("page") int page);

    @GET("/")
    Call<MovieDetails> getMovieDetails(@Query("apikey") String key,@Query("i") String id);

}