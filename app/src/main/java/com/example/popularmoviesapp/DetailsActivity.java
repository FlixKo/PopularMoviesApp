package com.example.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private TextView mTitle;
    private TextView mDescription;
    private ImageView mPoster;
    private TextView mRating;
    private TextView mYear;

    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mTitle = findViewById(R.id.tv_movie_title);
        mYear = findViewById(R.id.tv_movie_year);
        mDescription = findViewById(R.id.tv_movie_description);
        mRating = findViewById(R.id.tv_movie_rating);
        mPoster = findViewById(R.id.iv_movie_poster);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("movie")) {
                mMovie = intentThatStartedThisActivity.getExtras().getParcelable("movie");
                mTitle.setText(mMovie.getTitle());
                mYear.setText(mMovie.getYear());
                mDescription.setText(mMovie.getDescription());
                mRating.setText(mMovie.getRating());
                //mPoster.setImageResource(mMovie.getImage());

                Picasso.get()
                        .load(mMovie.getImage())
                        .placeholder(R.drawable.placeholder)
                        //.error(R.drawable.user_placeholder_error)
                        .into(mPoster);
                //Picasso.get().load(mMovie.getImage()).into(mPoster);
            }
        }
    }
}
