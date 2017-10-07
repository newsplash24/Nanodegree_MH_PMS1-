package com.nanodegree.mh.pms1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nanodegree.mh.pms1.data.Movie;
import com.nanodegree.mh.pms1.utilites.MoviesJsonUtiles;
import com.nanodegree.mh.pms1.utilites.NetworkUtilites;
import com.nanodegree.mh.pms1.R;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.onItemClickLisnter {

    private GridView mMoviesGrid;
    private Movie[] mMovies;
    private MoviesAdapter mMoviesAdapter;
    private ProgressBar mProgressBar;
    public static final String EXTRA_MOVIE = "extra_movie";
    private Toast mToast;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = (ProgressBar) findViewById(R.id.progressDialog);

        mMovies = new Movie[30];
        mMoviesAdapter = new MoviesAdapter(this, R.layout.movies_grid_item, mMovies, this);
        mMoviesGrid = (GridView) findViewById(R.id.moviesGrid);
        mMoviesGrid.setAdapter(mMoviesAdapter);

        FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
        fetchMoviesTask.execute(getString(R.string.param_most_popular));

    }

    public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]> {

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Movie[] doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            String category = params[0];
            URL moviesRequestUrl = NetworkUtilites.buildUrl(MainActivity.this, category);

            try {
                String jsonMoviesResponse = NetworkUtilites
                        .getResponseDataFromHttpUrl(moviesRequestUrl);

                Movie[] moviesData = MoviesJsonUtiles
                        .getMoviesFromJson(MainActivity.this, jsonMoviesResponse);

                return moviesData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] moviesData) {
            mMovies = moviesData;
            mProgressBar.setVisibility(View.INVISIBLE);
            if(moviesData != null) {
                MoviesAdapter mMoviesAdapter = new MoviesAdapter(
                        MainActivity.this,
                        R.layout.movies_grid_item,
                        moviesData, MainActivity.this);
                mMoviesGrid.setAdapter(mMoviesAdapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        this.menu = menu;
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        MenuItem category = menu.findItem(R.id.action_category);
        switch (id) {
            case R.id.action_most_popular: {
                FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
                String cat = getString(R.string.param_most_popular);
                fetchMoviesTask.execute(cat);
                category.setTitle(getString(R.string.most_popular));
                break;
            }

            case R.id.action_top_rated: {
                FetchMoviesTask fetchMoviesTask = new FetchMoviesTask();
                String cat = getString(R.string.param_top_rated);
                fetchMoviesTask.execute(cat);
                category.setTitle(getString(R.string.top_rated));
                break;
            }
            case R.id.action_category:{
                // It doesn't work :(
                MainActivity.this.openOptionsMenu();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClicked(int indx) {

        if(mMovies != null) {
            Movie clickedMovie = mMovies[indx];
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(EXTRA_MOVIE, clickedMovie);
            startActivity(intent);
        }
        else {

            if(mToast != null)
                mToast.cancel();
            mToast = Toast.makeText(this, "Make sure you connected and try again", Toast.LENGTH_LONG);
            mToast.show();
        }
    }

}
