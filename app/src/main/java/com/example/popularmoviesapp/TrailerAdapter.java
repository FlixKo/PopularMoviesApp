package com.example.popularmoviesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.popularmoviesapp.models.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    private final String LOG_TAG = TrailerAdapter.class.getName();
    private final TrailerAdapter.TrailerAdapterOnClickHandler mTrailerItemClickListener;
    private List<Trailer> mTrailer;

    TrailerAdapter(TrailerAdapterOnClickHandler trailerItemClickListener) {
        mTrailerItemClickListener = trailerItemClickListener;
    }

    void setTrailerList(List<Trailer> trailer) {
        mTrailer = trailer;
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(parent.getContext());
        View myView = myInflater.inflate(R.layout.trailer, parent, false);
        return new TrailerHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder holder, int position) {
        holder.myTextView.setText(mTrailer.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mTrailer != null ? mTrailer.size() : 0;
    }

    public interface TrailerAdapterOnClickHandler {
        void onClick(Trailer trailer);
    }

    public class TrailerHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView myTextView;

        public TrailerHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.trailer_element);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Trailer currentTrailer = mTrailer.get(adapterPosition);
            mTrailerItemClickListener.onClick(currentTrailer);
        }
    }

}
