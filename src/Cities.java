import java.util.ArrayList;

public class Cities {
    private double distance;
    private double time;
    private ArrayList<City> sortedCities;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public ArrayList<City> getSortedCities() {
        return sortedCities;
    }

    public void setSortedCities(ArrayList<City> sortedCities) {
        this.sortedCities = sortedCities;
    }
}
