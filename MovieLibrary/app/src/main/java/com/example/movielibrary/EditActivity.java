package com.example.movielibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    public MovieModel Movie;
    private SeekBar SeekbarUserRating;
    private TextView TextViewUserRating;
    private CheckBox CheckBoxWatched;
    private EditText EditTextUserComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Movie = (MovieModel) getIntent().getExtras().getSerializable("Movie");
        LoadView();

        SeekbarUserRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double value = ((float)progress / 10.0);
                TextViewUserRating.setText( Double.toString(value) );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    public void Btn_Cancel(View v) {
        startActivity(new Intent(EditActivity.this, OverviewActivity.class));
    }

    public void Btn_OK (View v) {
        Intent GoesTo = new Intent(EditActivity.this, OverviewActivity.class);
        addAll();
        GoesTo.putExtra("Movie", Movie);
        startActivity(GoesTo);
    }

    private void LoadView() {
        TextView ViewTitle = findViewById(R.id.textView_Edit_Title);
        TextViewUserRating =  findViewById(R.id.textView__Edit_UserRating);
        SeekbarUserRating = findViewById(R.id.seekBar_Edit_UserRating);
        CheckBoxWatched = findViewById(R.id.checkBox_Edit_Watched);
        EditTextUserComment = findViewById(R.id.editText_edit_UserComment);

        ViewTitle.setText(Movie.Title);
        if (Movie.UserRating != null) {
            int value = (int) (Movie.UserRating * 10);
            SeekbarUserRating.setProgress(value);
            TextViewUserRating.setText(Double.toString( Movie.UserRating));
        }
        CheckBoxWatched.setChecked(Movie.Watched);
        EditTextUserComment.setText(Movie.Comment);
    }

    private void addAll() {
        double value = ((float)SeekbarUserRating.getProgress() / 10.0);

        Movie.UserRating = value;
        Movie.Watched = CheckBoxWatched.isChecked();
        Movie.Comment = EditTextUserComment.getText().toString();
    }
}
