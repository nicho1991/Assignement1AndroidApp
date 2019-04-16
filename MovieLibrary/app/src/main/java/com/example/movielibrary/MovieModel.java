package com.example.movielibrary;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import java.io.Serializable;

public class MovieModel implements Serializable {
    // from CSV
    public String Title;
    public String Plot;
    public String Genres;
    public String Rating;
    public int Icon;
    public String Comment;


    //User data
    public Double UserRating = null;
    public boolean Watched;
    MovieModel(String Title, String Plot, String Genres, String Rating) {
        this.Title = Title;
        this.Plot = Plot;
        this.Genres = Genres;
        this.Rating = Rating;
        IconSelector();
    }

    private void IconSelector () {
        String PrimaryGenre = Genres.split(",")[0];

        switch (PrimaryGenre){
            case "Action":
                this.Icon = R.drawable.action;
                break;
            case "Adventure":
                this.Icon = R.drawable.adventure;

                break;
            case "Biography":
                this.Icon = R.drawable.biography;
                break;
            case "Comedy":
                this.Icon = R.drawable.comedy;
                break;
            case "Drama":
                this.Icon = R.drawable.drama;
                break;
            case "Fantasy":
                this.Icon = R.drawable.fantasy;
                break;
            case "History":
                this.Icon = R.drawable.history;
                break;
            case "Music":
                this.Icon = R.drawable.music;
                break;
            case "Romance":
                this.Icon = R.drawable.romance;
                break;
            case "Sci-fi":
                this.Icon = R.drawable.scifi;
                break;
            case "Sport":
                this.Icon = R.drawable.sport;
                break;
            case "Thriller":
                this.Icon = R.drawable.thriller;
                break;

            case "Animation":
                this.Icon = R.drawable.animation;
                break;
            default:
                this.Icon = R.drawable.ic_launcher_foreground;
                break;
        }
    }

}
