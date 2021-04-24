package crossing;

import someMethods.SomeMethods;

import java.util.Random;

public class ArithmeticCrossing implements ICrossover{
    Random random=new Random();

    @Override
    public Float[] cross(Float f1, Float f2) {
        float p=random.nextFloat();
        return new Float[]{f1*p,f2*(1-p)};
    }
}
