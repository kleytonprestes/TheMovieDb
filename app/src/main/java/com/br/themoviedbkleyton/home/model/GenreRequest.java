package com.br.themoviedbkleyton.home.model;

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
 * Created by kleyton on 16/01/18.
 */

public class GenreRequest extends BaseSync {

    private Call<GenreResponse> repo;
    private SyncInterface syncInterface;
    private List<Genre> genreList;


    public GenreRequest(SyncInterface syncInterface, List<Genre> genreList) {
        this.syncInterface = syncInterface;
        this.genreList = genreList;
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

        repo = serviceApi.getGenre(ApiUtils.API_KEY, ApiUtils.API_LANGUAGE);

        repo.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                        GenreResponse genreResponse = response.body();

                    if (genreResponse != null) {
                        genreList.addAll(genreResponse.getGenres());
                    }

                    onSuccessSync();

                } else {
                    onFailureSync();
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                onFailureSync();
            }
        });
    }
}
