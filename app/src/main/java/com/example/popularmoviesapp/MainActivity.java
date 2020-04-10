package com.example.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private final String LOG_TAG = MainActivity.class.getName();
    private static final String POPULARITY_DESCENDING  = "popularity.desc";
    private static final String BEST_RATED_DESCENDING = "vote_average.desc";
    private static final String VOTE_COUNT_DESCENDING = "vote_count.desc";
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;

    RecyclerView recyclerView;
    MovieAdapter mAdapter;

    public MainActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        ArrayList<Movie> emptyList = new ArrayList<>();
        mAdapter = new MovieAdapter(this, this, emptyList);
        recyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        makeMovieDbSearchQuery(POPULARITY_DESCENDING);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_order, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_best_rated) {
            makeMovieDbSearchQuery(BEST_RATED_DESCENDING);
            Toast.makeText(this,"Best Rated",Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.menu_most_popular) {
            makeMovieDbSearchQuery(VOTE_COUNT_DESCENDING);
            Toast.makeText(this,"Most Popular",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = DetailsActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("movie", movie);
        startActivity(intentToStartDetailActivity);
    }


    private void makeMovieDbSearchQuery(String searchQuery) {

        URL movieDbUrl = NetworkUtils.buildUrl(searchQuery);
        Log.d(LOG_TAG, "Movie DB Search URL: " + movieDbUrl);
        new MovieDbQueryTask().execute(movieDbUrl);
    }
    private void showErrorMessage() {
        recyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void showJsonDataView(String searchResult) throws JSONException {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mAdapter = new MovieAdapter(this, this,JsonUtils.extractMovieFromJSON(searchResult));
        recyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setVisibility(View.VISIBLE);
    }

    public class MovieDbQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String movieDbResults = null;
            try {
                movieDbResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
               Log.e(LOG_TAG, "Error fetching movies!!", e);
                showErrorMessage();
            }
            return movieDbResults;
        }

        @Override
        protected void onPostExecute(String movieDbSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieDbSearchResults != null && !movieDbSearchResults.equals("")) {
                try {
                    showJsonDataView(movieDbSearchResults);
                } catch (JSONException e) {
                    showErrorMessage();
                }
            } else {
                showErrorMessage();
            }
        }
    }
}
