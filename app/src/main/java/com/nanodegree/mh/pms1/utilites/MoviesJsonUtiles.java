package com.nanodegree.mh.pms1.utilites;

import android.content.Context;

import com.nanodegree.mh.pms1.data.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mohamed on 10/7/2017.
 */

public class MoviesJsonUtiles {

    public static Movie[] getMoviesFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        final String MDB_LIST = "results";
        final String MDB_ID = "id";


        final String MDB_TITLE = "original_title";


        final String MDB_POSTER_PATH = "poster_path";
        final String MDB_OVERVIEW = "overview";

        final String MDB_VOTE_AVERAGE = "vote_average";
        final String MDB_RELEASE_DATE = "release_date";


        Movie[] parsedMoviesData = null;

        JSONObject forecastJson = new JSONObject(forecastJsonStr);


        JSONArray moviesArray = forecastJson.getJSONArray(MDB_LIST);

        parsedMoviesData = new Movie[moviesArray.length()];

        for (int i = 0; i < parsedMoviesData.length; i++) {

            JSONObject movieJson = moviesArray.getJSONObject(i);

            parsedMoviesData[i] = new Movie();

            parsedMoviesData[i].set_ID(movieJson.getInt(MDB_ID));
            parsedMoviesData[i].setTitle(movieJson.getString(MDB_TITLE));
            parsedMoviesData[i].setDateReleased(movieJson.getString(MDB_RELEASE_DATE));
            parsedMoviesData[i].setOverview(movieJson.getString(MDB_OVERVIEW));
            parsedMoviesData[i].setVoteAverage(movieJson.getDouble(MDB_VOTE_AVERAGE));
            parsedMoviesData[i].setPosterPath(movieJson.getString(MDB_POSTER_PATH));
        }

        return parsedMoviesData;
    }
}
