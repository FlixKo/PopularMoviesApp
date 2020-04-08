package com.example.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {
    Movie test1 = new Movie("https://image.tmdb.org/t/p/w185//xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg", "2010", "Test1", "123min", "8/10", "Lorem Ipsum");
    Movie test2 = new Movie("https://image.tmdb.org/t/p/w185//xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg", "2010", "Test2", "123min", "8/10", "Lorem Ipsum Bladida");
    ArrayList<Movie> movies  = new ArrayList<>();

    //ArrayList<Movie> movies = JsonUtils.extractMovieFromJSON(getString(R.string.movies));

    RecyclerView recyclerView;
    MovieAdapter mAdapter;
    private final String api_key = "51ef7ace762b5d09cd2b2a5930bfde3c";

    public MainActivity() throws JSONException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        movies.add(test1);
        movies.add(test2);

        Picasso.get().setLoggingEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        mAdapter = new MovieAdapter(this, this,movies);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Picasso.Builder builder = new Picasso.Builder(getApplicationContext());
        builder.listener(new Picasso.Listener() {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Use AppCompatActivity's method getMenuInflater to get a handle on the menu inflater */
        MenuInflater inflater = getMenuInflater();
        /* Use the inflater's inflate method to inflate our menu layout to this menu */
        inflater.inflate(R.menu.sort_order, menu);
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_best_rated) {
            Toast.makeText(this,"Best Rated",Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.menu_most_popular) {
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

}
