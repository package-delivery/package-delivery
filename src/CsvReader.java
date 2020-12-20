import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class CsvReader {

    private static double[][] distanceMatrix;
    private static City [] cityMatrix;
    
    public static City[] getCityMatrix(){
        return cityMatrix;
    }

    public static double[][] getDistanceMatrix(){
        return distanceMatrix;
    }

    public static boolean readCsvFile(String filename){

        try{
            //Reads values of CSV file and splits the lines into own parts in array
            String[] lines = Files.readString(Paths.get(filename)).split("\n");

            //initializes City array
            cityMatrix = new City[lines.length];
            int counter = 0;
            //for each city in csv file, a city gets created with
            //its name and counter as the id and added to the city matrix
            for (String s:lines[0].split(",")){
                cityMatrix[counter] = new City(s, counter);
                counter++;
            }

            //distance matrix gets initialized
            distanceMatrix = new double[lines.length][lines.length];
            //nested for loops to parse values from
            //csv file onto distance matrix
            for (int i = 1; i < lines.length; i++){
                String [] buffer = lines[i].split(",");
                for (int j = 1; j < buffer.length; j++){
                    distanceMatrix[i][j] = Double.parseDouble(buffer[j]);
                }
            }
        }
        //catches file not found exception
        //and returns false if not found
        catch (IOException e) {
            return false;
        }
        //returns true if file is found
        return true;
    }
}
