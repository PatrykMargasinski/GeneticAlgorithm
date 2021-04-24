package mutation;

import someMethods.FloatToBytes;
import someMethods.SomeMethods;

import java.util.List;
import java.util.Random;

import static someMethods.SomeMethods.checkProbability;
import static someMethods.SomeMethods.generateRandomPoint;

//mutacja jednego punktu z zakresu (p1,p2)
public class UniformMutation implements IMutation {
    int probability;

    public UniformMutation(int probability) {
        this.probability = probability;
    }
    Random random=new Random();
    @Override
    public Float[] mutate(Float[] f1) {
        if(checkProbability(probability))
        {
            float number=random.nextFloat()*(random.nextInt(20) - FloatToBytes.b);
            if(SomeMethods.checkProbability(50))
                f1[0]+= number;
            else
                f1[1]+= number;
            if(f1[0]>FloatToBytes.b)f1[0]=10f;
            if(f1[1]>FloatToBytes.b)f1[1]=10f;
            if(f1[0]<FloatToBytes.a)f1[0]=-10f;
            if(f1[1]<FloatToBytes.a)f1[1]=-10f;
        }
        return f1;
    }

}
