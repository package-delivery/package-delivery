package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;

public class SimulatedAnnealing implements Algorithm, Displayable {

    private double currentRoute;
    private Cities bestRoute;
    private String visualized;

    public SimulatedAnnealing(String start) {
        this(start, false);
    }

    /**
     * Constructor, which anneals the optimal route.
     * Important: It is a heuristic algorithm, therefore the result won't be optimal and the algorithm is based on randomness so the result won't always be the same.
     * @param start name of the starting position
     */
    public SimulatedAnnealing(String start, boolean visualization) {

        // start stopwatch
        Instant starts = Instant.now();

        if (visualization) visualized = "";

        NearestNeighbor nn = new NearestNeighbor(start);
        double[][] matrix = CsvReader.getDistanceMatrix();
        bestRoute = nn.getResult();
        Cities route = nn.getResult();
        currentRoute = nn.getResult().getDistance();
        int breakpoint = 0, v = 0, mod = 1;
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
                breakpoint++;
               // if (breakpoint >= 250) break; // THIS CAN BE UNCOMMENTED IF YOU WANT TO MAKE THE ALGO FASTER, BUT NOT BETTER!
            } else {
                //System.out.println(breakpoint);
                breakpoint = 0;
                v++;
                if (visualization && v%mod==0) {
                    // Add Cities to visualized string
                    //visualized += route.getSortedCities().toString() + "\n";
                    for (City c : route.getSortedCities())
                        visualized += c.getCityName()+";";
                    visualized += "\n";

                    if (v > 10000) mod = 1000;
                    else if (v > 1000) mod = 100;
                    else if (v > 100) mod = 10;
                }
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

    @Override
    public String getVisualization() {
        return visualized;
    }
}
