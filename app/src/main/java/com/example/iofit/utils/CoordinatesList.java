package com.example.iofit.utils;

import java.util.ArrayList;
import java.util.List;

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

    public Coordinates get(int index) {
        return coordinatesList.get(index);
    }

    public Coordinates getFirst() {
        return coordinatesList.get(0);
    }

    public Coordinates getLast() {
        return coordinatesList.get(coordinatesList.size() - 1);
    }
}
