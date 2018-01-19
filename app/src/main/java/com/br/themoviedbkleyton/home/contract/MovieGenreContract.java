package com.br.themoviedbkleyton.home.contract;

import com.br.themoviedbkleyton.home.model.GenreAndMoviesResponse;

import java.util.ArrayList;

import base.basecontract.BaseContract;

/**
 * Created by kleyton on 16/01/18.
 */

public class MovieGenreContract {

    public interface View extends BaseContract.View {

        void setAdapter(ArrayList<GenreAndMoviesResponse> genreAndMovieList);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void requestGenres();
        void requestMoviesByCategory();
    }
}
