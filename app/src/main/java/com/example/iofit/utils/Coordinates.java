package com.example.iofit.utils;

public class Coordinates {
    private Float[] coordinates;

    public Coordinates() {
        coordinates = new Float[]{};
    }

    public Coordinates(float x, float y, float z) {
        coordinates = new Float[]{x, y, z};
    }

    public Float[] getCoordinates() {
        return coordinates;
    }

    public float X() {
        return coordinates[0];
    }

    public float Y() {
        return coordinates[1];
    }

    public float Z() {
        return coordinates[2];
    }
}
