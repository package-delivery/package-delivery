package com.packagedelivery;

import java.util.ArrayList;

public class SimulatedAnnealing implements Algorithm {

    private double bestDistance, currentRoute;
    ArrayList<City> sortedCities;

    public SimulatedAnnealing(String start) {
        NearestNeighbor nn = new NearestNeighbor(start);
        Cities route = nn.getResult();
        double[][] matrix = CsvReader.getDistanceMatrix();
        City[] cities = CsvReader.getCityMatrix();
        bestDistance = route.getDistance();
        currentRoute = route.getDistance();
        double sigma = 1000;
        while (sigma > 1) {
            // Calculate 2 random cities
            City r1 = route.getSortedCities().get((int) (Math.random()*(route.getSortedCities().size()-2))+1), r2;
            do {
                r2 = route.getSortedCities().get((int) (Math.random() * (route.getSortedCities().size() - 2)) + 1);
            } while (r1.equals(r2));

            // Switch the two cities
            route.getSortedCities().set(cities[r1.getId()].getId(), r2);
            route.getSortedCities().set(cities[r2.getId()].getId(), r1);

            // Check if route is ok, or if we have to switch back
            if (!checkNewRoute(route, sigma, matrix)) {
                route.getSortedCities().set(cities[r1.getId()].getId(), r1);
                route.getSortedCities().set(cities[r2.getId()].getId(), r2);
            }

            sigma = sigma*(1-0.000005);
        }
        sortedCities = route.getSortedCities();
        //System.out.println(bestDistance);
    }

    private boolean checkNewRoute(Cities route, double sigma, double[][] matrix) {
        double newDist = NearestInsertion.getWholeDistance(route.getIDs(), matrix);
        if (newDist < currentRoute) {
            currentRoute = newDist;
            if (currentRoute < bestDistance)
                bestDistance = currentRoute;
            return true;
        }

        double delta = newDist - currentRoute;
        double calc = Math.exp(-delta/sigma);
        if (calc > Math.random()) {
            currentRoute = newDist;
            if (currentRoute < bestDistance)
                bestDistance = currentRoute;
            return true;
        }
        return false;
    }

    @Override
    public Cities getResult() {
        return new Cities(bestDistance, sortedCities);
    }
}
