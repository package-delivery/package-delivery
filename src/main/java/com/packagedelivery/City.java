package com.packagedelivery;

import java.util.Objects;

/**
 * Stores one City with id and name
 */
public class City {
    private boolean visited;
    private String cityName;
    private int id;

    /**
     * Creates a new City
     * @param cityName String Name of the city
     * @param id Integer unique id of the city
     */
    public City(String cityName, int id){
        this.cityName = cityName;
        this.id = id;
    }

    /**
     * Getter for visited
     * @return If the city was visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Setter for visited
     * @param visited Set if the city was visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Getter for city names
     * @return The name of the city
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Getter for the id
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, id);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                ", id=" + id +
                '}';
    }
}
