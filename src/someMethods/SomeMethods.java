package someMethods;

import java.io.FileWriter;
import java.util.Random;

public class SomeMethods {
    public static Float fun(float x)
    {
        return x*x;
    }

    //jest okreslona szansa na mutacje lub krzyżowanie. Tutaj jest metoda sprawdzająca, czy strzął o prawdopodobieństwie
    //"probability" (0-100) jest udany
    public static boolean checkProbability(int probability)
    {
        Random ran=new Random();
        int drawn=ran.nextInt(100);
        return drawn+1 <= probability;
    }

    //nadpisywanie pliku, może na jakieś logi się przyda
    public static void writeToFile(String message){
        try {
            FileWriter zapis = new FileWriter("log.txt",true);
            zapis.write(message);
            zapis.close();
        }
        catch (Exception ex)
        {
            System.out.print("Nie dziala");
        }
    }
}
