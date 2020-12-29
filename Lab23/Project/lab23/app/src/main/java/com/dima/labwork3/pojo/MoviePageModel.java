package com.dima.labwork3.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MoviePageModel implements Serializable {

    @SerializedName("totalResults")
    private String page;
    @SerializedName("Search")
    private ArrayList<MovieDetails> movieModel;

    public MoviePageModel(ArrayList<MovieDetails> movieModel) {
        this.movieModel = movieModel;
    }

    public ArrayList<MovieDetails> getMoviesList() {
        return movieModel;
    }

}