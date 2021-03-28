package mutation;

import someMethods.SomeMethods;

//mutacja dwóch punktów
public class TwoPointMutation implements IMutation {
    int p1,p2;
    int probability;

    public TwoPointMutation(int p1, int p2, int probability)
    {
        this.p1=p1;
        this.p2=p2;
        this.probability=probability;
    }

    @Override
    public void mutate(StringBuilder sb1) {
        if (SomeMethods.checkProbability(probability) == true) {
            char temp = sb1.charAt(p1);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(p1, temp);

            temp = sb1.charAt(p2);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(p2, temp);
        }
    }
}
