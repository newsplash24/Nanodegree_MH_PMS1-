package com.nanodegree.mh.pms1;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.nanodegree.mh.pms1.data.Movie;
import com.nanodegree.mh.pms1.utilites.NetworkUtilites;
import com.nanodegree.mh.pms1.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by Mohamed on 10/6/2017.
 */

public class MoviesAdapter extends ArrayAdapter {

    private Context context;
    private Movie[] mMovies;

    onItemClickLisnter mOnItemClickLisnter;


    public interface onItemClickLisnter {
        void onItemClicked(int indx);
    }


    public MoviesAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull Movie[] movies,
                         onItemClickLisnter onItemClickLisnter ) {
        super(context, resource, movies);
        this.context = context;
        mMovies = movies;
        mOnItemClickLisnter = onItemClickLisnter;
    }

    @Override
    public int getCount() {
        return mMovies.length;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final ImageView moviePosterIV;
        if(rowView==null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutInflater.inflate(R.layout.movies_grid_item,null);

            moviePosterIV = (ImageView) rowView.findViewById(R.id.moviePosterIV);

            Log.v("TEST", "Before null");
            if(mMovies[position] != null) {
                rowView.setId(mMovies[position].get_ID());
                if (mMovies[position].getPosterPath() != null) {
                    moviePosterIV.setScaleType(ImageView.ScaleType.FIT_XY);
                    moviePosterIV.setAdjustViewBounds(true);
                Picasso.with(context).load(NetworkUtilites.getImagesURL() + mMovies[position].getPosterPath())
                        .into(moviePosterIV);
                    // It's not working now!
//                    loadImage(moviePosterIV,
//                            NetworkUtilites.getImagesURL() + mMovies[position].getPosterPath() );

                }
            }



        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickLisnter.onItemClicked(position);
            }
        });

        return rowView;
    }

    // It's not working now!

//    void loadImage(final ImageView imageView,final String url) {
//        Picasso.with(context)
//                .load(url)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//                        Picasso.with(context)
//                                .load(url)
//                                .error(R.drawable.img_place_holder)
//                                .into(imageView, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//
//                                    }
//
//                                    @Override
//                                    public void onError() {
//                                        Log.v("Picasso","Could not fetch image");
//                                    }
//                                });
//                    }
//                });
//    }


}
