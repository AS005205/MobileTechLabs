package com.dima.labwork3.util;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.dima.labwork3.interfaces.API;
import com.dima.labwork3.pojo.MovieDetails;
import com.dima.labwork3.pojo.MoviePageModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repo {

    API api = new Retrofit.Builder()
            .baseUrl(NetworkHelper.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build().create(API.class);

    private static final String API_KEY = "819bf43c";

    public void getPopularMovies(int page, NetworkHelper.MovieListLoadCallback callback) {
        new PopularMoviesTask(page, callback).execute();
    }

    private class PopularMoviesTask extends AsyncTask<Void, Void, Void> {

        private int mPage;
        private NetworkHelper.MovieListLoadCallback mCallback;

        PopularMoviesTask(int page, NetworkHelper.MovieListLoadCallback callback) {
            mPage = page;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getPopularMovies(API_KEY,"star wars", mPage).enqueue(new Callback<MoviePageModel>() {
                @Override
                public void onResponse(@NonNull Call<MoviePageModel> call, @NonNull Response<MoviePageModel> response) {
                    Log.d("Logger","Success");
                    Log.d("Logger", response.body().getMoviesList().get(1).getId());
                    mCallback.onLoadSuccess(response, response.body().getMoviesList());

                }

                @Override
                public void onFailure(@NonNull Call<MoviePageModel> call, @NonNull Throwable t) {
                    mCallback.onLoadFail(call);
                    Log.d("Logger","Unsuccessful all");
                    Log.d("Logger", t.getMessage());
                    Log.d("Logger", String.valueOf(call.request().url().encodedQuery()));
                }
            });
            return null;
        }
    }

    public void getMovieDetails(String id, NetworkHelper.MovieDetailsLoadCallback callback) {
        new MovieDetailsTask(id, callback).execute();
    }

    private class MovieDetailsTask extends AsyncTask<Void, Void, Void> {

        private String mId;
        private NetworkHelper.MovieDetailsLoadCallback mCallback;

        MovieDetailsTask(String id, NetworkHelper.MovieDetailsLoadCallback callback) {
            mId = id;
            mCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            api.getMovieDetails(API_KEY,String.valueOf(mId)).enqueue(new Callback<MovieDetails>() {
                @Override
                public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                    Log.d("Logger","Success");
                    Log.d("Logger", String.valueOf(call.request().url()));
                    mCallback.onLoadSuccess(response, response.body());
                }

                @Override
                public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                    Log.d("Logger","Unsuccessful");
                    Log.d("Logger", t.getMessage());
                    Log.d("Logger", String.valueOf(call.request().url().encodedQuery()));
                    mCallback.onLoadFail(call);
                }
            });
            return null;
        }
    }
}