package selection;

import comparing.Extrema;
import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//wybor losowy. Im lepszy osobnik - tym ma wieksze szanse
public class RouletteSelection implements ISelection {
    int chosenAmount;//ile osobnikow ma byc wybranych
    List<Float> probabilities;

    public RouletteSelection(int chosenAmount) {
        this.chosenAmount = chosenAmount;
    }

    @Override
    public List<Float[]> select(List<Float[]> population, Extrema extrema) {
        List<Float[]> chosenOnes=new ArrayList<>();
        for(int i=0;i<chosenAmount;i++)
        {
            probabilities=new ArrayList<>();
            if(extrema==Extrema.Minimum) calculateProbabilitiesForMinimum(population);
            else calculateProbabilitiesForMaximum(population);
            Random ran=new Random();
            int randomIndex=getRandomValueIndex(ran.nextInt(100));
            chosenOnes.add(population.get(randomIndex));
            population.remove(randomIndex);
        }
        return chosenOnes;
    }

    public void calculateProbabilitiesForMaximum(List<Float[]> population)
    {
        List<Float> functionValues=new ArrayList<>();
        for(int i=0;i<population.size();i++)
            functionValues.add(SomeMethods.fun(population.get(i)));
        float minValue=functionValues.stream().min(Float::compareTo).orElse(0f);
        if(minValue<=0f)
            for(int i=0;i<functionValues.size();i++)
                functionValues.set(i,functionValues.get(i)+minValue);

        float sum=functionValues.stream().reduce(0f,Float::sum);
        for(int i=0;i<functionValues.size();i++)
        {
            float prob=functionValues.get(i)/sum*100;
            probabilities.add(prob);
        }
    }

    public void calculateProbabilitiesForMinimum(List<Float[]> population)
    {
        List<Float> functionValues=new ArrayList<>();
        for(int i=0;i<population.size();i++)
            functionValues.add(SomeMethods.fun(population.get(i)));
        float minValue=functionValues.stream().min(Float::compareTo).orElse(0f);
        if(minValue<0f)
            for(int i=0;i<functionValues.size();i++)
                functionValues.set(i,functionValues.get(i)+minValue);

        float sum=functionValues.stream().reduce(0f,Float::sum);
        for(int i=0;i<functionValues.size();i++)
        {
            float prob=(sum-functionValues.get(i))/sum*100;
            probabilities.add(prob);
        }
    }

    public int getRandomValueIndex(float prob)
    {
        float sum=0;
        int i=0;
        for(Float f : probabilities)
        {
            sum+=f;
            if(prob<sum) return i;
            i++;
        }
        return probabilities.size()-1;
    }
}