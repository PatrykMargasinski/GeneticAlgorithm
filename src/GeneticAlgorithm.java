import mutation.*;
import someMethods.FloatToBytes;
import someMethods.SomeMethods;
import crossing.*;
import selection.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm
{
    int populationAmount;
    List<Float> population;
    float epochsNumber;
    ISelection selection;
    ICrossover crossover;
    IMutation mutation;
    FloatToBytes converter=new FloatToBytes(-10,10);

    public GeneticAlgorithm(int populationAmount, float epochsNumber, Selection selection,
                            Crossover crossover, int crossoverProbability,
                            Mutation mutation, int mutationProbability)
    {
        this.populationAmount=populationAmount;
        this.epochsNumber=epochsNumber;
        generatePopulation();
        selectionChoice(selection);
        mutationChoice(mutation, mutationProbability);
        crossoverChoice(crossover, crossoverProbability);
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
            population=selection.select(population);

            //crossing
            List<Float> chosenOnesCopy=new ArrayList<>(population);
            while(population.size()<populationAmount)
            {
                List<Integer> range = IntStream.range(0, chosenOnesCopy.size()).boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
                Collections.shuffle(range);
                StringBuilder childOne= new StringBuilder(converter.floatToBinary(chosenOnesCopy.get(range.remove(0))));
                StringBuilder childTwo= new StringBuilder(converter.floatToBinary(chosenOnesCopy.get(range.remove(0))));
                crossover.cross(
                        childOne,
                        childTwo
                );
                if(random.nextInt(2)==0) population.add(converter.binaryToFloat(childOne.toString()));
                else population.add(converter.binaryToFloat(childTwo.toString()));
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
            population.add(random.nextFloat()*5+5);
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

    void crossoverChoice(Crossover crossover, int probability)
    {
        switch (crossover)
        {
            case OnePoint:
                this.crossover=new OnePointCrossing(5,probability,true);
                break;
            case TwoPoint:
                this.crossover=new TwoPointCrossing(5,10,probability,true);
                break;
            case Homogeneours:
                this.crossover=new HomogeneousCrossing(true, probability);
                break;
        }
    }


    void mutationChoice(Mutation mutation, int probability)
    {
        switch (mutation)
        {
            case OnePoint:
                this.mutation=new OnePointMutation(5,probability);
                break;
            case TwoPoint:
                this.mutation=new TwoPointMutation(5,20,probability);
                break;
            case Border:
                this.mutation=new BorderMutation(true,probability);
                break;
        }
    }

    Float getBest()
    {
        Float min=Float.MAX_VALUE;
        for(Float f : population)
        {
            if(SomeMethods.fun(f)<SomeMethods.fun(min))
            {
                min=f;
            }
        }
        return min;
    }

}
