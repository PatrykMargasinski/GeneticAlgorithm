package crossing;

import someMethods.SomeMethods;

public class OnePointCrossing implements ICrossover {
    int probability;
    boolean startWithTheLeft;//true - zaczyna od lewej do punktu. False - od punktu do prawej

    public OnePointCrossing(int probability, boolean startWithTheLeft)
    {
        this.probability=probability;
        this.startWithTheLeft=startWithTheLeft;
    }

    @Override
    public String[] cross(String s1, String s2) {
        StringBuilder sb1=new StringBuilder(s1);
        StringBuilder sb2=new StringBuilder(s2);
        int point=SomeMethods.getSomeVariousNumbers(1,24).get(0);
        for(int i=0;i<sb1.length();i++)
        {
            boolean crossCondition=startWithTheLeft?i<=point:i>=point;
            if(crossCondition && SomeMethods.checkProbability(probability)==true) {
                char temp = sb1.charAt(i);
                sb1.setCharAt(i, sb2.charAt(i));
                sb2.setCharAt(i, temp);
            }
        }
        return new String[]{sb1.toString(),sb2.toString()};
    }
}
