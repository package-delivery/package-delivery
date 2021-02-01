package com.packagedelivery;

import java.util.ArrayList;

/**
 * Brute Force Algorithm implemented
 */
public class BruteForce implements Algorithm{

    private double[][] matrix;
    private City[] cities;
    private Cities bestDistance = new Cities();
    private int current, iteration, facCities;

    /**
     * Constructor, which calculates the best possible route.
     * @param start starting position as string
     */
    public BruteForce(String start) {
        matrix = CsvReader.getDistanceMatrix();
        cities = CsvReader.getCityMatrix();

        ArrayList<Integer> firstPermutation = new ArrayList<Integer>();
        // Check if starting point is in cities and convert start string to id of city
        current = -1;
        for (int i = 0; i < cities.length; i++) {
            if (start.equals(cities[i].getCityName())) {
                current = cities[i].getId();
            } else {
                firstPermutation.add(cities[i].getId());
            }
        }
        if (current == -1) {
            System.out.println("Es ist zu einem Fehler gekommen, die eingegebene Startposition befindet sich nicht in der Adjazenzmatrix!");
            return;
        }

        // Set bestDistance to MAX_VALUE
        bestDistance = new Cities(Double.MAX_VALUE, null);

        // Set iteration to 0 and calculate max routs
        iteration = 0;
        facCities = factorial(matrix.length-1) + 2;

        // Start recursive algorithm
        permute(firstPermutation, 0);
    }

    /**
     * recursive algorithm to calculate all permutations
     * @param arr
     * @param k
     */
    private void permute(java.util.ArrayList<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            calculate(arr);
            iteration++;
            if (iteration > facCities)
                return;
        }
    }

    /**
     * Checks if current calculated route is better than overall best route
     * @param l
     */
    private void calculate(ArrayList<Integer> l) {
        ArrayList<Integer> buffer = new ArrayList<>(l);
        buffer.add(0, current);
        buffer.add(current);
        double currentDist = NearestInsertion.getWholeDistance(buffer, matrix);
        if (currentDist < bestDistance.getDistance()) {
            bestDistance = new Cities(currentDist, idsToCities(buffer));
        }
    }

    /**
     * Converts an ArrayList of IDs to an ArrayList<City>
     * @param l
     * @return
     */
    private ArrayList<City> idsToCities(ArrayList<Integer> l) {
        ArrayList<City> cities = new ArrayList<>();
        for (Integer i : l) {
            cities.add(new City(this.cities[i].getCityName(), i));
        }
        return cities;
    }

    /**
     * Recursive algorithm to calculate factorial
     * @param number
     * @return
     */
    public static int factorial(int number) {
        if (number < 0) {
            throw new ArithmeticException(number + " is negative");
        }
        return number == 0 || number == 1 ? 1 : number * factorial(number - 1);
    }

    /**
     * Returns a Cities Object with the best possible route
     * @return
     */
    @Override
    public Cities getResult() {
        return bestDistance;
    }
}
