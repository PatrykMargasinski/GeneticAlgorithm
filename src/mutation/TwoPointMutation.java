package mutation;

import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import static someMethods.SomeMethods.generateRandomPoint;

//mutacja dwóch punktów
public class TwoPointMutation implements IMutation {
    int probability;

    public TwoPointMutation(int probability) {
        this.probability = probability;
    }

    @Override
    public Float[] mutate(Float[] f1) {
        StringBuilder sb1=new StringBuilder(FloatToBytes.floatToBinary(f1[0]) + FloatToBytes.floatToBinary(f1[1]));
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
        String s1=sb1.toString();
        return new Float[]{
                FloatToBytes.binaryToFloat(s1.substring(0, 24)),
                FloatToBytes.binaryToFloat(s1.substring(24, 48))
        };
    }
}
