package com.example.movielibrary;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class OverviewActivity extends AppCompatActivity {

    private MovieModel[] MovieList;
    SharedPreferences SharedPres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        SharedPres  = getSharedPreferences("Movie_Library", MODE_PRIVATE);
        // see if we have data
        String data = SharedPres.getString("MovieList",null);

        Log.d("TEST", "onCreate: " + data);
        if (data != null) {
            Gson gson = new Gson();
            MovieList = gson.fromJson(data, MovieModel[].class);
        } else {
            // get data from csv
            MovieList = this.fetchCSVData(); // get from csv
        }

        // see if we got a package
        if (this.getIntent().getExtras() != null) {
            MovieModel Movie = (MovieModel) getIntent().getExtras().getSerializable("Movie");
            // find the movie and replace it

            for (int i = 0; i < MovieList.length ; i++) {
                if (MovieList[i].Title.contains(Movie.Title)){ // match
                    MovieList[i] = Movie;
                }
            }
        }

        // save all we got
        Gson gson = new Gson();
        String SaveAsText = gson.toJson(MovieList);
        SharedPres.edit().putString("MovieList",SaveAsText).apply();

        // show all the movies
        InitiateListView(MovieList); // load up the listview
    }

    public void Btn_Exit(View v) { // close app
        ActivityCompat.finishAffinity(this);
    }

    private  MovieModel[] fetchCSVData() { // get movies as list from csv file
        InputStream inputStream = getResources().openRawResource(R.raw.movielist);
        CSVFile csvFile = new CSVFile(inputStream);
        return csvFile.read();
    }

    private void InitiateListView(MovieModel[] Movies) {
        // Create the adapter to convert the array to views
        MovieAdapter adapter = new MovieAdapter(OverviewActivity.this, Movies);
        // Attach the adapter to a ListView
        ListView list = findViewById(R.id.ListViewOverview);
        list.setAdapter(adapter);
    }
}