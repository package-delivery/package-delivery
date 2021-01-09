package com.packagedelivery;

import java.util.ArrayList;
import java.util.Objects;

public class Cities {
    private double distance;
    private double time;
    private ArrayList<City> sortedCities;

    /**
     * Constructors for Cities
     */
    public Cities() {
        this(0.0, null);
    }
    public Cities(double distance, ArrayList<City> sortedCities) {
        this.distance = distance;
        this.sortedCities = new ArrayList<City>(sortedCities);
    }

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


    /**
     * Method for comparing two Cities objects (generated with IntelliJ)
     * @param o Cities object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cities cities = (Cities) o;
        return Double.compare(cities.distance, distance) == 0 && Objects.equals(sortedCities, cities.sortedCities);
    }

    /**
     * HashCode method needed for ArrayLists (generated with IntelliJ)
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(distance, sortedCities);
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
