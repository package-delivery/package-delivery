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
        void checkNearestNeighborTobiTest() {
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

            //now with another startposition
            nn = new NearestNeighbor("e");
            result = nn.getResult();
            start = new City("e", 4);
            expected = new Cities(29, new ArrayList<City>(Arrays.asList(start, new City("a", 0), new City("b", 1),new City("c", 2), new City("d", 3), start)));
            assertEquals(expected, result);
        }

        @Test
        @DisplayName("Tests returns correct results for TSP.csv")
        void checkNearestNeighborMediumTest() {
            CsvReader.readCsvFile("mediumTSP.csv");
            NearestNeighbor nn = new NearestNeighbor("a");
            Cities result = nn.getResult();
            City start = new City("a", 0);
            Cities expected = new Cities(496.75, new ArrayList<City>(Arrays.asList(start, new City("b", 1), new City("c", 2), new City("h", 7), new City("g", 6), new City("d", 3), new City("e", 4), new City("f", 5), start)));
            assertEquals(expected, result);
        }
    }
    /*
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
    }*/
}
