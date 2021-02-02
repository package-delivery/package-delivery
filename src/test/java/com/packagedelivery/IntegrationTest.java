package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    @Nested
    class CheckApp {
        @Test
        @DisplayName("Test returns correct Value")
        void checkProgram() {

            //instead of input
            CsvReader.readCsvFile("tobiTSP.csv");

            //instead of selecting input, write it here
            String algorithmName = "NEAREST_NEIGHBOR";

            switch(algorithmName) {
                case "NEAREST_NEIGHBOR":
                    NearestNeighbor nn = new NearestNeighbor("a");
                    Cities result = nn.getResult();
                    City start = new City("a", 0);
                    Cities expected = new Cities(29, new ArrayList<City>(Arrays.asList(start, new City("b", 1), new City("c", 2), new City("d", 3), new City("e", 4),start)));
                    assertEquals(result, expected);
                    System.out.println(nn.getResult());
                    break;
                case "CONVEX_HULL":
                    ConvexHull ch = new ConvexHull("[[2.36,7.93],[4.74,8.47],[6.74,5.79],[2.1,3.29],[12,2.43],[8.94,2.87],[10.94,6.19],[11.48,11.87]]", false);
                    System.out.println(ch.getVisualization());
                    System.out.println(ch.getResult());
                    break;
                case "NEAREST_INSERTION":
                    NearestInsertion ni = new NearestInsertion("a");
                    result = ni.getResult();
                    assertEquals(29, result.getDistance());
                    String stringExpected = "Cities{distance=29.0, time=0.0, sortedCities=[City{cityName='a', id=0}, City{cityName='e', id=4}, City{cityName='d', id=3}, City{cityName='c', id=2}, City{cityName='b', id=1}, City{cityName='a', id=0}]}";
                    assertEquals(stringExpected, result.toString());
                    System.out.println(ni.getResult());
                    break;
                case "BRUTE_FORCE":
                    BruteForce bf = new BruteForce(Console.getStartingPosition());
                    System.out.println(bf.getResult());
                    break;
                case "SIMULATED_ANNEALING":
                    SimulatedAnnealing sa = new SimulatedAnnealing(Console.getStartingPosition(), false);
                    System.out.println(sa.getResult());
                    break;
                default:
                    System.out.println("No Algorithm found");
            }
        }
    }
}

