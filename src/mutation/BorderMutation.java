package mutation;

import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import java.util.Random;

//mutacja pierwszego lub ostatniego
public class BorderMutation implements IMutation {
    int probability;

    public BorderMutation(int probability) {
        this.probability = probability;
    }

    @Override
    public Float[] mutate(Float[] f1) {
        StringBuilder sb1=new StringBuilder(FloatToBytes.floatToBinary(f1[0]) + FloatToBytes.floatToBinary(f1[1]));
        int borderIndex = lastOrFirst(sb1.length() - 1);
        if (SomeMethods.checkProbability(probability)) {
            char temp = sb1.charAt(borderIndex);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(borderIndex, temp);
        }
        String s1=sb1.toString();
        return new Float[]{
                FloatToBytes.binaryToFloat(s1.substring(0, 24)),
                FloatToBytes.binaryToFloat(s1.substring(24, 48))
        };
    }

    private int lastOrFirst(int lastIndex) {
        Random random = new Random();
        if (random.nextBoolean()) {
            return 0;
        } else {
            return lastIndex;
        }
    }
}
