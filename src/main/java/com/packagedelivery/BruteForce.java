package com.packagedelivery;

public class BruteForce implements Algorithm{

    //1 has to be added to the initialisation because we need to return home
    private static City [] citiesArray = new City[CsvReader.getCityMatrix().length + 1];
    private Cities testCity = new Cities();

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) System.out.println(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    @Override
    public Cities getResult(){

        double distance = 0;
        double test = 0;
        String testString = "";


        //Writes each City object to matching part in array
        for(int i = 0; i < CsvReader.getCityMatrix().length; i++){
            citiesArray[i] = CsvReader.getCityMatrix()[i];
            testString += CsvReader.getCityMatrix()[i].getCityName();
        }

        //sets starting point to last place in array, so the distance home gets returned too
        citiesArray[CsvReader.getCityMatrix().length] = CsvReader.getCityMatrix()[0];
        testString += CsvReader.getCityMatrix()[0].getCityName();


        //to add: break if curr path is longer that old path
        //loops through all cities and adds length together
        for(int i = 0; i < citiesArray.length - 1; i++){
            distance += CsvReader.getDistanceMatrix()[citiesArray[i].getId()][citiesArray[i + 1].getId()];
        }

        //for(int i = 0; i < citiesArray.length; i++)
        //System.out.println(citiesArray[i].getCityName());

        //to remove first and last position from teststring
        testString = testString.substring(1, testString.length()-1);

        permutation("", testString);

        return testCity;

    }
}


