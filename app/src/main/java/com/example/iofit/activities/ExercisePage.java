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
import com.example.iofit.utils.Coordinates;
import com.example.iofit.utils.CoordinatesList;

public class ExercisePage extends AppCompatActivity implements SensorEventListener {
    private final String TAG = "ExercisePage";
    private TextView tvSelectedExercise = null, tvX = null, tvY = null, tvZ = null;
    private Button btnStartExercise = null;
    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;
    private Sensor gravitySensor = null;
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
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (gravitySensor == null) {
            Log.i(TAG, "Gravity Sensor not available");
            // TODO lanciare eccezioni o robe così
            return;
        }
        btnStartExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Scegliere ogni quanto prendere i dati
                sensorManager.registerListener(sensorEventListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(sensorEventListener, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);

                // Stop dopo X secondi
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sensorManager.unregisterListener(sensorEventListener);
                        Log.i(TAG, "Exercise over");
                        tvX.setText("0");
                        tvY.setText("0");
                        tvZ.setText("0");
                    }
                }, 10000); //Secondi 10
            }
        });
    }

    private Coordinates gravityValues;
    private Coordinates accelerationValues;

    @Override
    public void onSensorChanged(SensorEvent event) {
/*
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float accelerationRaw = (float) Math.sqrt(x * x + y * y + z * z);
*/
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            gravityValues = new Coordinates(event.values);
            //Log.i(TAG, "GRAV " + gravityValues.log(null));
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerationValues = new Coordinates(event.values);
            //Log.i(TAG, "ACC " + accelerationValues.log(null));
        }

        if(gravityValues == null || accelerationValues == null) {
            return;
        }

        float linearX = accelerationValues.getX() - gravityValues.getX();
        float linearY = accelerationValues.getY() - gravityValues.getY();
        float linearZ = accelerationValues.getZ() - gravityValues.getZ();
        Coordinates linearAcceleration = new Coordinates(linearX, linearY, linearZ);
        //Log.i(TAG, "LIN " + linearAcceleration.log(null));
        float accelerationYTrunc = (float) (Math.floor(linearAcceleration.getY() * 100) / 100);

        // Varia fra 0.01 e -0.01 ma con picchi di 0.02 se lo sfioro
        if(accelerationYTrunc <= 0.01f) {
            Log.i(TAG, "Fermo");
        } else {
            Log.i(TAG, "ACC Y" + accelerationYTrunc);
        }
    }

    /*
    float lastX, lastY, lastZ;
    Coordinates gravityCoord = new Coordinates();
    @Override
    public void onSensorChanged(SensorEvent event) {
        float gravityX, gravityY, gravityZ;
        if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            // Ottieni i valori del sensore di gravità
            gravityCoord = new Coordinates(event.values);
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Ottimizzabile
            if(coordinatesList.getSize() == 0) {
                Log.i(TAG, "Empty Coordinates");
                lastX = 0; lastY = 0; lastZ = 0;
            } else {
                lastX = coordinatesList.getLast().getX();
                lastY = coordinatesList.getLast().getY();
                lastZ = coordinatesList.getLast().getZ();
            }

            coordinatesList.add(event.values);
            //Log.i(TAG, "Coordinate: " + (coordinatesList.getLast().getX() + " " + coordinatesList.getLast().getY() + " " + coordinatesList.getLast().getZ()));

            tvX.setText("X: " + Float.toString(coordinatesList.getLast().getX()));
            tvY.setText("Y: " + Float.toString(coordinatesList.getLast().getY()));
            tvZ.setText("Z: " + Float.toString(coordinatesList.getLast().getZ()));

            float x = coordinatesList.getLast().getX();
            float y = coordinatesList.getLast().getY();
            float z = coordinatesList.getLast().getZ();

            //Log.i(TAG, "LAST Coordinate: " + lastX + " " + lastY + " " + lastZ);
            //Log.i(TAG, "NEW Coordinate: " + x + " " + y + " " + z);
            x -= lastX;
            y -= lastY;
            z -= lastZ;
            //Log.i(TAG, "DIFF Coordinate: " + x + " " + y + " " + z);

            int coeff = 0;
            if(gravityCoord.getY() == 0) {
                // Il gravitySensor non ha ancora mandato una misurazione
                Log.i(TAG, "Gravity Sensor didn't send a value yet");
                return;
            } else if(gravityCoord.getY() > 0) {
                coeff = 1;
            } else if(gravityCoord.getY() < 0) {
                coeff = -1;
            }
            // Calcola l'accelerazione risultante
            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);
            float accelerationTrunc = (float) (Math.floor(acceleration * 100) / 100);

            //Log.i(TAG, "ACC: " + acceleration * coeff);
            if(accelerationTrunc == 0.00f) {
                Log.i(TAG, "FERMO");
            } else if (coeff > 0) {
                Log.i(TAG, "SU");
            } else if (coeff < 0) {
                Log.i(TAG, "GIU");
            }
        }
    }
*/
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i(TAG, "Accuracy changed. New Accuracy: " + accuracy);
    }
}