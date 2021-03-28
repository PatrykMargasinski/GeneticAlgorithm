package mutation;

import someMethods.SomeMethods;

//mutacja pierwszego lub ostatniego
public class BorderMutation implements IMutation {
    int probability;
    boolean leftBorder;

    public BorderMutation(boolean leftBorder, int probability)
    {
        this.probability=probability;
        this.leftBorder=leftBorder;//true - mutowany gen o indeksie 0. False - ostatni
    }

    @Override
    public void mutate(StringBuilder sb1) {
        int borderIndex=leftBorder?0:sb1.length()-1;
        if (SomeMethods.checkProbability(probability)==true) {
            char temp=sb1.charAt(borderIndex);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(borderIndex, temp);
        }
    }
}
