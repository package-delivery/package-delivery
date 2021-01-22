package com.packagedelivery;

import java.util.Arrays;

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

        System.out.println(Arrays.toString(coords[position]));
        double[] point = new double[2];
        System.arraycopy(coords[position],0,point,0,2);
        do {
            double minAngle = Double.MAX_VALUE;
            int posAngle = -1;
            for (int i = 0; i < coords.length; i++) {
                if (coords[i][0]==point[0]&&coords[i][1]==point[1]) continue;
                double b = Math.abs(coords[i][0] - point[0]);
                double c = Math.abs(coords[i][1] - point[1]);
                double erg = Math.asin(b / (Math.sqrt(b * b + c * c)));
                if (minAngle > erg) {
                    minAngle = erg;
                    posAngle = i;
                }
                //System.out.println(b);
                //System.out.println(c);
                //System.out.println(erg);
            }
            System.arraycopy(coords[posAngle],0,point,0,2);
            System.out.println(point[0]+" "+point[1]);

        } while(coords[position][0]!=point[0]&&coords[position][1]!=point[1]);
    }

    @Override
    public Cities getResult() {
        return null;
    }
}
