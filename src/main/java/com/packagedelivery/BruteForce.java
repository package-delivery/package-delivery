package com.packagedelivery;

import java.util.ArrayList;

public class BruteForce implements Algorithm {

    private double[][] matrix;
    private City[] cities;
    private Cities bestDistance = new Cities();
    private double currentBest = Double.MAX_VALUE;
    private String currentBestRoute = "";
    private int current;
    private int counterPrefix;
    private long facCities;

    /**
     * Constructor, which calculates the best route.
     * This algorithm tries all routes, therefore the result will be optimal.
     * @param start name of the starting position
     */

    public BruteForce(String start) {
        matrix = CsvReader.getDistanceMatrix();
        cities = CsvReader.getCityMatrix();

        // Check if starting point is in cities and convert start string to id of city
        current = -1;
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
        // Create the initial path as String
        String initialPath = "";
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getId() == current) continue;
            initialPath += cities[i].getId();
        }
        // Now we start our recursive bruteforce calculation
        counterPrefix = 0;
        facCities = factorial(cities.length-1);
        permutation("", initialPath);

        // We got the best possible route
        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < currentBestRoute.length(); i++) {
            sortedCities.add(cities[currentBestRoute.charAt(i)-'0']);
        }
        bestDistance.setDistance(currentBest);
        bestDistance.setSortedCities(sortedCities);
    }

    /**
     * Recursive algorithm which calculates all possibilities
     * @param prefix
     * @param str
     */
    //calculates permutations
    private void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            //only returns at half the factorial cities, so that paths dont get calculated twice
            if(counterPrefix >= facCities/2){
                return;
            }
            counterPrefix++;
            calculate(prefix);
        }
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    public static long factorial(int number) {
        if (number < 0) {
            throw new ArithmeticException(number + " is negative");
        }
        return number == 0 || number == 1 ? 1 : number * factorial(number - 1);
    }

    /**
     * Takes a path as String, calculates if it is better than the current best path and remembers it.
     * @param permutation a path as String
     */
    private void calculate(String permutation) {
        // Adding start and ending point to calculation
        String s = current+permutation+current;
        double distance = 0;
        for (int i = 0; i < s.length()-1; i++) {
            distance += matrix[s.charAt(i) - '0'][s.charAt(i+1) - '0'];
        }
        // Check if new route is shorter than current best!
        if (distance < currentBest) {
            //System.out.println(distance);
            currentBest = distance;
            currentBestRoute = s;
        }
    }

    /**
     * Returns a Cities obejct with the best possible path
     * @return Cities object
     */
    @Override
    public Cities getResult() {
        return bestDistance;
    }

}
