package crossing;

import java.util.Random;

public class HeuresticCrossing implements ICrossover{
    Random random=new Random();
    @Override
    public Float[] cross(Float f1, Float f2) {
        //f2 jest wieksze niz f1
        float p=random.nextFloat();
        return new Float[]{
                p*(f2-f1),
                p*(f2-f1)
                };
    }
}
