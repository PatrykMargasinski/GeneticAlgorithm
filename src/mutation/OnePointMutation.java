package mutation;

import someMethods.SomeMethods;

//mutacja jednego punktu
public class OnePointMutation implements IMutation {
    int point;
    int probability;

    public OnePointMutation(int point, int probability) {
        this.point = point;
        this.probability = probability;
    }
    @Override
    public void mutate(StringBuilder sb1) {
        if (SomeMethods.checkProbability(probability)==true) {
            char temp = sb1.charAt(point);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(point, temp);
        }
    }
}
