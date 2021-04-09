import comparing.Extrema;
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
import selection.RouletteSelection;
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
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(30, 50,
                Extrema.Maximum,
                Selection.Roulette, 5,
                Crossover.Homogeneous, 90,
                Mutation.Border, 10, 10, true);
        geneticAlgorithm.run();
    }

    public static void testInversion() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        float f1 = 3f, f2 = -6f;
        String s1 = ftp.floatToBinary(f1);
        System.out.println("Value: " + f1 + "\tString: " + s1);
        IInversion inversion = new Inversion(50);
        f1 = ftp.binaryToFloat(inversion.invert(s1));
        System.out.println("\nAfter:");
        System.out.println("Value: " + f1 + "\tString: " + s1);
    }

    public static void testCrossovers() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        float f1 = 3f, f2 = -6f;
        String s1 = ftp.floatToBinary(f1);
        String s2 = ftp.floatToBinary(f2);
        System.out.println("Value: " + f1 + "\tString: " + s1);
        System.out.println("Value: " + f2 + "\tString: " + s2);
        ICrossover crossover = new OnePointCrossing(24, 50, true);
        String[] childs=crossover.cross(s1, s2);
        System.out.println("\nAfter:");
        f1 = ftp.binaryToFloat(childs[0]);
        f2 = ftp.binaryToFloat(childs[1]);
        System.out.println("Value: " + f1 + "\tString: " + s1);
        System.out.println("Value: " + f2 + "\tString: " + s2);
    }

    public static void testSelection() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        ISelection sel = new RouletteSelection(5);
        List<Float> population = new ArrayList<>();
        population.addAll(Arrays.asList(1f,2f,3f,4f,5f,6f,7f,8f,9f,10f));
        System.out.println(SomeMethods.get(sel.select(population,Extrema.Maximum)));
    }

    public static void testMutation() {
        FloatToBytes ftp = new FloatToBytes(-10, 10);
        float f1 = 3f, f2 = -6f;
        String s1 = ftp.floatToBinary(f1);
        System.out.println("Value: " + f1 + "\tString: " + s1);
        IMutation mutation = new OnePointMutation(50);
        System.out.println("\nAfter:");
        f1 = ftp.binaryToFloat(mutation.mutate(s1));
        System.out.println("Value: " + f1 + "\tString: " + s1);
    }
}
