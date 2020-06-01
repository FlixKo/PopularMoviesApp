package com.example.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.popularmoviesapp.utilities.JsonUtils;
import com.example.popularmoviesapp.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private final String LOG_TAG = MainActivity.class.getName();
    private static final String POPULARITY_DESCENDING = "popularity.desc";
    private static final String BEST_RATED_DESCENDING = "vote_average.desc";
    private static final String VOTE_COUNT_DESCENDING = "vote_count.desc";
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;

    private RecyclerView recyclerView;
    private ScrollView scrollView;
    private MovieAdapter mAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = findViewById(R.id.scroll_view);
        recyclerView = findViewById(R.id.recycler);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        ArrayList<Movie> emptyList = new ArrayList<>();
        mAdapter = new MovieAdapter(this, this, emptyList);
        recyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            gridLayoutManager = new GridLayoutManager(this, 2);
        }else{
            gridLayoutManager = new GridLayoutManager(this, 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);

        if(isConnected(this)){
            makeMovieDbSearchQuery(POPULARITY_DESCENDING);
        }else{
            showNoNetworkErrorMessage();
        }

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
            if(isConnected(this)){
                makeMovieDbSearchQuery(BEST_RATED_DESCENDING);
            }else{
                showNoNetworkErrorMessage();
            }
            return true;
        } else if (id == R.id.menu_most_popular) {
            if(isConnected(this)){
                makeMovieDbSearchQuery(POPULARITY_DESCENDING);
            }else{
                showNoNetworkErrorMessage();
            }
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
        scrollView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setText(getString(R.string.error_message));
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void showNoNetworkErrorMessage() {
        scrollView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setText(getString(R.string.no_network));
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void showJsonDataView(String searchResult) throws JSONException {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mAdapter = new MovieAdapter(this, this, JsonUtils.extractMovieFromJSON(searchResult));
        recyclerView.setAdapter(mAdapter);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        //recyclerView.setLayoutManager(gridLayoutManager);
        scrollView.setVisibility(View.VISIBLE);
    }
    private boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    return (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) ||
                            (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE);
                }
            }
        }
        return false;
    }

    class MovieDbQueryTask extends AsyncTask<URL, Void, String> {

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
