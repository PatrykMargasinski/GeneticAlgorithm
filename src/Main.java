import crossing.Crossover;
import crossing.ICrossover;
import crossing.OnePointCrossing;
import inversion.IInversion;
import inversion.Inversion;
import mutation.IMutation;
import mutation.Mutation;
import mutation.OnePointMutation;
import selection.BestSelection;
import selection.ISelection;
import selection.Selection;
import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        testGenetic();
    }

    public static void testGenetic() {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(10, 50,
                Selection.Best,
                Crossover.Homogeneours, 90,
                Mutation.Border, 10);
        geneticAlgorithm.run();
    }

    public static void testInversion() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        float f1 = 3f, f2 = -6f;
        StringBuilder s1 = new StringBuilder(ftp.floatToBinary(f1));
        System.out.println("Value: " + f1 + "\tString: " + s1);
        IInversion inversion = new Inversion(50);
        inversion.invert(s1);
        System.out.println("\nAfter:");
        f1 = ftp.binaryToFloat(s1.toString());
        System.out.println("Value: " + f1 + "\tString: " + s1);
    }

    public static void testCrossovers() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        float f1 = 3f, f2 = -6f;
        StringBuilder s1 = new StringBuilder(ftp.floatToBinary(f1));
        StringBuilder s2 = new StringBuilder(ftp.floatToBinary(f2));
        System.out.println("Value: " + f1 + "\tString: " + s1);
        System.out.println("Value: " + f2 + "\tString: " + s2);
        ICrossover crossover = new OnePointCrossing(24, 50, true);
        crossover.cross(s1, s2);
        System.out.println("\nAfter:");
        f1 = ftp.binaryToFloat(s1.toString());
        f2 = ftp.binaryToFloat(s2.toString());
        System.out.println("Value: " + f1 + "\tString: " + s1);
        System.out.println("Value: " + f2 + "\tString: " + s2);
    }

    public static void testSelection() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        ISelection sel = new BestSelection(4);
        List<Float> population = new ArrayList<>();
        population.addAll(Arrays.asList(-4.5f, -10f, 4.3f, 1f, -5f, 6f, -3.2f, 5.0f, -4f, 5.4f));
        System.out.println(SomeMethods.get(sel.select(population)));
    }

    public static void testMutation() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        float f1 = 3f, f2 = -6f;
        StringBuilder s1 = new StringBuilder(ftp.floatToBinary(f1));
        System.out.println("Value: " + f1 + "\tString: " + s1);
        IMutation mutation = new OnePointMutation(5, 50);
        mutation.mutate(s1);
        System.out.println("\nAfter:");
        f1 = ftp.binaryToFloat(s1.toString());
        System.out.println("Value: " + f1 + "\tString: " + s1);
    }
}
