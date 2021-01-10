package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class NearestNeighborTest {

    @Nested
    class CheckNearestNeighbor {
        @Test
        @DisplayName("Test returns correct NearestNeighbor values for tobiTsp.csv")
        void checkNearestNeighborTest() {
            CsvReader.readCsvFile("tobiTSP.csv");
            NearestNeighbor nn = new NearestNeighbor("a");
            Cities result = nn.getResult();
            City start = new City("a", 0);
            Cities expected = new Cities(29, new ArrayList<City>(Arrays.asList(start, new City("b", 1), new City("c", 2), new City("d", 3), new City("e", 4),start)));
            assertEquals(result, expected);

            //now with another startposition
            nn = new NearestNeighbor("c");
            result = nn.getResult();
            start = new City("c", 2);
            expected = new Cities(31, new ArrayList<City>(Arrays.asList(start, new City("a", 0), new City("b", 1), new City("e", 4), new City("d", 3), start)));
            assertEquals(expected, result);

        }
    }

    @Nested
    class RandomTests {
        @Test
        @DisplayName("Test returns correct values for 100 random generated csv files")
        void checkNearestNeighborTest() {
            Path python = Paths.get("src/main/resources");
            ProcessBuilder builder = new ProcessBuilder("python", "random_csv_generator.py");
            builder.directory(new File(String.valueOf(python.toAbsolutePath().toFile())));
            try {
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Sleep one second, otherwise java is to fast to realize that python created new files
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Start with tests
            for (int i = 0; i < 100; i++) {
                CsvReader.readCsvFile("zTSP_"+i+".csv");
                NearestNeighbor nn = new NearestNeighbor(CsvReader.getCityMatrix()[0].getCityName());
                Reference ref = new Reference();
                assertEquals(ref.reference().getDistance(), nn.getResult().getDistance(), 0.000001, "Failed for file zTSP_"+i+".csv");
            }
        }
    }
}

// We are using this as a refernce for our test cases
// Got from following website: https://www.sanfoundry.com/java-program-implement-traveling-salesman-problem-using-nearest-neighbour-algorithm/
class Reference {
    private int numberOfNodes;
    private Stack<Integer> stack;

    public Reference() {
        stack = new Stack<Integer>();
    }

    public double tsp(double[][] adjacencyMatrix) {
        ArrayList<Integer> finalpath = new ArrayList<>();
        numberOfNodes = adjacencyMatrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        double min = Double.MAX_VALUE, distance = 0;
        boolean minFlag = false;
        finalpath.add(0);

        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (adjacencyMatrix[element][i] > 1 && visited[i] == 0) {
                    if (min > adjacencyMatrix[element][i]) {
                            min = adjacencyMatrix[element][i];
                            dst = i;
                            minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                distance += min;
                visited[dst] = 1;
                stack.push(dst);
                finalpath.add(dst-1);
                minFlag = false;
                continue;
            }
            stack.pop();
        }
        distance += adjacencyMatrix[finalpath.get(finalpath.size()-1)+1][1];
        return distance;
    }

    public Cities reference() {
        double[][] temp = CsvReader.getDistanceMatrix();
        double[][] matrix = new double[temp.length+1][temp.length+1];
        for (int i = 0; i <= temp.length; i++) {
            for (int x = 0; x <= temp.length; x++) {
                if (i==0 || x==0) matrix[i][x]=0;
                else matrix[i][x] = temp[i-1][x-1];
            }
        }
        Reference r = new Reference();
        return new Cities(r.tsp(matrix), null);
    }

    public static void main(String... arg) {
        CsvReader.readCsvFile(Console.getFilename());
        Reference r = new Reference();
        System.out.println("the citys are visited as follows");
        r.reference();
    }

}
