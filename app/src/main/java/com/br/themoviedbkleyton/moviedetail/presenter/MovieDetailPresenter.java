package com.br.themoviedbkleyton.moviedetail.presenter;

import com.br.themoviedbkleyton.moviedetail.contract.MovieDetailContract;
import com.br.themoviedbkleyton.moviedetail.model.MovieDetail;
import com.br.themoviedbkleyton.moviedetail.model.RequestSimilarMovies;

import java.util.ArrayList;
import java.util.List;

import base.api.SyncInterface;

/**
 * Created by kleyton on 16/01/18.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter, SyncInterface {

    private MovieDetailContract.View view;
    private List<MovieDetail> movieDetailList = new ArrayList<>();


    @Override
    public void attachView(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void requestSimilar(MovieDetail movieDetail) {
        RequestSimilarMovies requestSimilarMovies = new RequestSimilarMovies(this,
                movieDetailList, movieDetail);
        requestSimilarMovies.startSync();
    }

    @Override
    public void onSuccessSync() {
        view.setAdapter(movieDetailList);
    }

    @Override
    public void onFailureSync() {

    }
}
