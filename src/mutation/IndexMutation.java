package mutation;

import someMethods.SomeMethods;

import java.util.Random;

import static someMethods.SomeMethods.generateRandomPoint;

//ciagly swap punktow z zakresu (p1,p2)
public class IndexMutation implements IMutation {
    int probability;

    public IndexMutation(int probability) {
        this.probability = probability;
    }

    @Override
    public String mutate(String s1) {
        StringBuilder sb1=new StringBuilder(s1);
        int p1 = generateRandomPoint(sb1.length());
        int p2 = generateRandomPoint(sb1.length());
        if(p1>p2)
        {
            int temp=p1;
            p1=p2;
            p2=temp;
        }
        for(int i=0;i<p2-p1;i++) {
            if (SomeMethods.checkProbability(probability) == true) {
                char temp = sb1.charAt(p1+i);
                sb1.setCharAt(p1+i,sb1.charAt(p2-i));
                sb1.setCharAt(p2-i, temp);
            }
        }
        return sb1.toString();
    }

}
