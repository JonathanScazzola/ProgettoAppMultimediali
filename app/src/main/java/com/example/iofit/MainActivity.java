package com.example.iofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iofit.activities.ExerciseSelector;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private Button btnStartExercises = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartExercises = findViewById(R.id.BtnStartExercises);

        btnStartExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExerciseSelector.class);
                startActivity(intent);
            }
        });
    }
}