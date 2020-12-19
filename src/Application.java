import java.util.Optional;

public class Application {
    public static void main(String[] args) {
        Console.getFilename();

        Optional<AlgorithmName> algo = Console.getSelectedAlgorithm();
        if(algo.isEmpty()) {
            System.err.println("No Algorithm found");
        }else{
            switch(algo.get()) {
                case NEAREST_NEIGHBOR:
                    NearestNeighbor nn = new NearestNeighbor();
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
