import mutation.*;
import someMethods.FloatToBytes;
import someMethods.SomeMethods;
import crossing.*;
import selection.*;
import java.util.*;

public class GeneticAlgorithm
{
    int populationAmount;
    List<Float> population;
    float epochsNumber;
    ISelection selection;
    ICrossover crossover;
    IMutation mutation;
    FloatToBytes converter=new FloatToBytes(-10,10);

    public GeneticAlgorithm(int populationAmount, float epochsNumber, Selection selection, Crossover crossover, Mutation mutation)
    {
        this.populationAmount=populationAmount;
        this.epochsNumber=epochsNumber;
        generatePopulation();
        mutationChoice(mutation);
        selectionChoice(selection);
        crossoverChoice(crossover);
    }


    public void run()
    {

        Random random=new Random();
        for(int i=0;i<epochsNumber;i++)
        {
            System.out.println("Epoka: "+i);
            System.out.println("Populacja: "+SomeMethods.get(population));
            System.out.println("Najlepszy: "+getBest());
            System.out.println("\n\n");
            //selection
            List<Float> chosenOnes=selection.select(population);

            //crossing
            List<Float> chosenOnesCopy=new ArrayList<>(chosenOnes);
            while(population.size()<populationAmount)
            {
                List<StringBuilder> couple=new ArrayList<>();
                for(int j=0;j<2;j++) {
                    Float child=chosenOnesCopy.remove(random.nextInt(chosenOnesCopy.size()));
                    couple.add(new StringBuilder(converter.floatToBinary(child)));
                }
                int index=random.nextInt(2);
                population.add(converter.binaryToFloat(couple.get(index).toString()));
            }

            //mutation
            for(int j=0;j<population.size();j++)
            {
                float mutatingNumber=population.get(j);
                StringBuilder mutatingNumberBinary=new StringBuilder(converter.floatToBinary(mutatingNumber));
                mutation.mutate(mutatingNumberBinary);
                population.set(j,converter.binaryToFloat(mutatingNumberBinary.toString()));
            }
        }
    }

    void generatePopulation()
    {
        population=new ArrayList<>();
        Random random=new Random();
        for(int i=0;i<populationAmount;i++)
        {
            population.add(random.nextFloat()*10-5);
            System.out.println("Added: "+population.get(population.size()-1));
        }
        SomeMethods.print(population);
    }

    void selectionChoice(Selection selection)
    {
        switch (selection)
        {
            case Best:
                this.selection=new BestSelection(5);
                break;
            case Roulette:
                this.selection=new RouletteSelection(5);
                break;
            case Tournament:
                this.selection=new TournamentSelection(5);
        }
    }

    void crossoverChoice(Crossover crossover)
    {
        switch (crossover)
        {
            case OnePoint:
                this.crossover=new OnePointCrossing(5,40,true);
                break;
            case TwoPoint:
                this.crossover=new TwoPointCrossing(5,10,40,true);
                break;
            case Homogeneours:
                this.crossover=new HomogeneousCrossing(true, 40);
                break;
        }
    }


    void mutationChoice(Mutation mutation)
    {
        switch (mutation)
        {
            case OnePoint:
                this.mutation=new OnePointMutation(5,40);
                break;
            case TwoPoint:
                this.mutation=new TwoPointMutation(5,20,40);
                break;
            case Border:
                this.mutation=new BorderMutation(true,40);
                break;
        }
    }

    Float getBest()
    {
        Float min=Float.MAX_VALUE;
        for(Float f : population)
        {
            if(SomeMethods.fun(f)<min)
            {
                min=f;
            }
        }
        return min;
    }

}
