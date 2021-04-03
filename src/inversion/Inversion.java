package inversion;

import someMethods.SomeMethods;

import java.util.Random;

public class Inversion implements IInversion {
    int probability;

    public Inversion(int probability) {
        this.probability = probability;
    }

    @Override
    public void invert(StringBuilder sb1) {
        if (SomeMethods.checkProbability(probability)) {
            Random random = new Random();
            int start = random.nextInt(sb1.length());
            int end = random.nextInt(sb1.length());
            if (start > end) {
                int x = start;
                start = end;
                end = x;
            }
            for (int i = start; i < end; i++) {
                if (sb1.charAt(i) == '0') {
                    sb1.setCharAt(i, '1');
                } else {
                    sb1.setCharAt(i, '0');
                }
            }
        }
    }
}
