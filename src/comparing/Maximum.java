package comparing;

import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Maximum implements IExtrema {
    @Override
    public Float[] getExtrema(List<Float[]> population) {
        Float[] max = null;
        for (Float[] f : population) {
            if (max==null||SomeMethods.fun(f) > SomeMethods.fun(max)) {
                max = f;
            }
        }
        return max;
    }

    @Override
    public List<Float[]> getSomeBest(List<Float[]> population, int bestAmount)
    {
        Collections.sort(population, Comparator.comparing(SomeMethods::fun).reversed());
        List<Float[]> bestOnes=new ArrayList<>();
        for(int i=0;i<bestAmount;i++)
        {
            bestOnes.add(population.get(i));
        }
        return bestOnes;
    }
}
