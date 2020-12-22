import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        String filename = Console.getFilename();
        System.out.println(CsvReader.readCsvFile(filename));

        Optional<AlgorithmName> algoName = Console.getSelectedAlgorithm();
        if(algoName.isEmpty()) {
            System.err.println("No Algorithm found");
        }else{
            switch(algoName.get()) {
                case NEAREST_NEIGHBOR:
                    NearestNeighbor nn = new NearestNeighbor("test");
                    nn.getResult().toString();
                    break;
                case CONVEX_HULL:
                    ConvexHull ch = new ConvexHull();
                    ch.getResult().toString();
                    break;
                case BRUTE_FORCE:
                    BruteForce bf = new BruteForce();
                    bf.getResult().toString();
                    break;
            }
        }
    }
}
