package crossing;

import someMethods.SomeMethods;

public class ThreePointCrossing implements ICrossover {
    int p1,p2,p3;
    int probability;
    boolean startingWithCrossing;//True, cross od p1 do p2. False - od 0 do p1 i od p2 do końca

    public ThreePointCrossing(int p1, int p2, int p3, int probability, boolean startingWithCrossing)
    {
        this.p1=p1;
        this.p2=p2;
        this.p3=p3;
        this.probability=probability;
        this.startingWithCrossing=startingWithCrossing;
    }

    @Override
    public String[] cross(String s1, String s2) {
        StringBuilder sb1=new StringBuilder(s1);
        StringBuilder sb2=new StringBuilder(s2);
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
