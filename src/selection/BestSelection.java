package selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import someMethods.SomeMethods;

//wybor iluś najlepszych osobnikow
public class BestSelection implements ISelection {
    int chosenAmount;//ile ma byc wybranych
    public BestSelection(int chosenOnes)
    {
        this.chosenAmount=chosenAmount;
    }

    @Override
    public List<Float> select(List<Float> population){
        List<Float> chosenOnes=new ArrayList<>();
        Collections.sort(population, Comparator.comparing(SomeMethods::fun));
        for(int i=0;i<chosenAmount;i++)
            chosenOnes.add(population.get(i));
        return chosenOnes;
    }
}
