package com.packagedelivery;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class ConvexHull implements Algorithm, Displayable{

    class Point {
        protected double x, y;

        public Point() {}

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getDistanceTo(Point p) {
            return Math.sqrt(Math.pow(Math.abs(this.x-p.x), 2) + Math.pow(Math.abs(this.y-p.y), 2));
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

    public ConvexHull(String s, boolean visualization) {

        //start stopwatch
        Instant starts = Instant.now();

        if (visualization) visualized = "";

        String[] points = s.replaceAll("\\[", "").split("],");
        points[points.length-1] = points[points.length-1].replaceAll("]]", "");

        ArrayList<Point> coords = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            String[] buf = points[i].split(",");
            coords.add(new Point(Double.parseDouble(buf[0]), Double.parseDouble(buf[1])));
        }

        Point min = new Point(0,Double.MAX_VALUE);
        for (Point p : coords)
            if (p.y < min.y) min = new Point(p.x, p.y);

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

        ArrayList<Point> hull = new ArrayList<>();
        ArrayList<Point> sortedPoints2 = new ArrayList<>();
        for (double angle : sortedPoints.keySet())
            sortedPoints2.add(sortedPoints.get(angle));
        hull.add(sortedPoints2.get(0));
        hull.add(sortedPoints2.get(1));
        if (visualization) visualized += hull.toString() + "\n";

        for (int i = 2; i < sortedPoints2.size(); i++) {
            while (!clock(hull.get(hull.size()-2), hull.get(hull.size()-1), sortedPoints2.get(i))) {
                hull.remove(hull.size()-1);
                if (visualization) visualized += hull.toString() + "\n";
            }
            hull.add(sortedPoints2.get(i));
            if (visualization) visualized += hull.toString() + "\n";
        }

        if (visualization) {
            ArrayList<Point> buf = new ArrayList<>(hull);
            buf.add(sortedPoints2.get(0));
            visualized += buf.toString() + "\n";
            buf = null;
        }

        while (hull.size() < sortedPoints2.size()) {
            double overallMinCost = Double.MAX_VALUE;
            Point pointToInsert = new Point(), before = new Point(), after = new Point();
            int insertionIndex = -1;
            for (Point p : sortedPoints2) {
                if (hull.contains(p)) continue;
                double minCost = Double.MAX_VALUE;
                for (int i = 0; i < hull.size() - 1; i++) {
                    double currentCost = hull.get(i).getDistanceTo(p) + p.getDistanceTo(hull.get(i + 1)) - hull.get(i).getDistanceTo(hull.get(i + 1));
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        before = hull.get(i);
                        after = hull.get(i + 1);
                    }
                }
                double overallCurrentCost = (before.getDistanceTo(p) + p.getDistanceTo(after)) / before.getDistanceTo(after);
                if (overallCurrentCost < overallMinCost) {
                    overallMinCost = overallCurrentCost;
                    pointToInsert = p;
                    insertionIndex = hull.indexOf(after);
                }
            }
            hull.add(insertionIndex, pointToInsert);
            if (visualization) {
                ArrayList<Point> buf = new ArrayList<>(hull);
                buf.add(sortedPoints2.get(0));
                visualized += buf.toString() + "\n";
                buf = null;
            }
        }

        hull.add(sortedPoints2.get(0));

        // Calculate the final distance and the Cities array
        this.sortedCities = new Cities();
        this.sortedCities.setDistance(0.0);
        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < hull.size()-1; i++) {
            sortedCities.add(new City(hull.get(i).toString(), 0));
            this.sortedCities.setDistance(this.sortedCities.getDistance()+hull.get(i).getDistanceTo(hull.get(i+1)));
        }
        sortedCities.add(new City(hull.get(hull.size()-1).toString(), 0));
        this.sortedCities.setSortedCities(sortedCities);

        // Set time
        Instant ends = Instant.now();
        this.sortedCities.setTime(Duration.between(starts, ends).toMillis());
    }

    private boolean clock(Point a, Point b, Point c) {
        return (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x) >= 0;
    }


    @Override
    public Cities getResult() {
        return this.sortedCities;
    }

    @Override
    public String getVisualization() {
        return visualized;
    }
}
