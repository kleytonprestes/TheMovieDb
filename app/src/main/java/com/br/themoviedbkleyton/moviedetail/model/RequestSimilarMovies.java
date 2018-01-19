package com.br.themoviedbkleyton.moviedetail.model;

import com.br.themoviedbkleyton.home.model.GenreAndMoviesResponse;

import java.util.List;

import base.api.ApiUtils;
import base.api.BaseSync;
import base.api.RetrofitConfig;
import base.api.ServiceApi;
import base.api.SyncInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kleyton on 17/01/18.
 */

public class RequestSimilarMovies extends BaseSync {

    private SyncInterface syncInterface;
    private List<MovieDetail> movieDetailList;
    private MovieDetail movieDetail;

    private ServiceApi serviceApi;
    private Call<GenreAndMoviesResponse> call;

    public RequestSimilarMovies(SyncInterface syncInterface, List<MovieDetail> movieDetailList, MovieDetail movieDetail) {
        this.syncInterface = syncInterface;
        this.movieDetailList = movieDetailList;
        this.movieDetail = movieDetail;
    }

    @Override
    public void onSuccessSync() {
        syncInterface.onSuccessSync();
    }

    @Override
    public void onFailureSync() {
        syncInterface.onFailureSync();
    }

    @Override
    public void startSync() {

        serviceApi = RetrofitConfig.getService();

        call = serviceApi.getSimilarMovie(String.valueOf(movieDetail.getId()),
                ApiUtils.API_KEY, ApiUtils.API_LANGUAGE, "1" );

        call.enqueue(new Callback<GenreAndMoviesResponse>() {
            @Override
            public void onResponse(Call<GenreAndMoviesResponse> call, Response<GenreAndMoviesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    GenreAndMoviesResponse genreAndMoviesResponse = response.body();
                    if (genreAndMoviesResponse != null) {
                        movieDetailList.addAll(genreAndMoviesResponse.getResults());
                    }
                    onSuccessSync();
                } else {
                    onFailureSync();
                }
            }

            @Override
            public void onFailure(Call<GenreAndMoviesResponse> call, Throwable t) {
                onFailureSync();
            }
        });
    }
}
