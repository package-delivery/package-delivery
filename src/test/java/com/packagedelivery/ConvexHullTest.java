package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvexHullTest {

    @Nested
    class checkNearestInsertion {
        @Test
        @DisplayName("Test returns correct ConvexHull values for tobiTsp.csv")
        void checkConvexHullTest() {
            CsvReader.readCsvFile("tobiTSP.csv");
            ConvexHull ch = new ConvexHull("[[0,3],[2,2],[4,4],[3,5],[2.088,5.209],[2.56,3.95],[1.548,2.961],[1.559,3.995],[0.683,5.12],[0.885,4.467]]",false);
            Cities result = ch.getResult();
            assertEquals(1466.6984435846798, result.getDistance());
            String expected = ch.getResult().getSortedCities().toString();
            assertEquals(expected, "[City{cityName='[2.0,2.0]', id=0}, City{cityName='[2.56,3.95]', id=0}, City{cityName='[4.0,4.0]', id=0}, City{cityName='[3.0,5.0]', id=0}, City{cityName='[2.088,5.209]', id=0}, City{cityName='[0.683,5.12]', id=0}, City{cityName='[0.885,4.467]', id=0}, City{cityName='[1.559,3.995]', id=0}, City{cityName='[0.0,3.0]', id=0}, City{cityName='[1.548,2.961]', id=0}, City{cityName='[2.0,2.0]', id=0}]");
        }
    }
}
