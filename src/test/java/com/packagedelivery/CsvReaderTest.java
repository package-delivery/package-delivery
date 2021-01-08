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
            assertTrue(CsvReader.readCsvFile("TSP.csv"));
        }

        @Test
        @DisplayName("Test return value if file is not present")
        void readCsvFileTestFail() {
            assertFalse(CsvReader.readCsvFile("test.csv"));
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

        @Test
        @DisplayName("Test return of DistanceMatrix")
        void getDistanceMatrixTestWithString() {
            CsvReader.readString("C,a,b,c,d,e,f,g,h,i,j,k,l,m\n" +
                    "a,0,17.5,64.5,40,64,96.5,94,94,28.5,54.5,97.5,103.5,113.5\n" +
                    "b,17.5,0,47,57.5,81.5,102.5,76.5,76.5,46,72,97,86,131\n" +
                    "c,64.5,47,0,104.5,128.5,161,94,90.75,93,119,144,102,178\n" +
                    "d,40,57.5,104.5,0,24,56.5,135.5,130.5,65.5,82.5,74,143.5,73.5\n" +
                    "e,64,81.5,128.5,24,0,32.5,136,149,80.5,75,50,179,49.5\n" +
                    "f,96.5,102.5,161,56.5,32.5,0,153,140,99.5,73.5,48.5,156,34\n" +
                    "g,94,76.5,94,135.5,136,153,0,53,63.5,79.5,104.5,61,142\n" +
                    "h,94,76.5,90.75,130.5,149,140,53,0,50.5,66.5,91.5,56,129\n" +
                    "i,28.5,46,93,56.5,80.5,99.5,63.5,50.5,0,26,51,66.5,88.5\n" +
                    "j,54.5,72,119,82.5,75,73.5,79.5,66.5,26,0,25,82.5,62.5\n" +
                    "k,97.5,97,144,74,50,48.5,104.5,91.5,51,25,0,107.5,37\n" +
                    "l,103.5,86,102,143.5,179,156,61,56,66.5,82.5,107.5,0,128.5\n" +
                    "m,113.5,131,178,73.5,49.5,34,142,129,88.5,62.5,37,128,0");
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

        @Test
        @DisplayName("Test return value of getCityMatrix")
        void getCityMatrixTestWithString() {
            CsvReader.readString("C,a,b,c,d,e,f,g,h,i,j,k,l,m\n" +
                    "a,0,17.5,64.5,40,64,96.5,94,94,28.5,54.5,97.5,103.5,113.5\n" +
                    "b,17.5,0,47,57.5,81.5,102.5,76.5,76.5,46,72,97,86,131\n" +
                    "c,64.5,47,0,104.5,128.5,161,94,90.75,93,119,144,102,178\n" +
                    "d,40,57.5,104.5,0,24,56.5,135.5,130.5,65.5,82.5,74,143.5,73.5\n" +
                    "e,64,81.5,128.5,24,0,32.5,136,149,80.5,75,50,179,49.5\n" +
                    "f,96.5,102.5,161,56.5,32.5,0,153,140,99.5,73.5,48.5,156,34\n" +
                    "g,94,76.5,94,135.5,136,153,0,53,63.5,79.5,104.5,61,142\n" +
                    "h,94,76.5,90.75,130.5,149,140,53,0,50.5,66.5,91.5,56,129\n" +
                    "i,28.5,46,93,56.5,80.5,99.5,63.5,50.5,0,26,51,66.5,88.5\n" +
                    "j,54.5,72,119,82.5,75,73.5,79.5,66.5,26,0,25,82.5,62.5\n" +
                    "k,97.5,97,144,74,50,48.5,104.5,91.5,51,25,0,107.5,37\n" +
                    "l,103.5,86,102,143.5,179,156,61,56,66.5,82.5,107.5,0,128.5\n" +
                    "m,113.5,131,178,73.5,49.5,34,142,129,88.5,62.5,37,128,0");
            assertEquals("a", CsvReader.getCityMatrix()[0].getCityName());
            assertEquals("m", CsvReader.getCityMatrix()[12].getCityName());
            assertEquals(12, CsvReader.getCityMatrix()[12].getId());
            assertEquals(0, CsvReader.getCityMatrix()[0].getId());
        }
    }
}
