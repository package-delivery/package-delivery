package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class NearestInsertion implements Algorithm{

    private ArrayList<Integer> path;
    private double[][] matrix;
    private City[] cities;
    private Cities sortedCities = new Cities();


    /**
     * Constructor, which calculates the best nearest insertion route.
     * Important: It is a heuristic algorithm, therefore the result won't be optimal.
     * @param start name of the starting position
     */
    public NearestInsertion(String start) {
        // Get adjazenz matrix and cities from CSVReader
        matrix = CsvReader.getDistanceMatrix();
        cities = CsvReader.getCityMatrix();

        // start stopwatch
        Instant starts = Instant.now();

        // Check if starting point is in cities and convert start string to id of city
        int current = -1;
        for (int i = 0; i < cities.length; i++) {
            if (start.equals(cities[i].getCityName())) {
                current = cities[i].getId();
                break;
            }
        }
        if (current == -1) {
            System.out.println("Es ist zu einem Fehler gekommen, die eingegebene Startposition befindet sich nicht in der Adjazenzmatrix!");
            return;
        }

        // Now we start with the actual algorithm
        path = new ArrayList<Integer>();
        // First of all we add the starting point to our path
        path.add(current);
        // Now we add the nearest neighbor to the path
        // We can use the static method getNearest which we also used in NearestNeighbor algorithm
        path.add((int) NearestNeighbor.getNearest(0, matrix, path)[0]);
        // Now we add again the starting position, because that is also the end position
        path.add(0);
        // We have now our first small cycle, now we have to add new nodes to it, until we included all of them

        while(path.size() <= matrix.length) {
            // Get the closest distance of some node outside the cycle, to one node which is already in the cycle
            // 0 index = city index of node outside cycle
            // 1 index = the distance to a node inside cycle
            // 2 index = city index of node inside cycle
            double[] shortestDistance = new double[]{-1, Double.MAX_VALUE};

            // We got for path.size()-1, because last element is same element as element on 0 index (start=end position)
            for (int i = 0; i < path.size() - 1; i++) {
                double[] result = NearestNeighbor.getNearest(path.get(i), matrix, path);
                if (result[1] < shortestDistance[1]) {
                    shortestDistance[0] = result[0];
                    shortestDistance[1] = result[1];
                }
            }

            // In our shortestResult we got now the index of the city and the shortest distance of a city outside the cycle to a city inside the cycle.
            // Now we have to find out where to place it in our cycle to make the new cycle as short as possible

            double wholeDistance = Double.MAX_VALUE;
            int position = -1;
            for (int i = 1; i < path.size()-1; i++) {
                ArrayList<Integer> temp = new ArrayList<>(path);
                temp.add(i, (int) shortestDistance[0]);
                double tempDistance = getWholeDistance(temp, matrix);
                if (tempDistance < wholeDistance) {
                    position = i;
                }
            }
            path.add(position, (int) shortestDistance[0]);
            // We added one element to our cycle and we perform the same steps now, until our cycle contains all the nodes given in the CSV file
        }
        //System.out.println(path);
        //System.out.println(getWholeDistance(path));

        // The cycle is finished now and we convert the Integers back to the String names
        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            sortedCities.add(cities[path.get(i)]);
        }

        Instant ends = Instant.now();
        this.sortedCities.setTime(Duration.between(starts, ends).toMillis());
        this.sortedCities.setSortedCities(sortedCities);
        this.sortedCities.setDistance(getWholeDistance(path, matrix));
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
        return null;
    }
}
