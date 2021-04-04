package mutation;

import someMethods.SomeMethods;

import static someMethods.SomeMethods.generateRandomPoint;

//mutacja jednego punktu
public class OnePointMutation implements IMutation {
    int probability;

    public OnePointMutation(int probability) {
        this.probability = probability;
    }

    @Override
    public void mutate(StringBuilder sb1) {
        int point = generateRandomPoint(sb1.length());
        if (SomeMethods.checkProbability(probability) == true) {
            char temp = sb1.charAt(point);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(point, temp);
        }
    }

}
