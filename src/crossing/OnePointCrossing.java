package crossing;

import someMethods.SomeMethods;

public class OnePointCrossing implements ICrossover {
    int point;
    int probability;
    boolean startWithTheLeft;//true - zaczyna od lewej do punktu. False - od punktu do prawej

    public OnePointCrossing(int point, int probability, boolean startWithTheLeft)
    {
        this.point=point;
        this.probability=probability;
        this.startWithTheLeft=startWithTheLeft;
    }

    @Override
    public void cross(StringBuilder sb1, StringBuilder sb2) {
        for(int i=0;i<sb1.length();i++)
        {
            boolean crossCondition=startWithTheLeft?i<=point:i>=point;
            if(crossCondition && SomeMethods.checkProbability(probability)==true) {
                char temp = sb1.charAt(i);
                sb1.setCharAt(i, sb2.charAt(i));
                sb2.setCharAt(i, temp);
            }
        }
    }
}
