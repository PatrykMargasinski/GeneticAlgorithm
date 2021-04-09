package mutation;

import someMethods.SomeMethods;

import static someMethods.SomeMethods.generateRandomPoint;

//mutacja dwóch punktów
public class TwoPointMutation implements IMutation {
    int probability;

    public TwoPointMutation(int probability) {
        this.probability = probability;
    }

    @Override
    public String mutate(String s1) {
        StringBuilder sb1=new StringBuilder(s1);
        int p1 = generateRandomPoint(sb1.length());
        int p2 = generateRandomPoint(sb1.length());
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
        return sb1.toString();
    }
}
