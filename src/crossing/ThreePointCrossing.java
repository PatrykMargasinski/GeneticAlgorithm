package crossing;

import someMethods.SomeMethods;

import java.util.List;

public class ThreePointCrossing implements ICrossover {
    int probability;
    boolean startingWithCrossing;//True, cross od p1 do p2. False - od 0 do p1 i od p2 do końca

    public ThreePointCrossing(int probability, boolean startingWithCrossing)
    {

        this.probability=probability;
        this.startingWithCrossing=startingWithCrossing;
    }

    @Override
    public String[] cross(String s1, String s2) {
        StringBuilder sb1=new StringBuilder(s1);
        StringBuilder sb2=new StringBuilder(s2);
        List<Integer> points=SomeMethods.getSomeVariousNumbers(3,24);
        int p1 = points.get(0);
        int p2 = points.get(1);
        int p3 = points.get(2);
        boolean crossCondition=startingWithCrossing;
        for(int i=0;i<s1.length();i++)
        {
            if(i==p1||i==p2||i==p3) crossCondition=!crossCondition;
            if(crossCondition && SomeMethods.checkProbability(probability)==true) {
                char temp = sb1.charAt(i);
                sb1.setCharAt(i, sb2.charAt(i));
                sb2.setCharAt(i, temp);
            }
        }
        return new String[]{sb1.toString(),sb2.toString()};
    }
}
