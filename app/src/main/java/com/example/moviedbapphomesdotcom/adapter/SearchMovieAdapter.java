package com.example.moviedbapphomesdotcom.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviedbapphomesdotcom.R;
import com.example.moviedbapphomesdotcom.models.SearchMovies;
import com.example.moviedbapphomesdotcom.models.TopMovies;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sid on 09-06-2017.
 */

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.ViewHolder> {
    private Context mContext;
    private List<SearchMovies> mSearchMovie;
    // Define listener member variable
    private static SearchMovieAdapter.OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(SearchMovieAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    // Pass in the contact array into the constructor
    public SearchMovieAdapter(Context context, List<SearchMovies> SearchMovie){
        mSearchMovie = SearchMovie;
        mContext = context;
    }
    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }
    // Your holder should contain a member variable
    // for any view that will be set as you render a row

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Store the context for easy access
        public TextView tempTextView;
        public ImageView medianimageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(final View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            tempTextView = (TextView) itemView.findViewById(R.id.textViewRVSearchMovie);
            medianimageView = (ImageView) itemView.findViewById(R.id.imageViewRVSearchMovie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public SearchMovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.search_movie_recycler, parent, false);

        // Return a new holder instance
        SearchMovieAdapter.ViewHolder viewHolder = new SearchMovieAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchMovieAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        SearchMovies current = mSearchMovie.get(position);

        // Set item views based on your views and data model
        TextView temptextView = viewHolder.tempTextView;
        temptextView.setText(current.getTitle());
        ImageView icon = viewHolder.medianimageView;
        Picasso.with(mContext).load(current.getImgpath()).into(icon);
    }

    @Override
    public int getItemCount() {
        return mSearchMovie.size();
    }
}

