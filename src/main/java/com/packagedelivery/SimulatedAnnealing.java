package com.packagedelivery;

public class SimulatedAnnealing implements Algorithm {

    private double distance;

    public SimulatedAnnealing(String start) {
        NearestNeighbor nn = new NearestNeighbor(start);
        Cities firstRoute = nn.getResult();
        
    }

    @Override
    public Cities getResult() {
        return null;
    }
}
