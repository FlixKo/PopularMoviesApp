package com.example.popularmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder>{

    private final ArrayList<Review> mReviews;
    private final String LOG_TAG = ReviewAdapter.class.getName();

    private final Context ctx;
    public ReviewAdapter(ArrayList<Review> mReviews, Context ctx) {
        this.mReviews = mReviews;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View myView = myInflater.inflate(R.layout.reviews, parent,false);
        return new ReviewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewHolder holder, int position) {
        Log.d(LOG_TAG, mReviews.get(position).getContent());
        holder.myTextView.setText(mReviews.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mReviews != null ? mReviews.size() : 0;
    }

    public class ReviewHolder extends RecyclerView.ViewHolder {

        final TextView myTextView;
        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.review_element);
        }
    }
}
