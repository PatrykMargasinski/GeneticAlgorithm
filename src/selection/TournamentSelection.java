package selection;
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
    public List<Float> select(List<Float> population){
        float selectionBorder=1.0f*population.size()/numberOfGroups;
        List<Float> chosenOnes=new ArrayList<>();
        for(float i=0;i<population.size();i+=selectionBorder)
        {
            chosenOnes.add(findMin((int)i, (int)(i+selectionBorder), population));
        }
        return chosenOnes;
    }

    //znajdowanie najlepszego osobnika w grupie. Grupa to osobniki od indeksu 'a' do 'b'
    float findMin(int a, int b, List<Float> population)
    {
        float min=Float.MAX_VALUE;
        float minValue=Float.MAX_VALUE;
        for(int i=a;i<b;i++)
        {
            if(SomeMethods.fun(population.get(i))<minValue)
            {
                min=population.get(i);
                minValue= SomeMethods.fun(population.get(i));
            }
        }
        return min;
    }
}