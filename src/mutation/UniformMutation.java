package mutation;

import someMethods.SomeMethods;

import java.util.List;
import java.util.Random;

import static someMethods.SomeMethods.generateRandomPoint;

//mutacja jednego punktu z zakresu (p1,p2)
public class UniformMutation implements IMutation {
    int probability;

    public UniformMutation(int probability) {
        this.probability = probability;
    }
    Random random=new Random();
    @Override
    public String mutate(String s1) {
        StringBuilder sb1=new StringBuilder(s1);
        List<Integer> points=SomeMethods.getSomeVariousNumbers(2,24);
        int p1 = points.get(0);
        int p2 = points.get(1);
        if(p1>p2)
        {
            int temp=p1;
            p1=p2;
            p2=temp;
        }
        int point=random.nextInt(100)%(p2-p1)+p1;
        if (SomeMethods.checkProbability(probability) == true) {
            char temp = sb1.charAt(point);
            if (temp == '0') temp = '1';
            else temp = '0';
            sb1.setCharAt(point, temp);
        }
        return sb1.toString();
    }

}
