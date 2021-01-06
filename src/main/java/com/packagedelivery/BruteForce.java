package com.packagedelivery;
import java.util.ArrayList; // import the ArrayList class

public class BruteForce implements Algorithm{

    private static ArrayList<String> permutationsArray = new ArrayList<>();
    private static ArrayList<City> sortedCities;

    //to add: break if curr path is longer that old path

    //1 has to be added to the initialisation because we need to return home
    //private static City [] citiesArray = new City[CsvReader.getCityMatrix().length + 1];
    private Cities finalCity = new Cities();

    //calculates permutations
    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) permutationsArray.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    //gets ID from city as String and changes it to the actual city Object
    private static void setSortedCities(String bestRoute) {
        City[] cities = CsvReader.getCityMatrix();
        sortedCities = new ArrayList<>();
        for (int i = 0; i < bestRoute.length(); i++) {
            sortedCities.add(cities[Integer.parseInt(String.valueOf(bestRoute.charAt(i)))]);
        }
    }

    @Override
    public Cities getResult(){

        double distance = 0;
        double bestDistance = Double.MAX_VALUE;
        String bestPath = "";
        String initialPath = "";

        //Writes each City object to matching part in array
        for(int i = 0; i < CsvReader.getCityMatrix().length; i++){
            initialPath += CsvReader.getCityMatrix()[i].getId();
        }

        //sets starting point to last place in array, so the distance home gets returned too
        initialPath += CsvReader.getCityMatrix()[0].getId();

        permutation("", initialPath.substring(1, initialPath.length()-1));

        System.out.println(permutationsArray);
        //to add "home" to permutationsArray
        for(int i = 0; i < permutationsArray.size(); i++){
            permutationsArray.set(i, initialPath.charAt(0) + permutationsArray.get(i) + initialPath.charAt(0));
        }
        System.out.println(permutationsArray);

        for (String s : permutationsArray) {
            //System.out.println(permutationsArray.get(i));
            for (int j = 0; j < s.length() - 1; j++) {
                distance += CsvReader.getDistanceMatrix()[Integer.parseInt(String.valueOf(s.charAt(j)))][Integer.parseInt(String.valueOf(s.charAt(j + 1)))];
            }
            //System.out.println(distance);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestPath = s;
            }
            distance = 0;
        }

        //System.out.println("Die beste Route ist mit sicherheit "+bestDistance+"km lang");
        setSortedCities(bestPath);
        finalCity.setDistance(bestDistance);
        finalCity.setSortedCities(sortedCities);

        return finalCity;

    }
}