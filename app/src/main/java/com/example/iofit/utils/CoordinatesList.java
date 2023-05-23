package com.example.iofit.utils;

import java.util.ArrayList;

public class CoordinatesList {
    private float x, y, z;
    private ArrayList<Coordinates> coordinatesList;

    public CoordinatesList() {
        coordinatesList = new ArrayList<Coordinates>();
    }

    public void add(Coordinates c) {
        // TODO se abbiamo tempo fare controlli tipo return true se andato bene ecc...
        this.coordinatesList.add(c);
        return;
    }

    public void add(float[] c) {
        // TODO se abbiamo tempo fare controlli tipo return true se andato bene ecc...
        Coordinates coord = new Coordinates(c);
        this.coordinatesList.add(coord);
        return;
    }

    public Coordinates get(int index) {
        return coordinatesList.get(index);
    }
    public ArrayList<Coordinates> getAll() {
        return coordinatesList;
    }
    public Coordinates getFirst() {
        return coordinatesList.get(0);
    }
    public int getSize() {
        return coordinatesList.size();
    }

    public Coordinates getLast() {
        return coordinatesList.get(coordinatesList.size() - 1);
    }
}
