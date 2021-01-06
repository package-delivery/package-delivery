package com.packagedelivery;

import java.util.ArrayList;

public class BetterBruteForce implements Algorithm {

    private double[][] matrix;
    private City[] cities;
    private Cities bestDistance = new Cities();
    private double currentBest = Double.MAX_VALUE;
    private String currentBestRoute = "";
    private int current;

    public BetterBruteForce(String start) {
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
        permutation("", initialPath);

        // We got the best possible route
        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < currentBestRoute.length(); i++) {
            sortedCities.add(cities[currentBestRoute.charAt(i)-'0']);
        }
        bestDistance.setDistance(currentBest);
        bestDistance.setSortedCities(sortedCities);
    }

    //calculates permutations
    private void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) calculate(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    private void calculate(String permutation) {
        // Adding start and ending point to calculation
        String s = current+permutation+current;
        double distance = 0;
        for (int i = 0; i < s.length()-1; i++) {
            distance += matrix[s.charAt(i) - '0'][s.charAt(i+1) - '0'];
        }
        // Check if new route is shorter than current best!
        if (distance < currentBest) {
            currentBest = distance;
            currentBestRoute = s;
        }
    }

    @Override
    public Cities getResult() {
        return bestDistance;
    }

}
