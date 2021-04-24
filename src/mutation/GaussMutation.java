package mutation;

import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import java.util.Random;

import static someMethods.SomeMethods.checkProbability;
import static someMethods.SomeMethods.generateRandomPoint;

//mutacja jednego punktu z zakresu (p1,p2)
public class GaussMutation implements IMutation {
    int probability;

    public GaussMutation(int probability) {
        this.probability = probability;
    }
    Random random=new Random();
    @Override
    public Float[] mutate(Float[] f1) {
        if(checkProbability(probability))
        {
            if(SomeMethods.checkProbability(50))
                f1[0]+= (float)random.nextGaussian();
            else
                f1[1]+= (float)random.nextGaussian();
            if(f1[0]>FloatToBytes.b)f1[0]=10f;
            if(f1[1]>FloatToBytes.b)f1[1]=10f;
            if(f1[0]<FloatToBytes.a)f1[0]=-10f;
            if(f1[1]<FloatToBytes.a)f1[1]=-10f;
        }
        return f1;
    }

}
