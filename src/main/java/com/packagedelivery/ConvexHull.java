package com.packagedelivery;

import java.util.Arrays;

public class ConvexHull implements Algorithm{

    public ConvexHull(String s) {
        String[] points = s.replaceAll("\\[", "").split("], ");
        points[points.length-1] = points[points.length-1].replaceAll("]]", "");
        double[][] coords = new double[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            String[] buf = points[i].split(",");
            coords[i][0] = Double.parseDouble(buf[0]);
            coords[i][1] = Double.parseDouble(buf[1]);
        }

        //for (int i = 0; i < coords.length; i++)
        //    System.out.println(coords[i][0]+ " "+ coords[i][1]);

        double min = Double.MAX_VALUE;
        int position = -1;
        for (int i = 0; i < coords.length; i++) {
            if (min > coords[i][1]) {
                min = coords[i][1];
                position = i;
            }
        }

        System.out.println(coords[position]);
        for (int i = 0; i < coords.length; i++) {
            if (i == position) continue;
            double b = Math.abs(coords[i][0] - coords[position][0]);
            double c = Math.abs(coords[i][1] - coords[position][1]);
            double erg = Math.asin(b/(Math.sqrt(b*b+c*c)));
            System.out.println(b);
            System.out.println(c);
            System.out.println(erg);
        }
    }

    @Override
    public Cities getResult() {
        return null;
    }
}
