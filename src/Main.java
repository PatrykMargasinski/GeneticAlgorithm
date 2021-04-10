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

    public static void testSelection() {
        ISelection sel=new RouletteSelection(2);
        List<Float[]>pop=new ArrayList<>();
        pop.add(new Float[]{1f,1f});pop.add(new Float[]{2f,2f});pop.add(new Float[]{3f,3f});pop.add(new Float[]{4f,4f});
        pop.add(new Float[]{5f,5f});pop.add(new Float[]{6f,6f});pop.add(new Float[]{7f,7f});pop.add(new Float[]{8f,8f});
        SomeMethods.print(pop);
        SomeMethods.printValues(pop);
        List<Float[]> wynik=sel.select(pop,Extrema.Maximum);
        System.out.println("\n\n\n\n");
        SomeMethods.print(wynik);
        SomeMethods.printValues(wynik);
    }

}
