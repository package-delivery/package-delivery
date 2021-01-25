package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class NearestNeighbor implements Algorithm{
    private double distance;
    private ArrayList<Integer> path;
    private Cities sortedCities = new Cities();

    /**
     * Constructor, which calculates the best nearest neighbor route.
     * Important: It is a heuristic algorithm, therefore the result won't be optimal.
     * @param start name of the starting position
     */
    public NearestNeighbor(String start) {
        // get com.project.City array and adjazenzmatrix
        double[][] matrix = CsvReader.getDistanceMatrix();
        City[] cities = CsvReader.getCityMatrix();

        // start stopwatch
        Instant starts = Instant.now();

        // Check if start city is a city in the csv file
        boolean contains = false;
        for (int i = 0; i < cities.length; i++) {
            if (start.equals(cities[i].getCityName())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            System.out.println("Es ist zu einem Fehler gekommen, die eingegebene Startposition befindet sich nicht in der Adjazenzmatrix!");
            return;
        }

        // Now we have to convert the start string to the id
        int current = 0;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getCityName().equals(start)) {
                current = cities[i].getId();
                break;
            }
        }
        distance = 0;
        int idStart = current;
        path = new ArrayList<Integer>();
        path.add(current);
        while (path.size() < matrix.length) {
            double[] temp = getNearest(current, matrix, path);
            path.add((int) temp[0]);
            distance += temp[1];
            current = path.get(path.size()-1);
        }
        path.add(idStart);
        distance += matrix[idStart][current];

        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            sortedCities.add(cities[path.get(i)]);
        }
        Instant ends = Instant.now();
        this.sortedCities.setTime(Duration.between(starts, ends).toNanos());
        this.sortedCities.setSortedCities(sortedCities);
        this.sortedCities.setDistance(distance);
    }

    /**
     * Static method which returns the nearest neighbor of a point and the distance.
     * Important: Points which are already in the path, are skipped.
     * @param point The Point/City for which we need to search the nearest neighbour
     * @param matrix The adjazenzmatrix with all distances between points
     * @param path An ArrayList with points
     * @return an array with an integer value at position 0 (if of nearest neighbor), and double value at position 1 (distance from nearest neighbor to point)
     */
    protected static double[] getNearest(int point, double[][] matrix, ArrayList<Integer> path) {
        double diff = Double.MAX_VALUE;
        int position = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (path.contains(i)) continue;
            if (matrix[point][i] < diff)  {
                diff = matrix[point][i];
                position = i;
            }
        }
        //distance += diff;
        return new double[]{position, diff};
    }

    /**
     * Return Cities object
     * @return Cities object with best nearest neighbour route
     */
    @Override
    public Cities getResult() {
        return new Cities(sortedCities.getDistance(), sortedCities.getSortedCities());
    }

    @Override
    public String getVisualization() {
        return null;
    }

}
