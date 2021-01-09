package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
        @DisplayName("Test returns correct values for random generated csv files")
        void checkNearestNeighborTest() {

        }
    }
}
