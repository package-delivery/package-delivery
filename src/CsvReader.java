import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CsvReader {

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> arList = new ArrayList<ArrayList<String>>();
        try {
            String[] lines = Files.readString(Paths.get("TSP.csv")).split("\n");
            String[] cities = lines[0].split(",");

            ArrayList<ArrayList<Double>> matrix = new ArrayList<ArrayList<Double>>();
            ArrayList<Double> buffer = new ArrayList<>();
            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split(",");
                for (int x = 1; x < line.length; x++) buffer.add(Double.parseDouble(line[x]));
                matrix.add(new ArrayList<Double>(buffer));
                buffer.clear();
            }
            for (int i = 0; i < matrix.size(); i++) {
                for (int x = 0; x < matrix.size(); x++) {
                    System.out.print(matrix.get(x).get(i)+" ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
