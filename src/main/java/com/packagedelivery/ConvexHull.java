package com.packagedelivery;

import java.util.ArrayList;
import java.util.TreeMap;

public class ConvexHull implements Algorithm{

    public ConvexHull(String s) {
        String[] points = s.replaceAll("\\[", "").split("], ");
        points[points.length-1] = points[points.length-1].replaceAll("]]", "");
        double[][] coords = new double[points.length][2];
        for (int i = 0; i < points.length; i++) {
            String[] buf = points[i].split(",");
            coords[i][0] = Double.parseDouble(buf[0]);
            coords[i][1] = Double.parseDouble(buf[1]);
        }
        double min = Double.MAX_VALUE;
        int position = -1;
        for (int i = 0; i < coords.length; i++) {
            if (min > coords[i][1]) {
                min = coords[i][1];
                position = i;
            }
        }
        TreeMap<Double, double[]> sortedPoints = new TreeMap<>();
        sortedPoints.put(0.0, coords[position]);
        for (int i = 0; i < coords.length; i++) {
            if (i == position) continue;
            double b = coords[i][0] - coords[position][0];
            double c = coords[i][1] - coords[position][1];
            double erg = Math.atan(c/b);
            if (erg < 0) erg += Math.PI;
            sortedPoints.put(erg, coords[i]);
        }
        ArrayList<double[]> hull = new ArrayList<>();
        ArrayList<double[]> sortedPoints2 = new ArrayList<>();
        for (double d : sortedPoints.keySet())
            sortedPoints2.add(sortedPoints.get(d));
        hull.add(sortedPoints2.get(0));
        hull.add(sortedPoints2.get(1));
        for (int i = 2; i < sortedPoints2.size(); i++) {
            while (!clock(hull.get(hull.size()-2), hull.get(hull.size()-1), sortedPoints2.get(i))) {
                hull.remove(hull.size()-1);
            }
            hull.add(sortedPoints2.get(i));
        }

        for (int i = 0; i < hull.size(); i++) {
            System.out.println(hull.get(i)[0]+" "+hull.get(i)[1]);
        }
    }

    private boolean clock(double[] a, double[] b, double[] c) {
        return (b[0]-a[0])*(c[1]-a[1]) - (b[1]-a[1])*(c[0]-a[0]) >= 0;
    }

    @Override
    public Cities getResult() {
        return null;
    }

    @Override
    public String getVisualization() {
        return null;
    }
}
