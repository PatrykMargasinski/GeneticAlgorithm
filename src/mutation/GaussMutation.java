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
    public String mutate(String s1) {
        Float[] ind=new Float[]{
                FloatToBytes.binaryToFloat(s1.substring(0, 24)),
                FloatToBytes.binaryToFloat(s1.substring(24, 48))
        };
        if(checkProbability(probability))
        {
            ind[0]+= (float)random.nextGaussian();
        }
        if(checkProbability(probability))
        {
            ind[1]+= (float)random.nextGaussian();
        }
        return FloatToBytes.floatToBinary(ind[0])+FloatToBytes.floatToBinary(ind[1]);
    }

}
