package com.packagedelivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvexHullTest {

    @Nested
    class checkConvexHull {
        @Test
        @DisplayName("Test returns correct ConvexHull values for first test")
        void checkConvexHullTest() {
            // Visualisierung auf https://www.geogebra.org/m/mfsbd7fb
            String coords = "[[5.7249919434758,9.274802780708],[-32.6037232017414,55.6686433744592],[56.1823781267804,53.0725585402919],[-42.4688455715772,-13.3872132143912],[36.7117418705256,-55.7033960113184],[-54.9300527755803,-62.1936080967366],[87.5950046202047,-45.0594481912324],[82.6624434352869,5.0449891081967],[-60.9010478941651,31.78466290012]]";
            ConvexHull ch = new ConvexHull(coords);
            assertEquals(44062, ch.getResult().getDistance(), 1);
            assertEquals(new ArrayList<City>(Arrays.asList(new City("[-54.9300527755803,-62.1936080967366]", 0), new City("[36.7117418705256,-55.7033960113184]" ,0), new City("[87.5950046202047,-45.0594481912324]", 0), new City("[82.6624434352869,5.0449891081967]", 0), new City("[56.1823781267804,53.0725585402919]", 0), new City("[5.7249919434758,9.274802780708]", 0), new City("[-32.6037232017414,55.6686433744592]", 0), new City("[-60.9010478941651,31.78466290012]", 0), new City("[-42.4688455715772,-13.3872132143912]", 0), new City("[-54.9300527755803,-62.1936080967366]", 0))), ch.getResult().getSortedCities());
        }

        @Test
        @DisplayName("Test returns correct ConvexHull values for second test")
        void checkConvexHullTest2() {
            // Visualisierung auf https://www.geogebra.org/calculator/aavz4gae
            String coords = "[[-6.9042207982024,-7.609411074344],[28.11586834799,74.2500473048803],[102.9713088979762,146.4789811689018],[108.6620733842324,94.8243496782682],[39.0596462061751,25.6596736145386]]";
            ConvexHull ch = new ConvexHull(coords);
            System.out.println(ch.getResult().getSortedCities());
            assertEquals(31354, ch.getResult().getDistance(), 1);
            assertEquals(new ArrayList<City>(Arrays.asList(new City("[-6.9042207982024,-7.609411074344]", 0),new City("[39.0596462061751,25.6596736145386]", 0),new City("[108.6620733842324,94.8243496782682]", 0),new City("[102.9713088979762,146.4789811689018]", 0),new City("[28.11586834799,74.2500473048803]", 0),new City("[-6.9042207982024,-7.609411074344]", 0))), ch.getResult().getSortedCities());
        }
    }
}
