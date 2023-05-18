package com.example.iofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iofit.R;

public class ExerciseSelector extends AppCompatActivity {
    private final String TAG = "ExerciseSelector";
    private Button btnBench = null;
    private Button btnSquat = null;
    private Button btnDeadlift = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selector);

        btnBench = findViewById(R.id.BtnBench);
        btnSquat = findViewById(R.id.BtnSquat);
        btnDeadlift = findViewById(R.id.BtnDeadlift);

        btnBench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseSelector.this, ExercisePage.class);

                // Per ora lo schianto qui ma penso che poi si debba trovare un modo più carino per passare il nome dell'esercizio
                intent.putExtra(getString(R.string.LABEL_EXERCISE_NAME), "Bench");
                startActivity(intent);
            }
        });

        btnSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseSelector.this, ExercisePage.class);

                // Per ora lo schianto qui ma penso che poi si debba trovare un modo più carino per passare il nome dell'esercizio
                intent.putExtra(getString(R.string.LABEL_EXERCISE_NAME), "Squat");
                startActivity(intent);
            }
        });

        btnDeadlift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExerciseSelector.this, ExercisePage.class);

                // Per ora lo schianto qui ma penso che poi si debba trovare un modo più carino per passare il nome dell'esercizio
                intent.putExtra(getString(R.string.LABEL_EXERCISE_NAME), "Deadlift");
                startActivity(intent);
            }
        });
    }
}