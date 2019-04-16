package com.example.movielibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {
    public MovieModel Movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Movie = (MovieModel) getIntent().getExtras().getSerializable("Movie");
        LoadView();

    }

    public void Btn_OK(View v) {
        startActivity(new Intent(DetailsActivity.this , OverviewActivity.class));
    }

    private void LoadView() {
       ImageView ViewIcon = (ImageView )findViewById(R.id.imageView_Details);
       TextView ViewGenres = findViewById(R.id.textView_Details_Genres);
       TextView ViewIMDB = findViewById(R.id.textView_Details_IMDBRating);
       TextView ViewPlot = findViewById(R.id.textView_Details_Plot);
       TextView ViewTitle = findViewById(R.id.textView_Details_Title);
       TextView ViewUserRating =  findViewById(R.id.textView_Details_UserRating);
       TextView UserComment = findViewById(R.id.TextView_Details_UserComment);
       CheckBox Watched = findViewById(R.id.checkBox_Details_Watched);


        ViewIcon.setImageResource(Movie.Icon);
        ViewGenres.setText(Movie.Genres);

        ViewPlot.setText(Movie.Plot);
        ViewTitle.setText(Movie.Title);
        UserComment.setText(Movie.Comment);

        if (Movie.Comment == "" | Movie.Comment == null) {
            UserComment.setVisibility(View.GONE);
        }

        Watched.setChecked(Movie.Watched);


        ViewIMDB.setText(getResources().getString(R.string.String_IMDB) + " " + Movie.Rating);
        if (Movie.UserRating != null) {
            ViewUserRating.setText(getResources().getString(R.string.String_User) + " " + Double.toString(Movie.UserRating) );
        }

    }
}
