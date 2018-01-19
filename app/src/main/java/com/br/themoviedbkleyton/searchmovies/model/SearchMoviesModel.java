package com.br.themoviedbkleyton.searchmovies.model;

import com.br.themoviedbkleyton.home.model.GenreAndMoviesResponse;
import com.br.themoviedbkleyton.moviedetail.model.MovieDetail;

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

public class SearchMoviesModel extends BaseSync {

    private SyncInterface syncInterface;
    private Call<GenreAndMoviesResponse> call;
    private String query;
    private List<MovieDetail> movieDetailList;

    public SearchMoviesModel(SyncInterface syncInterface, String query, List<MovieDetail> movieDetailList) {
        this.syncInterface = syncInterface;
        this.query = query;
        this.movieDetailList = movieDetailList;
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

        ServiceApi serviceApi = RetrofitConfig.getService();

        call = serviceApi.searchMovie(ApiUtils.API_KEY, ApiUtils.API_LANGUAGE, "true", query);

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
