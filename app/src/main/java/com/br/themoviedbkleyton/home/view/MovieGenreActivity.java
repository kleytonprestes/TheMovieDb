package com.br.themoviedbkleyton.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.br.themoviedbkleyton.R;
import com.br.themoviedbkleyton.home.contract.MovieGenreContract;
import com.br.themoviedbkleyton.home.model.GenreAndMoviesResponse;
import com.br.themoviedbkleyton.home.presenter.MovieGenrePresenter;

import java.util.ArrayList;

import base.baseactivity.BaseActivity;

/**
 * Created by kleyton on 16/01/18.
 */

public class MovieGenreActivity extends BaseActivity implements MovieGenreContract.View {

    private MovieGenreContract.Presenter presenter = new MovieGenrePresenter();

    private MovieGenreAdapter mAdapter;

    private RecyclerView categoryList;

    private RecyclerView.LayoutManager mLayoutManager;

    private Toolbar toolbar;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_category_list);

        initViews();
        requestGenres();
    }


    private void initViews() {
        presenter.attachView(this);
        categoryList = findViewById(R.id.category_list);
        progressBar = findViewById(R.id.progress);
        configToolbar();

    }

    private void configToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private void requestGenres() {
        presenter.requestGenres();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setAdapter(ArrayList<GenreAndMoviesResponse> genreAndMovieList) {
        mAdapter = new MovieGenreAdapter(this, genreAndMovieList);
        mLayoutManager = new LinearLayoutManager(this);
        categoryList.setLayoutManager(mLayoutManager);
        categoryList.setAdapter(mAdapter);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        setUpSearchView(menu);
        return true;
    }
}
