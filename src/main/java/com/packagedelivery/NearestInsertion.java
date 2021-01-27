package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class NearestInsertion implements Algorithm, Displayable{

    private double[][] matrix;
    private City[] cities;
    private String visualized;

    private ArrayList<Integer> path;
    private Cities sortedCities;

    public NearestInsertion(String start) {
        this(start, false);
    }

    /**
     * Constructor, which calculates the best nearest insertion route.
     * Important: It is a heuristic algorithm, therefore the result won't be optimal.
     * @param start name of the starting position
     */
    public NearestInsertion(String start, boolean visualization) {
        matrix = CsvReader.getDistanceMatrix();
        cities = CsvReader.getCityMatrix();

        if (visualization) visualized = "";

        // start stopwatch
        Instant starts = Instant.now();

        // Check if starting point is in cities and convert start string to id of city
        int current = -1;
        for (City city : cities) {
            if (start.equals(city.getCityName())) {
                current = city.getId();
                break;
            }
        }
        if (current == -1) {
            System.out.println("Es ist zu einem Fehler gekommen, die eingegebene Startposition befindet sich nicht in der Adjazenzmatrix!");
            System.exit(-1);
        }

        // Now we start with the actual algorithm
        path = new ArrayList<Integer>();
        // First of all we add the starting point to our path
        path.add(current);
        // Now we add the nearest neighbor to the path
        // We can use the static method getNearest which we also used in NearestNeighbor algorithm
        path.add((int) NearestNeighbor.getNearest(0, matrix, path)[0]);
        // We have now our first small cycle, now we have to add new nodes to it, until we included all of them

        if (visualization) {
            ArrayList<Integer> buf = new ArrayList<>(path);
            buf.add(current);
            visualized += intListToString(buf) + "\n";
            buf = null;
        }

        while (path.size() < matrix.length) {
            double overallMinCost = Double.MAX_VALUE;
            int pointToInsert = -1, insertionIndex = -1;
            for (int i = 0; i < matrix.length; i++) {
                if (path.contains(i)) continue;
                double minCost = Double.MAX_VALUE;
                int before = -1, after = -1;

                // Find segment in hull before -> after that minimizes cost for cost(before -> p) + cost(p -> after) - cost(before -> after)
                for (int x = 0; x < path.size() - 1; x++) {
                    double currentCost = cost(path.get(x), cities[i].getId()) + cost(cities[i].getId(), path.get(x + 1)) - cost(path.get(x), path.get(x + 1));
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        before = path.get(x);
                        after = path.get(x + 1);
                    }
                }

                // Find point that minimizes cost for (before -> p -> after) / cost(before -> after)
                double overallCurrentCost = (cost(before, cities[i].getId()) + cost(cities[i].getId(), after)) / cost(before, after);
                if (overallCurrentCost < overallMinCost) {
                    overallMinCost = overallCurrentCost;
                    pointToInsert = cities[i].getId();
                    insertionIndex = path.indexOf(after);
                }
            }
            // Add point to path
            path.add(insertionIndex, pointToInsert);

            if (visualization) {
                ArrayList<Integer> buf = new ArrayList<>(path);
                buf.add(current);
                visualized += intListToString(buf) + "\n";
                buf = null;
            }

        }
        path.add(current);
        // Calculate final Cities object
        ArrayList<City> sortedCities = new ArrayList<>();
        for (Integer id : path)
            sortedCities.add(cities[id]);
        this.sortedCities = new Cities(getWholeDistance(path, matrix), sortedCities);
        Instant ends = Instant.now();
        this.sortedCities.setTime(Duration.between(starts, ends).toMillis());
    }

    /**
     * Gives distance between to points in matrix
     * @param p1
     * @param p2
     * @return
     */
    private double cost(int p1, int p2) {
        return matrix[p1][p2];
    }

    /**
     * Calculates the distance of a route and returns it
     * @param path an ArrayList which contains a route.
     * @param matrix
     * @return the distance of the route as double
     */
    protected static double getWholeDistance(ArrayList<Integer> path, double[][] matrix) {
        double dist = 0;
        for (int i = 0; i < path.size()-1; i++) {
            dist += matrix[path.get(i)][path.get(i+1)];
        }
        return dist;
    }

    private String intListToString(ArrayList<Integer> l) {
        String r = "";
        for (Integer i : l) r += cities[i].getCityName() + ";";
        return r;
    }

    /**
     * Return Cities object
     * @return Cities object with nearest insertion route
     */
    @Override
    public Cities getResult() {
        return this.sortedCities;
    }

    @Override
    public String getVisualization() {
        return this.visualized;
    }
}
