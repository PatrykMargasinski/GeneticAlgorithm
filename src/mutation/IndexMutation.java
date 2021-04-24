package mutation;

//ciagly swap punktow z zakresu (p1,p2)
public class IndexMutation implements IMutation {
    int probability;

    public IndexMutation(int probability) {
        this.probability = probability;
    }

    @Override
    public Float[] mutate(Float[] f1) {
        Float temp=f1[0];
        f1[0]=f1[1];
        f1[1]=temp;
        return f1;
    }

}
