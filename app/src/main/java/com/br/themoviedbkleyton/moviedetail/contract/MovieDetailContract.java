package com.br.themoviedbkleyton.moviedetail.contract;

import com.br.themoviedbkleyton.moviedetail.model.MovieDetail;

import java.util.ArrayList;
import java.util.List;

import base.basecontract.BaseContract;

/**
 * Created by kleyton on 16/01/18.
 */

public class MovieDetailContract {

    public interface View extends BaseContract.View {

        void setAdapter(List<MovieDetail> movieDetailList);
    }

    public interface Presenter extends BaseContract.Presenter<View> {
        void requestSimilar(MovieDetail movieDetail);
    }
}
