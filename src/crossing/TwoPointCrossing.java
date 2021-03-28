package crossing;

import someMethods.SomeMethods;

public class TwoPointCrossing implements ICrossover {
    int p1,p2;
    int probability;
    boolean crossingInside;//True, cross od p1 do p2. False - od 0 do p1 i od p2 do końca

    public TwoPointCrossing(int p1,int p2, int probability, boolean crossingInside)
    {
        this.p1=p1;
        this.p2=p2;
        this.probability=probability;
        this.crossingInside=crossingInside;
    }

    @Override
    public void cross(StringBuilder sb1, StringBuilder sb2) {
        for(int i=0;i<p1;i++)
        {
            boolean crossCondition=crossingInside?i>=p1&&i<=p2:i<=p1 || i>=p2;
            if(crossCondition && SomeMethods.checkProbability(probability)==true) {
                char temp = sb1.charAt(i);
                sb1.setCharAt(i, sb2.charAt(i));
                sb2.setCharAt(i, temp);
            }
        }
    }
}
