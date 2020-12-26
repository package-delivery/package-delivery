package com.packagedelivery;
import java.util.ArrayList; // import the ArrayList class

public class BruteForce implements Algorithm{

    private static ArrayList<String> permutationsArray = new ArrayList<String>();

    //to add: break if curr path is longer that old path

    //1 has to be added to the initialisation because we need to return home
    private static City [] citiesArray = new City[CsvReader.getCityMatrix().length + 1];
    private Cities testCity = new Cities();

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) permutationsArray.add(prefix); //System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    @Override
    public Cities getResult(){

        double distance = 0;
        Cities bestDistance = new Cities();
        double test = 0;
        String testString = "";

        // Initialize bestDistance
        bestDistance.setDistance(Double.MAX_VALUE);

        //Writes each City object to matching part in array
        for(int i = 0; i < CsvReader.getCityMatrix().length; i++){
            citiesArray[i] = CsvReader.getCityMatrix()[i];
            testString += CsvReader.getCityMatrix()[i].getId();
        }

        //sets starting point to last place in array, so the distance home gets returned too
        citiesArray[CsvReader.getCityMatrix().length] = CsvReader.getCityMatrix()[0];
        testString += CsvReader.getCityMatrix()[0].getId();

        permutation("", testString.substring(1, testString.length()-1));

        //to add "home" to permutationsArray
        for(int i = 0; i < permutationsArray.size(); i++){
            permutationsArray.set(i, testString.charAt(0) + permutationsArray.get(i) + testString.charAt(0));
        }


        for(int i = 0; i < permutationsArray.size(); i++){
            //System.out.println(permutationsArray.get(i));
            for(int j = 0; j < permutationsArray.get(i).length() - 1; j++){
                distance += CsvReader.getDistanceMatrix()[Integer.parseInt(String.valueOf(permutationsArray.get(i).charAt(j)))][Integer.parseInt(String.valueOf(permutationsArray.get(i).charAt(j + 1)))];
            }
            //System.out.println(distance);
            if (distance < bestDistance.getDistance()) {
                System.out.println(permutationsArray.get(i));
                bestDistance.setDistance(distance);
                bestDistance.setSortedCities(permutationsArray.get(i));
            }
            distance = 0;
        }

        //System.out.println("Die beste Route ist mit sicherheit "+bestDistance+"km lang");

        return bestDistance;

    }
}


