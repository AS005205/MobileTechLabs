package com.dima.labwork3.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class MovieDetails {

    @SerializedName("imdbID")
    @Expose
    @NonNull
    @PrimaryKey
    private String id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("Poster")
    @Expose
    private String posterPath;

    public MovieDetails() {
    }

    public MovieDetails(@NonNull String id, String title, String year, String type, String plot, String posterPath) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.type = type;
        this.plot = plot;
        this.posterPath = posterPath;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}