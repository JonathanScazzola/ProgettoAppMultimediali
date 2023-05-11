package com.example.iofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iofit.R;
import com.example.iofit.utils.Coordinates;
import com.example.iofit.utils.CoordinatesList;

import org.w3c.dom.Text;

public class GraphicExercise extends AppCompatActivity {
    private final String TAG = "GraphicExercise";
    private TextView tvSelectedExercise = null;
    private Button btnStartExercise = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_exercise);

        tvSelectedExercise = findViewById(R.id.TvSelectedExercise);
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra(getString(R.string.LABEL_EXERCISE_NAME));
        tvSelectedExercise.setText(exerciseName);

        btnStartExercise = findViewById(R.id.BtnStartExercise);

        btnStartExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Coordinates c = new Coordinates(10.5f, 5.9f, 3.3f);
                Log.i(TAG, "Coordinate: " + (c.X() + " " + c.Y() + " " + c.Z()));
                CoordinatesList cList = new CoordinatesList();
                cList.add(c);
                c = cList.get(0);
                Log.i(TAG, "Coordinate: " + (c.X() + " " + c.Y() + " " + c.Z()));
                c = cList.getFirst();
                Log.i(TAG, "Coordinate: " + (c.X() + " " + c.Y() + " " + c.Z()));
                c = cList.getLast();
                Log.i(TAG, "Coordinate: " + (c.X() + " " + c.Y() + " " + c.Z()));
            }
        });
    }
}