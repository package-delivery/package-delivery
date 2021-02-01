package com.packagedelivery;

import java.util.Optional;
import java.util.Scanner;

/**
 * Implements all the Console activities
 */
public abstract class Console {
    /**
     * Asks use via console which algorithm they want to use
     * @return Optional of AlgorithmName with the algorithm selected
     */
    public static Optional<AlgorithmName> getSelectedAlgorithm() {
        System.out.println("Select Algorithm:");
        System.out.println("[1] Nearest Neighbor");
        System.out.println("[2] Brute Force");
        System.out.println("[3] Convex Hull");
        System.out.println("[4] Nearest Insertion");
        System.out.println("[5] Simulated Annealing");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        if(input.equals("1")){
            return Optional.of(AlgorithmName.NEAREST_NEIGHBOR);
        }else if(input.equals("2")) {
            return Optional.of(AlgorithmName.BRUTE_FORCE);
        }else if(input.equals("3")) {
            return Optional.of(AlgorithmName.CONVEX_HULL);
        }else if(input.equals("4")) {
            return Optional.of(AlgorithmName.NEAREST_INSERTION);
        }else if(input.equals("5")) {
            return Optional.of(AlgorithmName.SIMULATED_ANNEALING);
        }else {
            return Optional.empty();
        }
    }

    /**
     * Asks the user to select a starting point
     * @return String with the starting point
     */
    public static String getStartingPosition() {
        System.out.println("In welcher Stadt möchten Sie starten?");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    /**
     * Asks the user to insert filename of dataset
     * @return String filename of the data
     */
    public static String getFilename() {
        System.out.println("Input filename:");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        return input;
    }
}
