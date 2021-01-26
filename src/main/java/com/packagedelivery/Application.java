package com.packagedelivery;

import java.util.Optional;

public class Application {
    public static void main(String[] args) {

        boolean validInput = false;
        while (!validInput) {
            String filename = Console.getFilename();
            if (!CsvReader.readCsvFile(filename)) {
                System.err.println("Der eingegebene Dateiname ist nicht korrekt! Bitte verusche es erneut:");
            } else {
                validInput = true;
            }
        }

        Optional<AlgorithmName> algoName = Console.getSelectedAlgorithm();
        if(algoName.isEmpty()) {
            System.err.println("No Algorithm found");
        }else{
            long startTime = System.nanoTime();
            switch(algoName.get()) {
                case NEAREST_NEIGHBOR:
                    NearestNeighbor nn = new NearestNeighbor(Console.getStartingPosition());
                    System.out.println(nn.getResult());
                    break;
                case CONVEX_HULL:
                    ConvexHull ch = new ConvexHull("[[0,3],[2,2],[4,4],[3,5],[2.088,5.209],[2.56,3.95],[1.548,2.961],[1.559,3.995],[0.683,5.12],[0.885,4.467]]", true);
                    System.out.println(ch.getVisualization());
                    System.out.println(ch.getResult());
                    break;
                case NEAREST_INSERTION:
                    NearestInsertion ni = new NearestInsertion(Console.getStartingPosition(), true);
                    System.out.println(ni.getVisualization());
                    System.out.println(ni.getResult());
                    break;
                case BRUTE_FORCE:
                    BruteForce bf = new BruteForce(Console.getStartingPosition());
                    System.out.println(bf.getResult());
                    break;
                case SIMULATED_ANNEALING:
                    SimulatedAnnealing sa = new SimulatedAnnealing(Console.getStartingPosition(), false);
                    System.out.println(sa.getResult());
                    //System.out.println(sa.getVisualization());
                    break;
            }
        }
    }
}