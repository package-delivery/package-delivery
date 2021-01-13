package com.packagedelivery;

public class SimulatedAnnealing implements Algorithm {

    private double currentRoute;
    Cities bestRoute;

    public SimulatedAnnealing(String start) {
        NearestNeighbor nn = new NearestNeighbor(start);
        double[][] matrix = CsvReader.getDistanceMatrix();
        City[] cities = CsvReader.getCityMatrix();
        bestRoute = nn.getResult();
        System.out.println(bestRoute);
        //for (int j = 0; j < 100000; j++) {
            //System.out.println(route);
            Cities route = nn.getResult();
            currentRoute = nn.getResult().getDistance();
            double sigma = 500;
            //Math.random();
            while (sigma > 1) {
                // Calculate 2 random cities
                City r1 = route.getSortedCities().get((int) (Math.random() * (route.getSortedCities().size() - 1)) + 1), r2;
                do {
                    r2 = route.getSortedCities().get((int) (Math.random() * (route.getSortedCities().size() - 1)) + 1);
                } while (r1.equals(r2));

                // Switch the two cities
                route.getSortedCities().set(cities[r1.getId()].getId(), r2);
                route.getSortedCities().set(cities[r2.getId()].getId(), r1);

                // Check if route is ok, or if we have to switch back
                if (!checkNewRoute(route, sigma, matrix)) {
                    route.getSortedCities().set(cities[r1.getId()].getId(), r1);
                    route.getSortedCities().set(cities[r2.getId()].getId(), r2);
                }

                sigma = sigma * (1 - 0.00000005);
            }
            //System.out.println(currentRoute);
        //}
    }

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

    @Override
    public Cities getResult() {
        return bestRoute;
    }
}
