package com.example.popularmoviesapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesapp.database.MovieDatabase;
import com.example.popularmoviesapp.models.Movie;
import com.example.popularmoviesapp.models.MovieViewModel;
import com.example.popularmoviesapp.utilities.FetchMoviesFromNetwork;
import com.example.popularmoviesapp.utilities.JsonUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private final String LOG_TAG = MainActivity.class.getName();
    MovieViewModel movieViewModel;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessageDisplay;
    private RecyclerView recyclerView;
    private ScrollView scrollView;
    private MovieAdapter mAdapter;
    private MovieDatabase mDb;

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

        movieViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
                getApplication()).create(MovieViewModel.class);

        mDb = MovieDatabase.getInstance(getApplicationContext());

        ArrayList<Movie> emptyList = new ArrayList<>();
        mAdapter = new MovieAdapter(this, this, emptyList);
        recyclerView.setAdapter(mAdapter);
        GridLayoutManager gridLayoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            gridLayoutManager = new GridLayoutManager(this, 2);
        } else {
            gridLayoutManager = new GridLayoutManager(this, 3);
        }
        recyclerView.setLayoutManager(gridLayoutManager);


        if (isConnected(this)) {
            FetchMoviesFromNetwork.getPopularMovies(getApplicationContext());
            FetchMoviesFromNetwork.getTopRatedMovies(getApplicationContext());
            getPopularMovies();

            //TODO ??
            //getTopRatedMovies();
        } else {
            showNoNetworkErrorMessage();
        }

    }

    private void getPopularMovies() {
        movieViewModel.getPopularMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                setTitle(R.string.popular_movies);
                mAdapter.setMovieList(new ArrayList<>(movies));
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getTopRatedMovies() {
        movieViewModel.getTopRatedMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                setTitle(R.string.best_rated);
                mAdapter.setMovieList(new ArrayList<>(movies));
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void getFavoriteMovies() {
        movieViewModel.getFavouriteMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                setTitle(R.string.fav_movies);
                mAdapter.setMovieList(new ArrayList<>(movies));
                mAdapter.notifyDataSetChanged();
            }
        });
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
            getTopRatedMovies();
            return true;
        } else if (id == R.id.menu_most_popular) {
            getPopularMovies();
            return true;
        } else if (id == R.id.menu_favorites) {
            getFavoriteMovies();
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

    @Override
    protected void onResume() {
        super.onResume();
    }
}
