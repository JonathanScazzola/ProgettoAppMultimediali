package com.example.iofit.utils;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Coordinates {
    private Float[] coordinates;

    public Coordinates() {
        coordinates = new Float[]{null, null, null};
    }

    public Coordinates(float x, float y, float z) {
        coordinates = new Float[]{x, y, z};
    }
    public Coordinates(float[] c) {
        coordinates = new Float[c.length];
        int i = 0;
        for(float elem : c) {
            coordinates[i] = Float.valueOf(elem);
            ++i;
        }
    }

    public Float[] getCoordinates() {
        return coordinates;
    }

    public float getX() {
        return coordinates[0];
    }

    public float getY() {
        return coordinates[1] != null ? coordinates[1] : 0;
    }

    public float getZ() {
        return coordinates[2];
    }
    // TODO metodo da finire
    public float truncate(String axisName, int decimalPart) {
        switch (axisName) {
            case "X":
                break;
            case "Y":
                break;
            case "Z":
                break;
        }
        return 0;
    }
    public String log(@Nullable String axisName) {
        if(axisName == null)
            return "[" + coordinates[0] + " " + coordinates[1] + " " + coordinates[2] + "]";
        switch (axisName) {
            case "X":
                return Float.toString(coordinates[0]);
            case "Y":
                return Float.toString(coordinates[1]);
            case "Z":
                return Float.toString(coordinates[2]);
            default:
                return "";
        }
    }
}
