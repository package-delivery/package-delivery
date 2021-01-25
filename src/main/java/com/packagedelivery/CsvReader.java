package com.packagedelivery;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public abstract class CsvReader {

    private static double[][] distanceMatrix;
    private static City[] cityMatrix;

    /**
     * Returns all Cities in the csv file
     * Used to convert city name and id
     *
     * @return City[] Array with all cities
     */
    public static City[] getCityMatrix() {
        return cityMatrix;
    }

    /**
     * Returns the matrix with the distances in a two-dimensional array
     * @return double[][] adjazenzmatrix with distances
     */
    public static double[][] getDistanceMatrix() {
        return distanceMatrix;
    }

    /**
     * Reads a csv file with the name filename and parses csv content
     * @param filename Name of the file to open
     * @return boolean of the operation is successful or not
     */
    public static boolean readCsvFile(String filename){

        try{
            //Reads values of CSV file and splits the lines into own parts in array
            String[] lines = Files.readString(Paths.get("src/main/resources/" + filename)).trim().split("\n");

            //initializes com.project.City array
            cityMatrix = new City[lines.length];
            int counter = 0;
            //for each city in csv file, a city gets created with
            //its name and counter as the id and added to the city matrix
            for (String s:lines[0].trim().split(",")){
                cityMatrix[counter] = new City(s, counter-1);
                counter++;
            }
            // Remove fist element of com.project.City Array because it is only "CSV" or "X" or something like that
            cityMatrix = Arrays.copyOfRange(cityMatrix, 1, cityMatrix.length);

            //distance matrix gets initialized
            distanceMatrix = new double[lines.length-1][lines.length-1];
            //nested for loops to parse values from
            //csv file onto distance matrix
            for (int i = 1; i < lines.length; i++){
                String [] buffer = lines[i].split(",");
                for (int j = 1; j < buffer.length; j++){
                    distanceMatrix[i-1][j-1] = Double.parseDouble(buffer[j]);
                }
            }
            return true;
        }
        //catches file not found exception
        //and returns false if not found
        catch(IOException e) {
            return false;
        }

    }


    /**
     * Parses String and writes data in arrays distanceMatrix and cityMatrix
     * @param string csv values as a string (adjazenzmatrix)
     * @return boolean if the operation is successful
     */
    public static boolean readString(String string){
        String[] lines = string.split("\n");

        //initializes com.project.City array
        cityMatrix = new City[lines.length];
        int counter = 0;
        //for each city in csv file, a city gets created with
        //its name and counter as the id and added to the city matrix
        for (String s:lines[0].trim().split(",")){
            cityMatrix[counter] = new City(s, counter-1);
            counter++;
        }
        // Remove fist element of com.project.City Array because it is only "CSV" or "X" or something like that
        cityMatrix = Arrays.copyOfRange(cityMatrix, 1, cityMatrix.length);

        //distance matrix gets initialized
        distanceMatrix = new double[lines.length-1][lines.length-1];
        //nested for loops to parse values from
        //csv file onto distance matrix
        for (int i = 1; i < lines.length; i++){
            String [] buffer = lines[i].split(",");
            for (int j = 1; j < buffer.length; j++){
                distanceMatrix[i-1][j-1] = Double.parseDouble(buffer[j]);
            }
        }
        return true;
    }
}