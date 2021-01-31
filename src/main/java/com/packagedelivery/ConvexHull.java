package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class ConvexHull implements Algorithm, Displayable{
    /**
     * Inner class Point with implementation of haversine_distance
     */
    class Point {
        protected double x, y;

        public Point() {}

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistanceTo(Point p) {
            // Radius of the Earth in miles
            double R = 3958.8;
            // Convert degrees to radians
            double rlat1 = this.x * (Math.PI/180);
            // Convert degrees to radians
            double rlat2 = p.x * (Math.PI/180);
            // Radian difference (latitudes)
            double difflat = rlat2-rlat1;
            // Radian difference (longitudes)
            double difflon = (p.y-this.y) * (Math.PI/180);
            double d =  2 * R * Math.asin(Math.sqrt(Math.sin(difflat/2)*Math.sin(difflat/2)+Math.cos(rlat1)*Math.cos(rlat2)*Math.sin(difflon/2)*Math.sin(difflon/2)));
            // Convert miles to km
            return d*1.609344;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }
    }

    private Cities sortedCities;
    private String visualized;

    public ConvexHull(String start) {
        this(start, false);
    }

    /**
     * constructor which calculates the convex hull route
     * Important: It is a heuristic algorithm, therefore the result won't be optimal.
     * @param s Coordinates as string
     * @param visualization boolean to enable/disable visualization
     */
    public ConvexHull(String s, boolean visualization) {

        //start stopwatch
        Instant starts = Instant.now();

        // Starts visualization
        if (visualization) visualized = "";

        // Splits the string and converts it to ArrayList<Point>
        String[] points = s.replaceAll("\\[", "").split("],");
        points[points.length-1] = points[points.length-1].replaceAll("]]", "");
        ArrayList<Point> coords = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            String[] buf = points[i].split(",");
            coords.add(new Point(Double.parseDouble(buf[0]), Double.parseDouble(buf[1])));
        }

        // Search the point with the smallest y coordinate
        Point min = new Point(0,Double.MAX_VALUE);
        for (Point p : coords)
            if (p.y < min.y) min = new Point(p.x, p.y);

        // Now we are sorting the points based on there angle to the x-axes
        // We use an insane useful data structure: TreeMap
        // and trigonometry
        TreeMap<Double, Point> sortedPoints = new TreeMap<>();
        sortedPoints.put(0.0, min);
        for (Point p : coords) {
            if (p.equals(min)) continue;
            double b = p.x - min.x;
            double c = p.y - min.y;
            double erg = Math.atan(c/b);
            if (erg < 0) erg += Math.PI;
            sortedPoints.put(erg, p);
        }

        // We put the sorted values into an ArrayList
        ArrayList<Point> hull = new ArrayList<>();
        ArrayList<Point> sortedPoints2 = new ArrayList<>();
        for (double angle : sortedPoints.keySet())
            sortedPoints2.add(sortedPoints.get(angle));

        // We start creating the convex hull
        hull.add(sortedPoints2.get(0));
        hull.add(sortedPoints2.get(1));
        if (visualization) visualized += pointsToString(hull);
        for (int i = 2; i < sortedPoints2.size(); i++) {
            while (!clock(hull.get(hull.size()-2), hull.get(hull.size()-1), sortedPoints2.get(i))) {
                hull.remove(hull.size()-1);
                if (visualization) visualized += pointsToString(hull);
            }
            hull.add(sortedPoints2.get(i));
            if (visualization) visualized += pointsToString(hull);
        }
        // Hull is finished now

        // virtualization
        if (visualization) {
            ArrayList<Point> buf = new ArrayList<>(hull);
            buf.add(sortedPoints2.get(0));
            visualized += pointsToString(buf);
            buf = null;
        }

        // Add all remaining points to the hull
        // The hull gets compressed
        while (hull.size() < sortedPoints2.size()) {
            double overallMinCost = Double.MAX_VALUE;
            Point pointToInsert = new Point(), before = new Point(), after = new Point();
            int insertionIndex = -1;
            for (Point p : sortedPoints2) {
                if (hull.contains(p)) continue;
                double minCost = Double.MAX_VALUE;
                // Find segment in hull before -> after that minimizes cost for cost(before -> p) + cost(p -> after) - cost(before -> after)
                for (int i = 0; i < hull.size() - 1; i++) {
                    double currentCost = hull.get(i).getDistanceTo(p) + p.getDistanceTo(hull.get(i + 1)) - hull.get(i).getDistanceTo(hull.get(i + 1));
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        before = hull.get(i);
                        after = hull.get(i + 1);
                    }
                }
                // Find point that minimizes cost for (before -> p -> after) / cost(before -> after)
                double overallCurrentCost = (before.getDistanceTo(p) + p.getDistanceTo(after)) / before.getDistanceTo(after);
                if (overallCurrentCost < overallMinCost) {
                    overallMinCost = overallCurrentCost;
                    pointToInsert = p;
                    insertionIndex = hull.indexOf(after);
                }
            }
            // Add point to hull
            hull.add(insertionIndex, pointToInsert);
            if (visualization) {
                ArrayList<Point> buf = new ArrayList<>(hull);
                buf.add(sortedPoints2.get(0));
                visualized += pointsToString(buf);
                buf = null;
            }
        }
        // Add starting point to the route as well
        hull.add(sortedPoints2.get(0));

        // Calculate the final distance and the Cities array
        this.sortedCities = new Cities();
        double distance = 0.0;

        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < hull.size(); i++) {
            sortedCities.add(new City(hull.get(i).toString(), 0));
        }
        this.sortedCities.setSortedCities(sortedCities);
        for (int i = 0; i < hull.size()-1; i++) {
            System.out.println(hull.get(i));
            System.out.println(hull.get(i+1));
            distance += hull.get(i).getDistanceTo(hull.get(i+1));
            System.out.println(distance);
        }

        this.sortedCities.setDistance(distance);
        // Set time
        Instant ends = Instant.now();
        this.sortedCities.setTime(Duration.between(starts, ends).toMillis());
    }

    /**
     * Calculates the cross product of three vectors, returns false if it is negative, true otherwise
     * @param a
     * @param b
     * @param c
     * @return
     */
    private boolean clock(Point a, Point b, Point c) {
        return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x) >= 0;
    }

    private String pointsToString(ArrayList<Point> l) {
        return l.toString().replaceAll("], ",";").replaceAll(",", "|").replaceAll("[\\[\\]]", "") + "\n";
    }

    /**
     * Returns Cities object
     * @return Cities object with convex hull route
     */
    @Override
    public Cities getResult() {
        return this.sortedCities;
    }

    /**
     * Returns a string with for visualization on the website
     * @return
     */
    @Override
    public String getVisualization() {
        return visualized;
    }
}
