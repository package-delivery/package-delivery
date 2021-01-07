package com.packagedelivery;

import java.util.ArrayList;

public class Cities {
    private double distance;
    private double time;
    private ArrayList<City> sortedCities;

    /**
     * Returns distance shortest distance that the algorithm calculated
     * @return double distance in km
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the distance that the algorithm has chosen (the algorithm calls this function)
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Returns time that the algorithm used
     * @return double time
     */
    public double getTime() {
        return time;
    }

    /**
     * Sets time that the algorithm used (used internally by the algo)
     * @param time
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Gets List of cities sorted after the algorithms result
     * @return ArrayList<Citiy>
     */
    public ArrayList<City> getSortedCities() {
        return sortedCities;
    }

    /**
     * Sets List of cities sorted after the algorithms result
     * @param sortedCities<Citiy>
     */
    public void setSortedCities(ArrayList<City> sortedCities) {
        this.sortedCities = sortedCities;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "distance=" + distance +
                ", time=" + time +
                ", sortedCities=" + sortedCities +
                '}';
    }
}
