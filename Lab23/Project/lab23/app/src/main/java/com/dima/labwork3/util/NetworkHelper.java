package com.dima.labwork3.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dima.labwork3.pojo.MovieDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NetworkHelper {

    public static final String BASE_URL = "https://www.omdbapi.com";

    public interface MovieListLoadCallback {
        void onLoadFail(Call call);

        void onLoadSuccess(Response response, List<MovieDetails> movieModels);
    }

    public interface MovieDetailsLoadCallback {
        void onLoadFail(Call call);

        void onLoadSuccess(Response response, MovieDetails movieDetails);
    }

    public static boolean isInternetUnavailable(Context context) {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return true;
        }
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo == null || !networkInfo.isConnected();
    }
}