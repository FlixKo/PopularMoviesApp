package com.example.popularmoviesapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesapp.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private final String LOG_TAG = ReviewAdapter.class.getName();
    private List<Review> mReviews;

    void setReviewList(List<Review> reviews) {
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(parent.getContext());
        View myView = myInflater.inflate(R.layout.reviews, parent, false);
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
