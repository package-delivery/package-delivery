package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NearestNeighborTest {

    @Nested
    class checkNearestNeighbor {
        @Test
        @DisplayName("Test returns correct NearestNeighbor values for tobiTsp.csv")
        void checkNearestNeighborTest() {
            CsvReader.readCsvFile("tobiTSP.csv");
            NearestNeighbor nn = new NearestNeighbor("a");
            Cities result = nn.getResult();
            City start = new City("a", 0);


            Cities expected = new Cities(29, new ArrayList<City>(Arrays.asList(start, new City("b", 1), new City("c", 2), new City("d", 3), new City("e", 4),start)));
            assertEquals(29, result.getDistance());
            //String expected = "Cities{distance=29.0, time=0.0, sortedCities=[City{cityName='a', id=0}, City{cityName='b', id=1}, City{cityName='c', id=2}, City{cityName='d', id=3}, City{cityName='e', id=4}, City{cityName='a', id=0}]}";
            //assertEquals(expected, result.toString());
            assertEquals(result, expected);


            //now with another startposition
            /*
            nn = new NearestNeighbor("c");
            result = nn.getResult();
            assertEquals(31, result.getDistance());
            expected = "Cities{distance=31.0, time=0.0, sortedCities=[City{cityName='c', id=2}, City{cityName='a', id=0}, City{cityName='b', id=1}, City{cityName='e', id=4}, City{cityName='d', id=3}, City{cityName='c', id=2}]}";
            assertEquals(expected, result.toString());
            */
        }



    }



}
