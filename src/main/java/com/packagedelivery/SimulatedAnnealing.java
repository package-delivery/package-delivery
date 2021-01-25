package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;

public class SimulatedAnnealing implements Algorithm {

    private double currentRoute;
    Cities bestRoute;

    /**
     * Constructor, which anneals the optimal route.
     * Important: It is a heuristic algorithm, therefore the result won't be optimal and the algorithm is based on randomness so the result won't always be the same.
     * @param start name of the starting position
     */
    public SimulatedAnnealing(String start) {

        // start stopwatch
        Instant starts = Instant.now();

        NearestNeighbor nn = new NearestNeighbor(start);
        double[][] matrix = CsvReader.getDistanceMatrix();
        bestRoute = nn.getResult();
        Cities route = nn.getResult();
        currentRoute = nn.getResult().getDistance();
        double sigma = 1000;
        while (sigma > 1) {
            // Calculate 2 random cities
            City r1 = route.getSortedCities().get((int) (Math.random() * (route.getSortedCities().size() - 2)) + 1), r2;
            do {
                r2 = route.getSortedCities().get((int) (Math.random() * (route.getSortedCities().size() - 2)) + 1);
            } while (r1.equals(r2));

            // Switch the two cities
            int i1 = route.getSortedCities().indexOf(r1), i2 = route.getSortedCities().indexOf(r2);
            route.getSortedCities().set(i1, r2);
            route.getSortedCities().set(i2, r1);

            // Check if route is ok, or if we have to switch back
            if (!checkNewRoute(route, sigma, matrix)) {
                route.getSortedCities().set(i1, r1);
                route.getSortedCities().set(i2, r2);
            }

            // Reduce sigma
            sigma = sigma * (1 - 0.00005);
        }

        Instant ends = Instant.now();
        this.bestRoute.setTime(Duration.between(starts, ends).toMillis());
    }

    /**
     * Checks if the new route is "better" than the old route. Better is written in quotes because sometimes also a worse route gets accepted.
     * @param route current route as Cities object
     * @param sigma tolerance value as double
     * @param matrix adjacent matrix as double[][]
     * @return boolean
     */
    private boolean checkNewRoute(Cities route, double sigma, double[][] matrix) {
        double newDist = NearestInsertion.getWholeDistance(route.getIDs(), matrix);
        if (newDist < currentRoute) {
            currentRoute = newDist;
            if (currentRoute < bestRoute.getDistance()) {
                bestRoute.setDistance(currentRoute);
                bestRoute.setSortedCities(route.getSortedCities());
            }
            return true;
        }

        double delta = newDist - currentRoute;
        double calc = Math.exp(-delta/sigma);
        if (calc > Math.random()) {
            currentRoute = newDist;
            if (currentRoute < bestRoute.getDistance()) {
                bestRoute.setDistance(currentRoute);
                bestRoute.setSortedCities(route.getSortedCities());
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a Cities object with an annealed route.
     * @return Cities object
     */
    @Override
    public Cities getResult() {
        return bestRoute;
    }
}
