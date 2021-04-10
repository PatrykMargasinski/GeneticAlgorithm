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
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(30, 500,
                Extrema.Minimum,
                Selection.Best, 5,
                Crossover.Homogeneous, 90,
                Mutation.Border, 10, 10, true);
        geneticAlgorithm.run();
    }

}
