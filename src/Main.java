import java.util.*;

public class Main {
    static Float fun(float x)
    {
        return x*x;
    }

    public static void main(String[] args) {
        GeneticAlgorithm gen=new GeneticAlgorithm(10,5);

        StringBuilder a=new StringBuilder("11111111");
        StringBuilder b=new StringBuilder("00000000");
        gen.homogeneousCrossing(a,b);
        System.out.println(a+" "+b);
    }
}
