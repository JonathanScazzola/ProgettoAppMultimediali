package com.example.iofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.iofit.R;

import org.w3c.dom.Text;

public class GraphicExercise extends AppCompatActivity {
    private final String TAG = "GraphicExercise";
    private TextView tvSelectedExercise = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_exercise);

        tvSelectedExercise = findViewById(R.id.TvSelectedExercise);
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra(getString(R.string.LABEL_EXERCISE_NAME));
        tvSelectedExercise.setText(exerciseName);
    }
}