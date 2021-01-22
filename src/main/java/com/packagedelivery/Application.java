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
            switch(algoName.get()) {
                case NEAREST_NEIGHBOR:
                    NearestNeighbor nn = new NearestNeighbor(Console.getStartingPosition());
                    System.out.println(nn.getResult());
                    break;
                case CONVEX_HULL:
                    ConvexHull ch = new ConvexHull("[[0, 3], [2, 2], [4, 4], [3, 5]]");
                    System.out.println(ch.getResult());
                    break;
                case BRUTE_FORCE:
                    BruteForce bf = new BruteForce();
                    System.out.println(bf.getResult());
                    break;
                case NEAREST_INSERTION:
                    NearestInsertion ni = new NearestInsertion(Console.getStartingPosition());
                    System.out.println(ni.getResult());
                    break;
                case BETTER_BRUTE_FORCE:
                    BetterBruteForce bbf = new BetterBruteForce(Console.getStartingPosition());
                    System.out.println(bbf.getResult());
                    break;
                case SIMULATED_ANNEALING:
                    SimulatedAnnealing sa = new SimulatedAnnealing(Console.getStartingPosition());
                    System.out.println(sa.getResult());
                    break;
            }
        }
    }
}