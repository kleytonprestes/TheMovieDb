package com.br.themoviedbkleyton.searchmovies.presenter;

import com.br.themoviedbkleyton.moviedetail.contract.MovieDetailContract;
import com.br.themoviedbkleyton.moviedetail.model.MovieDetail;
import com.br.themoviedbkleyton.searchmovies.contract.SearchMoviesContract;
import com.br.themoviedbkleyton.searchmovies.model.SearchMoviesModel;

import java.util.ArrayList;
import java.util.List;

import base.api.SyncInterface;

/**
 * Created by kleyton on 17/01/18.
 */

public class SerchMoviesPresenter implements SearchMoviesContract.Presenter{

    private SearchMoviesModel model;

    private SearchMoviesContract.View view;

    @Override
    public void RequestSearchMovies(SyncInterface syncInterface, String query, List<MovieDetail> movieDetailList) {
        model = new SearchMoviesModel(syncInterface, query, movieDetailList);
        model.startSync();
    }

    @Override
    public void attachView(SearchMoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
