package com.packagedelivery;

public class BruteForce implements Algorithm{

    //1 has to be added to the initialisation because we need to return home
    private static City [] citiesArray = new City[CsvReader.getCityMatrix().length + 1];
    private Cities testCity = new Cities();

    @Override
    public Cities getResult(){

        double distance = 0;
        double test = 0;


        //Writes each City object to matching part in array
        for(int i = 0; i < CsvReader.getCityMatrix().length; i++){
            citiesArray[i] = CsvReader.getCityMatrix()[i];
        }

        //sets starting point to last place in array, so the distance home gets returned too
        citiesArray[CsvReader.getCityMatrix().length] = CsvReader.getCityMatrix()[0];


        //to add: break if curr path is longer that old path
        for(int i = 0; i < citiesArray.length - 1; i++){
            distance += CsvReader.getDistanceMatrix()[citiesArray[i].getId()][citiesArray[i + 1].getId()];
        }

        return testCity;

    }
}


