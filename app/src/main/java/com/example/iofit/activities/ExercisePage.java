package com.example.iofit.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.iofit.R;
import com.example.iofit.utils.CoordinatesList;

public class ExercisePage extends AppCompatActivity implements SensorEventListener {
    private final String TAG = "ExercisePage";
    private TextView tvSelectedExercise = null, tvX = null, tvY = null, tvZ = null;
    private Button btnStartExercise = null;
    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;
    private SensorEventListener sensorEventListener = null;
    private CoordinatesList coordinatesList = null;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_page);

        tvSelectedExercise = findViewById(R.id.TvSelectedExercise);
        tvX = findViewById(R.id.TvX);
        tvY = findViewById(R.id.TvY);
        tvZ = findViewById(R.id.TvZ);
        Intent intent = getIntent();
        String exerciseName = intent.getStringExtra(getString(R.string.LABEL_EXERCISE_NAME));
        tvSelectedExercise.setText(exerciseName);

        btnStartExercise = findViewById(R.id.BtnStartExercise);

        coordinatesList = new CoordinatesList();

        mediaPlayer = MediaPlayer.create(this, R.raw.saund);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorEventListener = this;
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer == null) {
            Log.i(TAG, "Accelerometer not available");
            // TODO lanciare eccezioni o robe così
            return;
        }

        btnStartExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Scegliere ogni quanto prendere i dati
                sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

                // Stop dopo 3 secondi
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sensorManager.unregisterListener(sensorEventListener);
                        Log.i(TAG, "Exercise over");
                        tvX.setText("0");
                        tvY.setText("0");
                        tvZ.setText("0");

                    }
                }, 3000); //3000
            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        coordinatesList.add(event.values);
        Log.i(TAG, "Coordinate: " + (coordinatesList.getLast().getX() + " " + coordinatesList.getLast().getY() + " " + coordinatesList.getLast().getZ()));
        tvX.setText("X: " + Float.toString(coordinatesList.getLast().getX()));

        float X = coordinatesList.getLast().getX();



        if (X > 2 || X < -2) { // T

            mediaPlayer = MediaPlayer.create(this, R.raw.saund);

            mediaPlayer.start();
        }

        tvY.setText("Y: " + Float.toString(coordinatesList.getLast().getY()));
        tvZ.setText("Z: " + Float.toString(coordinatesList.getLast().getZ()));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG, "Accuracy changed. New Accuracy: " + accuracy);
    }
}