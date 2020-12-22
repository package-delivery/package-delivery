import java.util.ArrayList;

public class NearestNeighbor implements Algorithm{
    private double distance;
    private ArrayList<Integer> path;
    private Cities sortedCities = new Cities();

    public NearestNeighbor(String start) {
        // get City array and adjazenzmatrix
        double[][] matrix = CsvReader.getDistanceMatrix();
        City[] cities = CsvReader.getCityMatrix();

        // Check if start city is a city in the csv file
        boolean contains = false;
        for (int i = 0; i < cities.length; i++) {
            if (start.equals(cities[i].getCityName())) {
                contains = true;
                break;
            }
        }
        if (!contains) {
            System.out.println("Es ist zu einem Fehler gekommen, die eingegebene Startposition befindet sich nicht in der Adjazenzmatrix!");
            return;
        }

        // Now we have to convert the start string to the id
        int current = 0;
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getCityName().equals(start)) {
                current = cities[i].getId();
                break;
            }
        }
        distance = 0;
        int idStart = current;
        path = new ArrayList<Integer>();
        path.add(current);
        while (path.size() < matrix.length-1) {
            path.add(getNearest(current, matrix));
            current = path.get(path.size()-1);
        }
        path.add(idStart);

        ArrayList<City> sortedCities = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            sortedCities.add(cities[path.get(i)]);
        }

        this.sortedCities.setSortedCities(sortedCities);
        this.sortedCities.setDistance(distance);
    }
    private int getNearest(int point, double[][] matrix) {
        double diff = Double.MAX_VALUE;
        int position = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (path.contains(i)) continue;
            if (matrix[point][i] < diff)  {
                diff = matrix[point][i];
                position = i;
            }
        }
        distance += diff;
        return position;
    }

    @Override
    public Cities getResult() {
        return sortedCities;
    }

}
