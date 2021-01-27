package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Nested
class SimulatedAnnealingTest {
    @Test
    @DisplayName("Test returns correct Simulated Annealing values for tobiTsp.csv")
    void checkConvexHullTest() {
        CsvReader.readCsvFile("tobiTSP.csv");
        SimulatedAnnealing sa = new SimulatedAnnealing("a");
        Cities result = sa.getResult();
        assertEquals(29.0, result.getDistance());
        String expected = sa.getResult().getSortedCities().toString();
        assertEquals(expected, "[City{cityName='a', id=0}, City{cityName='e', id=4}, City{cityName='d', id=3}, City{cityName='c', id=2}, City{cityName='b', id=1}, City{cityName='a', id=0}]");
    }
}

