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

    public float getX() {
        return coordinates[0];
    }

    public float getY() {
        return coordinates[1];
    }

    public float getZ() {
        return coordinates[2];
    }
}
