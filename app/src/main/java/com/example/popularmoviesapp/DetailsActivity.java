package com.example.popularmoviesapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    // TODO!
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView mTitle = findViewById(R.id.tv_movie_title);
        TextView mYear = findViewById(R.id.tv_movie_year);
        TextView mDescription = findViewById(R.id.tv_movie_description);
        TextView mRating = findViewById(R.id.tv_movie_rating);
        ImageView mPoster = findViewById(R.id.iv_movie_poster);
        Button mFavorite = findViewById(R.id.bt_favorite);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                final Movie mMovie = Objects.requireNonNull(intentThatStartedThisActivity.getExtras()).getParcelable("movie");
                mTitle.setText(Objects.requireNonNull(mMovie).getTitle());
                mYear.setText(mMovie.getYear());
                mDescription.setText(mMovie.getDescription());
                mRating.setText(mMovie.getRating());
                //mPoster.setImageResource(mMovie.getImage());

                Picasso.get()
                        .load(mMovie.getImage())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.image_not_found)
                        .into(mPoster);

                mFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Favorite: " + Objects.requireNonNull(mMovie).getTitle(), Toast.LENGTH_LONG).show();
                        // store movie in DB
                    }
                });
            }
        }
    }
}
