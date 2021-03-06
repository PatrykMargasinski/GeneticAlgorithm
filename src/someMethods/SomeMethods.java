package someMethods;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SomeMethods {

    public static Float fun(Float[] x) {
        double sum1 = 0.0;
        double sum2 = 0.0;
        sum1 += Math.pow(x[0], 2);
        sum2 += (Math.cos(2*Math.PI*x[0]));
        sum1 += Math.pow(x[1], 2);
        sum2 += (Math.cos(2*Math.PI*x[1]));

        return (float)(-20.0f * Math.exp(-0.2f * Math.sqrt(sum1 / (2f))) -
                Math.exp(sum2 / (2f)) + 20.0f + Math.exp(1));
    }

    public static Float testFun(Float[] x)
    {
        return -(x[0]*x[0]+x[1]*x[1] + 2);
    }

    //jest okreslona szansa na mutacje lub krzyżowanie. Tutaj jest metoda sprawdzająca, czy strzął o prawdopodobieństwie
    //"probability" (0-100) jest udany
    public static boolean checkProbability(int probability)
    {
        Random ran=new Random();
        int drawn=ran.nextInt(100);
        return drawn+1 <= probability;
    }

    //nadpisywanie pliku, może na jakieś logi się przyda
    public static void writeToFile(String message){
        try {
            FileWriter zapis = new FileWriter("log.txt",true);
            zapis.write(message);
            zapis.close();
        }
        catch (Exception ex)
        {
            System.out.print("Nie dziala");
        }
    }


    //do wypisywania listy
    public static String get(List<Float[]> list)
    {
        StringBuilder str=new StringBuilder("");
        for(Float[] f : list)
        {
            str.append(getOne(f)+" ");
        }
        return str.toString();
    }

    public static String getValues(List<Float[]> list)
    {
        StringBuilder str=new StringBuilder("");
        for(Float[] f : list)
        {
            str.append(getValue(f)+" ");
        }
        return str.toString();
    }

    public static void print(List<Float[]> list)
    {
        System.out.println(get(list));
    }

    public static void printValues(List<Float[]> list)
    {
        System.out.println(getValues(list));
    }

    public static String getOne(Float[] f)
    {
        return "["+f[0]+","+f[1]+"]";
    }

    public static String getValue(Float[] f)
    {
        return fun(f)+"";
    }
    public static float getAverageValue(List<Float[]> list){
        float sum=0f;
        for(Float[] value : list){
            sum=sum+fun(value);
        }
        return sum/ list.size();
    }

    public static float getStandardDeviation(List<Float[]> list)
    {
        float average=getAverageValue(list);
        float sum=0;
        for(Float[] x : list)
        {
            sum+=Math.pow(fun(x)-average,2);
        }
        return (float) Math.sqrt(sum/list.size());
    }

    //generowanie randomowego inta z przedziału 0,length
    public static int generateRandomPoint(int length){
        Random random = new Random();
        return random.nextInt(length);
    }

    public static List<Integer> getSomeVariousNumbers(int amount, int limit)
    {
        List<Integer> range = IntStream.range(0, limit).boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(range);
        List<Integer> chosenOnes=new ArrayList<>();
        for(int i=0;i<amount;i++)
            chosenOnes.add(range.remove(0));

        Collections.sort(chosenOnes);

        return chosenOnes;
    }
}
