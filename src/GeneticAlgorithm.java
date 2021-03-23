import java.util.*;

public class GeneticAlgorithm
{
    int populationAmount;
    List<Float> population=new ArrayList<>();
    float epochsNumber;

    public GeneticAlgorithm(int populationAmount, float epochsNumber)
    {
        this.populationAmount=populationAmount;
        this.epochsNumber=epochsNumber;
        Random random=new Random();
        for(int i=0;i<populationAmount;i++)
        {
            population.add(random.nextFloat()*100-50);
        }
    }

    float bestPercent=0.4f;

    public List<Float> bestSelection()
    {
        List<Float> chosenOnes=new ArrayList<>();
        Collections.sort(population,Comparator.comparing(Main::fun).reversed());
        for(int i=0;i<population.size()*bestPercent;i++)
            chosenOnes.add(population.get(i));
        return chosenOnes;
    }

    float selectionBorder=populationAmount/4f;

    public List<Float> tournamentSelection()
    {
        List<Float> chosenOnes=new ArrayList<>();
        for(float i=0;i<population.size();i+=selectionBorder)
        {
            chosenOnes.add(findMin((int)i, (int)(i+selectionBorder)));
        }
        return chosenOnes;
    }

    float findMin(int a, int b)
    {
        float min=Float.MAX_VALUE;
        float minValue=Float.MAX_VALUE;
        for(int i=a;i<b;i++)
        {
            if(Main.fun(population.get(i))<minValue)
            {
                min=population.get(i);
                minValue=Main.fun(population.get(i));
            }
        }
        return min;
    }

    int chosenAmount=4;

    public List<Float> rouletteSelection()
    {
        List<Float> chosenOnes=new ArrayList<>();
        int sum=0;
        for(int i=0;i<population.size();i++)
        {
            sum+=Main.fun(population.get(i));
        }
        Random random=new Random();
        int curChosen=0;
        while(curChosen<chosenAmount) {
            int randomNumber = random.nextInt() % sum;
            int i = 0, curSum = 0;
            while (i < populationAmount) {
                curSum += Main.fun(population.get(i));
                if (curSum > randomNumber) {
                    chosenOnes.add(population.get(i));
                    curChosen++;
                    break;
                }
            }
        }
        return chosenOnes;
    }

    public void onePointCrossing(int point, StringBuilder sb1, StringBuilder sb2)
    {
        for(int i=0;i<point;i++)
        {
            char temp=sb1.charAt(i);
            sb1.setCharAt(i,sb2.charAt(i));
            sb2.setCharAt(i,temp);
        }
    }

    public void twoPointCrossing(int p1, int p2, StringBuilder sb1, StringBuilder sb2)
    {
        for(int i=0;i<p1;i++)
        {
            char temp=sb1.charAt(i);
            sb1.setCharAt(i,sb2.charAt(i));
            sb2.setCharAt(i,temp);
        }

        for(int i=p2;i<sb1.length();i++)
        {
            char temp=sb1.charAt(i);
            sb1.setCharAt(i,sb2.charAt(i));
            sb2.setCharAt(i,temp);
        }
    }

    public void homogeneousCrossing(StringBuilder sb1, StringBuilder sb2)
    {
        for(int i=0;i<sb1.length();i++)
        {
            if(i%2==0)
            {
                char temp=sb1.charAt(i);
                sb1.setCharAt(i,sb2.charAt(i));
                sb2.setCharAt(i,temp);
            }
        }
    }
}
