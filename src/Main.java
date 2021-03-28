import crossing.*;
import mutation.*;
import selection.*;
import someMethods.FloatToBytes;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        testSelection();
    }

    public static void testCrossovers()
    {
        FloatToBytes ftp=new FloatToBytes(-10,10);
        float f1=3f,f2=-6f;
        StringBuilder s1=new StringBuilder(ftp.floatToBinary(f1));
        StringBuilder s2=new StringBuilder(ftp.floatToBinary(f2));
        System.out.println("Value: "+f1+"\tString: "+s1);
        System.out.println("Value: "+f2+"\tString: "+s2);
        ICrossover crossover=new OnePointCrossing(24,50,true);
        crossover.cross(s1,s2);
        System.out.println("\nAfter:");
        f1=ftp.binaryToFloat(s1.toString());
        f2=ftp.binaryToFloat(s2.toString());
        System.out.println("Value: "+f1+"\tString: "+s1);
        System.out.println("Value: "+f2+"\tString: "+s2);
    }

    public static void testSelection()
    {
        FloatToBytes ftp=new FloatToBytes(-10,10);
        ISelection sel=new BestSelection(5);
        List<Float> population=new ArrayList<>();
        population.addAll(Arrays.asList(-4.5f,-10f,4.3f,1f,-5f,6f,-3.2f,5.0f,-4f,5.4f));
        print(sel.select(population));

    }

    public static void testMutation()
    {
        FloatToBytes ftp=new FloatToBytes(-10,10);
        float f1=3f,f2=-6f;
        StringBuilder s1=new StringBuilder(ftp.floatToBinary(f1));
        System.out.println("Value: "+f1+"\tString: "+s1);
        IMutation mutation=new OnePointMutation(5,50);
        mutation.mutate(s1);
        System.out.println("\nAfter:");
        f1=ftp.binaryToFloat(s1.toString());
        System.out.println("Value: "+f1+"\tString: "+s1);
    }

    //do wypisywania listy
    public static void print(List<Float> list)
    {
        for(float f : list)
        {
            System.out.println(f);
        }
    }
}
