package inversion;

import someMethods.SomeMethods;

import java.util.Random;

import static someMethods.SomeMethods.generateRandomPoint;

public class Inversion implements IInversion {
    int probability;

    public Inversion(int probability) {
        this.probability = probability;
    }

    @Override
    public void invert(StringBuilder sb1) {
        if (SomeMethods.checkProbability(probability)) {
            int start = generateRandomPoint(sb1.length());
            int end = generateRandomPoint(sb1.length());
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
