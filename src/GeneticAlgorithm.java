import comparing.Extrema;
import comparing.IExtrema;
import comparing.Maximum;
import comparing.Minimum;
import crossing.*;
import inversion.IInversion;
import inversion.Inversion;
import mutation.*;
import org.jfree.data.xy.XYSeries;
import selection.*;
import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneticAlgorithm {
    int populationAmount;
    List<Float[]> population;
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
    int bestAmount;
    JTextArea resultTextArea;
    long createdMillis;
    XYSeries firstPlotXYSeries;
    XYSeries secondPlotXYSeries;
    XYSeries thirdPlotXYSeries;


    static Logger logger = Logger.getLogger("GeneticAlgorithm");

    public GeneticAlgorithm(int populationAmount, float epochsNumber,
                            Extrema extrema,
                            Selection selection, int chosenAmount,
                            Crossover crossover, int crossoverProbability,
                            Mutation mutation, int mutationProbability, int inversionProbability,
                            int isEliteStrategyEnabled, int bestAmount) {
        this.populationAmount = populationAmount;
        this.epochsNumber = epochsNumber;
        this.chosenAmount = chosenAmount;
        generatePopulation();
        extremaChoice(extrema);
        selectionChoice(selection);
        mutationChoice(mutation, mutationProbability);
        crossoverChoice(crossover, crossoverProbability);
        inversionChoice(inversionProbability);
        this.isEliteStrategyEnabled = isEliteStrategyEnabled==0;
        this.bestAmount=bestAmount;
    }


    public void run() {
        setupLogs();
        Random random = new Random();
        firstPlotXYSeries = new XYSeries("Wartość funkcji");
        secondPlotXYSeries = new XYSeries("Wartość funkcji");
        thirdPlotXYSeries = new XYSeries("Wartość funkcji");

        for (int i = 0; i < epochsNumber; i++) {
            //save best from population
            Float[] best = extrema.getExtrema(population);

            List<Float[]> bestOnes=extrema.getSomeBest(population,bestAmount);

            try {
                logCurrentIteration(i, best);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            firstPlotXYSeries.add(i, SomeMethods.fun(best));
            secondPlotXYSeries.add(i, SomeMethods.getAverageValue(population));
            thirdPlotXYSeries.add(i,SomeMethods.getStandardDeviation(population));
            //selection
            population = selection.select(population, chosenExtrema);

            //crossing
            List<Float[]> chosenOnesCopy = new ArrayList<>(population);
            while (population.size() < populationAmount - (isEliteStrategyEnabled?bestAmount:0)) {
                List<Integer> range = IntStream.range(0, chosenOnesCopy.size()).boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
                Collections.shuffle(range);
                int removed = range.remove(0);
                String[] parentOne = new String[]{
                        converter.floatToBinary(chosenOnesCopy.get(removed)[0]),
                        converter.floatToBinary(chosenOnesCopy.get(removed)[1])
                };
                removed = range.remove(0);
                String[] parentTwo = new String[]{
                        converter.floatToBinary(chosenOnesCopy.get(removed)[0]),
                        converter.floatToBinary(chosenOnesCopy.get(removed)[1])
                };

                String[] firstHalf = crossover.cross(
                        parentOne[0],
                        parentTwo[0]
                );

                String[] secondHalf = crossover.cross(
                        parentOne[1],
                        parentTwo[1]
                );

                if (random.nextInt(2) == 0) population.add(
                        new Float[]{
                                converter.binaryToFloat(firstHalf[0]),
                                converter.binaryToFloat(secondHalf[0])
                        }
                );
                else population.add(
                        new Float[]{
                                converter.binaryToFloat(firstHalf[1]),
                                converter.binaryToFloat(secondHalf[1])
                        });

            }

            //mutation
            for (int j = 0; j < population.size(); j++) {
                Float[] mutatingNumber = population.get(j);
                String mutatingNumberBinary = converter.floatToBinary(mutatingNumber[0]) + converter.floatToBinary(mutatingNumber[1]);
                String mutated = mutation.mutate(mutatingNumberBinary);
                population.set(j, new Float[]{
                        converter.binaryToFloat(mutated.substring(0, 24)),
                        converter.binaryToFloat(mutated.substring(24, 48))
                });
            }
            //inversion
            for (int j = 0; j < population.size(); j++) {
                Float[] invertingNumber = population.get(j);
                String invertingNumberBinary = converter.floatToBinary(invertingNumber[0]) + converter.floatToBinary(invertingNumber[1]);
                String inverted = inversion.invert(invertingNumberBinary);
                population.set(j, new Float[]{
                        converter.binaryToFloat(inverted.substring(0, 24)),
                        converter.binaryToFloat(inverted.substring(24, 48))
                });
            }
            //elite strategy: add best to population
            if (isEliteStrategyEnabled) {
                for(int j=0;j<bestAmount;j++)
                    population.add(bestOnes.get(j));
            }
        }
    }

    public XYSeries getFirstPlotXYSeries() {
        return firstPlotXYSeries;
    }

    public XYSeries getSecondPlotXYSeries() {
        return secondPlotXYSeries;
    }

    public XYSeries getThirdPlotXYSeries() {
        return thirdPlotXYSeries;
    }

    private void setupLogs() {
        FileHandler fileHandler;
        createdMillis = System.currentTimeMillis();
        ConsoleHandler consoleHandler;
        try {
            // This block configure the logger with handler and formatter
            fileHandler = new FileHandler("logs/geneticAlgorithm.log");
            consoleHandler = new ConsoleHandler();
            logger.setUseParentHandlers(false);
            CustomRecordFormatter formatter = new CustomRecordFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);

            consoleHandler.setFormatter(formatter);
            logger.addHandler(consoleHandler);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    private void logCurrentIteration(int i, Float[] best) throws FileNotFoundException {
        logger.info("Epoka: " + i);
        logger.info("Populacja: " + SomeMethods.get(population));
        logger.info("Wartości: " + SomeMethods.getValues(population));
        logger.info("Najlepszy osobnik: " + SomeMethods.getOne(best));
        logger.info("Wartość najlepszego: " + SomeMethods.fun(best));
        logger.info("\n\n");

        if (i + 1 == epochsNumber) {
            logger.info("Czas trwania programu: " + getAgeInMillis() + " ms");
            resultTextArea.setText("Najlepszy osobnik: " + SomeMethods.getOne(best) + "\n" +
                    "Wartość najlepszego: " + SomeMethods.fun(best) + "\n" +
                    "Czas trwania programu: " + getAgeInMillis() + " ms");
            Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    logger.removeHandler(handler);
                }
            }
        }
    }

    public long getAgeInMillis() {
        return System.currentTimeMillis() - createdMillis;
    }

    void generatePopulation() {
        population = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < populationAmount; i++) {
            population.add(new Float[]{random.nextFloat() * 5 - 5, random.nextFloat() * 5 - 5});
            logger.info("Added: " + SomeMethods.getOne(population.get(population.size() - 1)));
        }
        SomeMethods.print(population);
    }

    void extremaChoice(Extrema extrema) {
        chosenExtrema = extrema;
        switch (extrema) {
            case Maximum:
                this.extrema = new Maximum();
                break;
            case Minimum:
                this.extrema = new Minimum();
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
            case ThreePoint:
                this.crossover = new ThreePointCrossing(5,10,15, probability,true);
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

    public void setResultTextArea(JTextArea resultTextArea) {
        this.resultTextArea = resultTextArea;
    }
}
