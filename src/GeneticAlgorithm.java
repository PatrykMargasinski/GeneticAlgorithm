import comparing.Extrema;
import comparing.IExtrema;
import comparing.Maximum;
import comparing.Minimum;
import crossing.*;
import inversion.IInversion;
import inversion.Inversion;
import mutation.*;
import selection.*;
import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm {
    int populationAmount;
    List<Float> population;
    float epochsNumber;
    int chosenAmount;
    IExtrema extrema;
    Extrema chosenExtrema;
    ISelection selection;
    ICrossover crossover;
    IMutation mutation;
    IInversion inversion;
    FloatToBytes converter = new FloatToBytes(-10, 10);
    boolean isEliteStrategyEnabled;

    public GeneticAlgorithm(int populationAmount, float epochsNumber,
                            Extrema extrema,
                            Selection selection, int chosenAmount,
                            Crossover crossover, int crossoverProbability,
                            Mutation mutation, int mutationProbability, int inversionProbability, boolean isEliteStrategyEnabled) {
        this.populationAmount = populationAmount;
        this.epochsNumber = epochsNumber;
        this.chosenAmount=chosenAmount;
        generatePopulation();
        extremaChoice(extrema);
        selectionChoice(selection);
        mutationChoice(mutation, mutationProbability);
        crossoverChoice(crossover, crossoverProbability);
        inversionChoice(inversionProbability);
        this.isEliteStrategyEnabled = isEliteStrategyEnabled;
    }


    public void run() {

        Random random = new Random();
        for (int i = 0; i < epochsNumber; i++) {
            //save best from population
            Float best = extrema.getExtrema(population);

            System.out.println("Epoka: " + i);
            System.out.println("Populacja: " + SomeMethods.get(population));
            System.out.println("Najlepszy: " + best);
            System.out.println("\n\n");
            //selection
            population = selection.select(population,chosenExtrema);

            //crossing
            List<Float> chosenOnesCopy = new ArrayList<>(population);
            while (population.size() < populationAmount - 1) {
                List<Integer> range = IntStream.range(0, chosenOnesCopy.size()).boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
                Collections.shuffle(range);
                String parentOne =converter.floatToBinary(chosenOnesCopy.get(range.remove(0)));
                String parentTwo = converter.floatToBinary(chosenOnesCopy.get(range.remove(0)));
                String[] childs=crossover.cross(
                        parentOne,
                        parentTwo
                );
                if (random.nextInt(2) == 0) population.add(converter.binaryToFloat(childs[0]));
                else population.add(converter.binaryToFloat(childs[1]));
            }

            //mutation
            for (int j = 0; j < population.size(); j++) {
                float mutatingNumber = population.get(j);
                String mutatingNumberBinary = converter.floatToBinary(mutatingNumber);
                String mutated=mutation.mutate(mutatingNumberBinary);
                population.set(j, converter.binaryToFloat(mutated));
            }
            //inversion
            for (int j = 0; j < population.size(); j++) {
                float invertingNumber = population.get(j);
                String invertingNumberBinary = converter.floatToBinary(invertingNumber);
                String inverted=inversion.invert(invertingNumberBinary);
                population.set(j, converter.binaryToFloat(inverted));
            }
            //elite strategy: add best to population
            if (isEliteStrategyEnabled) {
                population.add(best);
            }
        }
    }

    void generatePopulation() {
        population = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < populationAmount; i++) {
            population.add(random.nextFloat() * 5 + 5);
            System.out.println("Added: " + population.get(population.size() - 1));
        }
        SomeMethods.print(population);
    }

    void extremaChoice(Extrema extrema)
    {
        chosenExtrema=extrema;
        switch (extrema)
        {
            case Maximum:
                this.extrema= new Maximum();
                break;
            case Minimum:
                this.extrema= new Minimum();
                break;
        }
    }

    void selectionChoice(Selection selection) {
        switch (selection) {
            case Best:
                this.selection = new BestSelection(chosenAmount);
                break;
            case Roulette:
                this.selection = new RouletteSelection(chosenAmount);
                break;
            case Tournament:
                this.selection = new TournamentSelection(chosenAmount);
        }
    }

    void crossoverChoice(Crossover crossover, int probability) {
        switch (crossover) {
            case OnePoint:
                this.crossover = new OnePointCrossing(5, probability, true);
                break;
            case TwoPoint:
                this.crossover = new TwoPointCrossing(5, 10, probability, true);
                break;
            case Homogeneous:
                this.crossover = new HomogeneousCrossing(true, probability);
                break;
        }
    }


    void mutationChoice(Mutation mutation, int probability) {
        switch (mutation) {
            case OnePoint:
                this.mutation = new OnePointMutation(probability);
                break;
            case TwoPoint:
                this.mutation = new TwoPointMutation(probability);
                break;
            case Border:
                this.mutation = new BorderMutation(probability);
                break;
        }
    }

    void inversionChoice(int probability) {
        this.inversion = new Inversion(probability);
    }

}
