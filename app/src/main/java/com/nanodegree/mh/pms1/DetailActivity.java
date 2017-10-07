package com.nanodegree.mh.pms1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nanodegree.mh.pms1.data.Movie;
import com.nanodegree.mh.pms1.R;
import com.nanodegree.mh.pms1.utilites.NetworkUtilites;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    TextView titleTV;
    TextView releaseDateTV;
    TextView voteAverageTV;
    TextView summaryTV;

    ImageView thumnailIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTV = (TextView) findViewById(R.id.titleTV);
        releaseDateTV = (TextView) findViewById(R.id.releaseDateTV);
        voteAverageTV = (TextView) findViewById(R.id.voteAverageTV);
        summaryTV = (TextView) findViewById(R.id.summaryTV);

        thumnailIV = (ImageView) findViewById(R.id.thumbnailIV);

        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.EXTRA_MOVIE)) {
            Movie movie = (Movie) intent.getSerializableExtra(MainActivity.EXTRA_MOVIE);
            if(movie != null)
            displayData(movie);

        }
    }

    void displayData(Movie movie) {
        titleTV.setText(movie.getTitle());
        releaseDateTV.setText(movie.getDateReleased());
        voteAverageTV.setText(movie.getVoteAverage()+"");
        summaryTV.setText(movie.getOverview());

        Picasso.with(this).load(NetworkUtilites.getImagesThumbnailUrl() + movie.getPosterPath())
                .into(thumnailIV);
    }
}
