package com.packagedelivery;

import java.util.Optional;
import java.util.Scanner;

public abstract class Console {
    public static Optional<AlgorithmName> getSelectedAlgorithm() {
        System.out.println("Select Algorithm:");
        System.out.println("[1] Nearest Neighbor");
        System.out.println("[2] Brute Force");
        System.out.println("[3] Convex Hull");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        if(input.equals("1")){
            return Optional.of(AlgorithmName.NEAREST_NEIGHBOR);
        }else if(input.equals("2")) {
            return Optional.of(AlgorithmName.BRUTE_FORCE);
        }else if(input.equals("3")) {
            return Optional.of(AlgorithmName.CONVEX_HULL);
        }else {
            return Optional.empty();
        }
    }
    public static String getStartingPosition() {
        System.out.println("In welcher Stadt möchten Sie starten?");
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    public static String getFilename() {
        System.out.println("Input filename:");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        // TODO: Check if file exists
        return input;
    }
}
