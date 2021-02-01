package com.packagedelivery;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Contains the result of the Algorithm with calculated distance and cities in order
 */
public class Cities {
    private double distance;
    private double time;
    private ArrayList<City> sortedCities;

    /**
     * Default constructor for cities
     */
    public Cities() {
        this(0.0, null);
    }

    /**
     * Constructor
     * @param distance Calculated distance
     * @param sortedCities Array with all the City Objects in right order
     */
    public Cities(double distance, ArrayList<City> sortedCities) {
        this.distance = distance;
        this.sortedCities = sortedCities;
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
     * @param distance Distance in km
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
     * @param time Time in ms
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * Gets List of cities sorted after the algorithms result
     * @return ArrayList City
     */
    public ArrayList<City> getSortedCities() {
        return sortedCities;
    }

    /**
     * Gets List of IDs of the sorted Cities
     * @return ArrayList Integer
     */
    public ArrayList<Integer> getIDs() {
        ArrayList<Integer> l = new ArrayList<>();
        for (City c : sortedCities)
            l.add(c.getId());
        return l;
    }

    /**
     * Sets List of cities sorted after the algorithms result
     * @param sortedCities City
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
     * @return Hash of the object
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
