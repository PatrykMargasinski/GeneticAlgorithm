package comparing;

import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Minimum implements IExtrema {
    @Override
    public Float[] getExtrema(List<Float[]> population) {
        Float[] min = null;
        for (Float[] f : population) {
            if (min==null||SomeMethods.fun(f) < SomeMethods.fun(min)) {
                min = f;
            }
        }
        return min;
    }

    @Override
    public List<Float[]> getSomeBest(List<Float[]> population, int bestAmount)
    {
        Collections.sort(population, Comparator.comparing(SomeMethods::fun));
        List<Float[]> bestOnes=new ArrayList<>();
        for(int i=0;i<bestAmount;i++)
        {
            bestOnes.add(population.get(i));
        }
        return bestOnes;
    }
}
