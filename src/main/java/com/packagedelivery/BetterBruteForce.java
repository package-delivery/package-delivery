package com.packagedelivery;

public class BetterBruteForce implements Algorithm {

    private double[][] matrix;
    private City[] cities;
    private Cities bestDistance = new Cities();

    public BetterBruteForce(String start) {
        matrix = CsvReader.getDistanceMatrix();
        cities = CsvReader.getCityMatrix();

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
        // Create the initial path as String
        String initialPath = ""+current;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getId() == current) continue;
            initialPath += cities[i].getId();
        }
        initialPath += ""+current;
        System.out.println(initialPath);

    }

    @Override
    public Cities getResult() {
        return null;
    }

}
