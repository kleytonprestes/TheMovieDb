package com.br.themoviedbkleyton.moviedetail.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kleyton on 16/01/18.
 */

public class SimilarMovie {

    @SerializedName("results")
    private ArrayList<MovieDetail> similarMovies;

    public ArrayList<MovieDetail> getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(ArrayList<MovieDetail> similarMovies) {
        this.similarMovies = similarMovies;
    }
}
