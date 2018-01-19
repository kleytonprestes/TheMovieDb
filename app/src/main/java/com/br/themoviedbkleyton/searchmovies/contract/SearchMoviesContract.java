package com.br.themoviedbkleyton.searchmovies.contract;


import com.br.themoviedbkleyton.moviedetail.model.MovieDetail;

import java.util.List;

import base.api.SyncInterface;
import base.basecontract.BaseContract;

/**
 * Created by kleyton on 17/01/18.
 */

public class SearchMoviesContract {
    public interface View extends BaseContract.View {


    }

    public interface Presenter extends BaseContract.Presenter<View> {
        void RequestSearchMovies(SyncInterface syncInterface, String query, List<MovieDetail> movieDetailList);
    }
}
