package crossing;

import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import java.util.List;

public class TwoPointCrossing implements ICrossover {
    int probability;
    boolean crossingInside;//True, cross od p1 do p2. False - od 0 do p1 i od p2 do końca

    public TwoPointCrossing(int probability, boolean crossingInside)
    {
        this.probability=probability;
        this.crossingInside=crossingInside;
    }

    @Override
    public Float[] cross(Float s1, Float s2) {
        StringBuilder sb1=new StringBuilder(FloatToBytes.floatToBinary(s1));
        StringBuilder sb2=new StringBuilder(FloatToBytes.floatToBinary(s2));
        List<Integer> points=SomeMethods.getSomeVariousNumbers(2,24);
        int p1 = points.get(0);
        int p2 = points.get(1);
        for(int i=0;i<sb1.length();i++)
        {
            boolean crossCondition=crossingInside?i>=p1&&i<=p2:i<=p1 || i>=p2;
            if(crossCondition && SomeMethods.checkProbability(probability)==true) {
                char temp = sb1.charAt(i);
                sb1.setCharAt(i, sb2.charAt(i));
                sb2.setCharAt(i, temp);
            }
        }
        return new Float[]{
                FloatToBytes.binaryToFloat(sb1.toString()),
                FloatToBytes.binaryToFloat(sb2.toString())};
    }
}
