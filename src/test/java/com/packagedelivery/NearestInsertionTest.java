package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NearestInsertionTest {

    @Nested
    class checkNearestInsertion {
        @Test
        @DisplayName("Test returns correct NearestInsertion values for tobiTsp.csv")
        void checkNearestInsertionTest() {
            CsvReader.readCsvFile("tobiTSP.csv");
            NearestInsertion ni = new NearestInsertion("a");
            Cities result = ni.getResult();
            assertEquals(29, result.getDistance());
            String expected = "Cities{distance=29.0, time=0.0, sortedCities=[City{cityName='a', id=0}, City{cityName='b', id=1}, City{cityName='c', id=2}, City{cityName='d', id=3}, City{cityName='e', id=4}, City{cityName='a', id=0}]}";
            assertEquals(expected, result.toString());

            //now with another startposition
            ni = new NearestInsertion("c");
            result = ni.getResult();
            assertEquals(31, result.getDistance());
            expected = "Cities{distance=31.0, time=0.0, sortedCities=[City{cityName='c', id=2}, City{cityName='a', id=0}, City{cityName='b', id=1}, City{cityName='e', id=4}, City{cityName='d', id=3}, City{cityName='c', id=2}]}";
            assertEquals(expected, result.toString());

        }
    }
}
