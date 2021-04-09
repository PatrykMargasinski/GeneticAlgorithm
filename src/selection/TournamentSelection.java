package selection;
import comparing.Extrema;
import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.List;

//podzial na okresloną ilość grup o takiej samej liczbie osobników. Wybór jednego w każdej grupie
public class TournamentSelection implements ISelection {
    int numberOfGroups;//ile grup, jednocześnie ile ma byc wybranych
    public TournamentSelection(int numberOfGroups)
    {
        this.numberOfGroups=numberOfGroups;
    }

    @Override
    public List<Float[]> select(List<Float[]> population, Extrema extrema){
        float selectionBorder=1.0f*population.size()/numberOfGroups;
        List<Float[]> chosenOnes=new ArrayList<>();
        for(float i=0;i<population.size();i+=selectionBorder)
        {
            if(extrema==Extrema.Minimum)
                chosenOnes.add(findMin((int)i, (int)(i+selectionBorder), population));
            else
                chosenOnes.add(findMax((int)i, (int)(i+selectionBorder), population));
        }
        return chosenOnes;
    }

    //znajdowanie najlepszego osobnika w grupie. Grupa to osobniki od indeksu 'a' do 'b'
    Float[] findMin(int a, int b, List<Float[]> population)
    {
        Float[] min=null;
        float minValue=Float.MAX_VALUE;
        for(int i=a;i<b;i++)
        {
            if(min== null||SomeMethods.fun(population.get(i))<minValue)
            {
                min=population.get(i);
                minValue= SomeMethods.fun(population.get(i));
            }
        }
        return min;
    }

    Float[] findMax(int a, int b, List<Float[]> population)
    {
        Float[] max=null;
        float maxValue=Float.MIN_VALUE;
        for(int i=a;i<b;i++)
        {
            if(max== null||SomeMethods.fun(population.get(i))>maxValue)
            {
                max=population.get(i);
                maxValue= SomeMethods.fun(population.get(i));
            }
        }
        return max;
    }
}