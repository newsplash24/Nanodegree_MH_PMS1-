package com.nanodegree.mh.pms1.data;

import java.io.Serializable;

/**
 * Created by Mohamed on 10/6/2017.
 */

public class Movie implements Serializable {

    private String imagePath;

    private String overview;

    private double voteAverage;

    private String title;

    private String dateReleased;

    private int movieID;

    public Movie() {
    }

    public Movie(String posterPath, String title, double voteAverage, String overview,
          String dateReleased, int movieID){

        this.imagePath = posterPath;

        this.title = title;

        this.overview = overview;

        this.voteAverage = voteAverage;

        this.dateReleased = dateReleased;

        this.movieID = movieID;
    }

    public void setVoteAverage(double vote_average) {
        this.voteAverage = vote_average;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosterPath(String poster_path) {
        this.imagePath = poster_path;
    }

    public void setDateReleased(String dateReleased) {
        this.dateReleased = dateReleased;
    }
    public void set_ID(int _ID) {
        setMovieID(_ID);
    }


    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return imagePath;
    }

    public String getDateReleased() {
        return this.dateReleased;
    }
    public int get_ID() {
        return getMovieID();
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getMovieID() {
        return movieID;
    }
}
