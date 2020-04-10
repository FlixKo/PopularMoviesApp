package com.example.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView mTitle = findViewById(R.id.tv_movie_title);
        TextView mYear = findViewById(R.id.tv_movie_year);
        TextView mDescription = findViewById(R.id.tv_movie_description);
        TextView mRating = findViewById(R.id.tv_movie_rating);
        ImageView mPoster = findViewById(R.id.iv_movie_poster);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                Movie mMovie = Objects.requireNonNull(intentThatStartedThisActivity.getExtras()).getParcelable("movie");
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
            }
        }
    }
}
