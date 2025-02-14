package com.packagedelivery;

import java.util.Optional;

/**
 * Main Class for the Console Application
 */
public class Application {
    /**
     * Main Method
     * @param args cli parameter
     */
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
            switch(algoName.get()) {
                case NEAREST_NEIGHBOR:
                    NearestNeighbor nn = new NearestNeighbor(Console.getStartingPosition());
                    System.out.println(nn.getResult());
                    break;
                case CONVEX_HULL:
                    ConvexHull ch = new ConvexHull(Console.getCoordinates(), false);
                    System.out.println(ch.getResult());
                    break;
                case NEAREST_INSERTION:
                    NearestInsertion ni = new NearestInsertion(Console.getStartingPosition(), false);
                    System.out.println(ni.getResult());
                    break;
                case BRUTE_FORCE:
                    BruteForce bf = new BruteForce(Console.getStartingPosition());
                    System.out.println(bf.getResult());
                    break;
                case SIMULATED_ANNEALING:
                    SimulatedAnnealing sa = new SimulatedAnnealing(Console.getStartingPosition(), false);
                    System.out.println(sa.getResult());
                    break;
            }
        }
    }
}