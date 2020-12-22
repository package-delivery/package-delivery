package com.packagedelivery;

public class City {
    private boolean visited;
    private String cityName;
    private int id;

    public City(String cityName, int id){
        this.cityName = cityName;
        this.id = id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getCityName() {
        return cityName;
    }

    public int getId() {
        return id;
    }
}
