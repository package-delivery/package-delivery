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
        //System.out.println(CsvReader.readCsvFile(filename));

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
                    ConvexHull ch = new ConvexHull();
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
            }
        }

    }
}