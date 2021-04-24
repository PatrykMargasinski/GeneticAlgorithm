package crossing;

import someMethods.FloatToBytes;
import someMethods.SomeMethods;

//wymiana co drugi gen
public class HomogeneousCrossing implements ICrossover {
    int probability;
    boolean even;//even=true - wymienia parzyste indeksy. W przeciwnym wypadku nieparzyste

    public HomogeneousCrossing(boolean even,int probability)
    {
        this.probability=probability;
        this.even=even;
    }

    @Override
    public Float[] cross(Float s1, Float s2) {
        StringBuilder sb1=new StringBuilder(FloatToBytes.floatToBinary(s1));
        StringBuilder sb2=new StringBuilder(FloatToBytes.floatToBinary(s2));
        for(int i=0;i<sb1.length();i++)
        {
            boolean crossCondition=even?i%2==0:i%2==1;
            if(crossCondition && SomeMethods.checkProbability(probability)==true)
            {
                char temp=sb1.charAt(i);
                sb1.setCharAt(i,sb2.charAt(i));
                sb2.setCharAt(i,temp);
            }
        }
        return new Float[]{
                FloatToBytes.binaryToFloat(sb1.toString()),
                FloatToBytes.binaryToFloat(sb2.toString())};
    }
}
