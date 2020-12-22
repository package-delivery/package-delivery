package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CsvReaderTest {

    @Nested
    class readCsvReader {
        @Test
        @DisplayName("Test return value if file is present")
        void readCsvFileTest() {
            assertEquals(true, CsvReader.readCsvFile("TSP.csv"));
        }

        @Test
        @DisplayName("Test return value if file is not present")
        void readCsvFileTestFail() {
            assertEquals(false, CsvReader.readCsvFile("test.csv"));
        }
    }

    @Nested
    class getDistanceMatrix {
        @Test
        @DisplayName("Test return of DistanceMatrix")
        void getDistanceMatrixTest() {
            CsvReader.readCsvFile("TSP.csv");
            assertEquals(0, CsvReader.getDistanceMatrix()[0][0]);
            assertEquals(0, CsvReader.getDistanceMatrix()[1][1]);
            assertEquals(0, CsvReader.getDistanceMatrix()[12][12]);
            assertEquals(113.5, CsvReader.getDistanceMatrix()[12][0]);
            assertEquals(128.5, CsvReader.getDistanceMatrix()[2][4]);
        }
    }

    @Nested
    class getCityMatrix {
        @Test
        @DisplayName("Test return value of getCityMatrix")
        void getCityMatrixTest() {
            CsvReader.readCsvFile("TSP.csv");
            assertEquals("a", CsvReader.getCityMatrix()[0].getCityName());
            assertEquals("m", CsvReader.getCityMatrix()[12].getCityName());
            assertEquals(12, CsvReader.getCityMatrix()[12].getId());
            assertEquals(0, CsvReader.getCityMatrix()[0].getId());
        }
    }
}
