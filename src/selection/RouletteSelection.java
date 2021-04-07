package selection;

import comparing.Extrema;
import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

//wybor losowy. Im lepszy osobnik - tym ma wieksze szanse
public class RouletteSelection implements ISelection {
    int chosenAmount;//ile osobnikow ma byc wybranych

    public RouletteSelection(int chosenAmount) {
        this.chosenAmount = chosenAmount;
    }

    @Override
    public List<Float> select(List<Float> population, Extrema extrema) {
        List<Float> chosenOnes = new ArrayList<>();
        for (int i = 0; i < chosenAmount; i++) {
            chosenOnes.add(getOne(population));
        }
        return chosenOnes;
    }

    public Float getOne(List<Float> population) {
        float sum = 0;
        for (Float f : population) {
            sum += abs(SomeMethods.fun(f));
        }

        Random random = new Random();
        float rand = random.nextInt() % (int) sum;
        int ind = 0;
        for (float i = 0; i < sum; ) {
            if (i >= rand) {
                Float temp = population.get(ind);
                population.remove(temp);
                return temp;
            }
            if (ind < population.size()) {
                i += abs(SomeMethods.fun(population.get(ind)));
                ind++;
            }
        }

        float temp = population.get(ind-1);
        population.remove(temp);
        return temp;
    }
}