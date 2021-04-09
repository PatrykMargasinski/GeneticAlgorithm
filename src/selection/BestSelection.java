package selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import comparing.Extrema;
import someMethods.SomeMethods;

//wybor iluś najlepszych osobnikow
public class BestSelection implements ISelection {
    int chosenAmount;//ile ma byc wybranych
    public BestSelection(int chosenAmount)
    {
        this.chosenAmount=chosenAmount;
    }

    @Override
    public List<Float[]> select(List<Float[]> population, Extrema extrema){
        List<Float[]> chosenOnes=new ArrayList<>();
        if(extrema==Extrema.Minimum)
            Collections.sort(population, Comparator.comparing(SomeMethods::fun));
        else
            Collections.sort(population, Comparator.comparing(SomeMethods::fun).reversed());
        for(int i=0;i<chosenAmount;i++)
            chosenOnes.add(population.get(i));
        return chosenOnes;
    }
}
