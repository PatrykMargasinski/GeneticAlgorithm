package someMethods;


//modyfikowalem tu troche. Moglem popsuć, jest taka opcja xd
public class FloatToBytes {
    static int a=-10;
    static int b=10;

    private static String to24Bytes(String binary)
    {
        char sign='0';
        StringBuilder str=new StringBuilder(binary);
        while(str.length()<24)
        {
            str.insert(0,sign);
        }
        return str.toString();
    }


    //binary to float
    public static float binaryToFloat(String binary){
        return (float) (a+binaryToDecimal(binary)*(b-a)/(Math.pow(2,binary.length())-1));
    }
    private static int binaryToDecimal(String bin) {
        return Integer.parseInt(bin,2);
    }

    //calculating length
    private static int log2(int N)
    {
        return (int)(Math.log(N) / Math.log(2));
    }
    private static int calculateLength(){
        return log2((int) ((b - a) * Math.pow(10, 6))) + log2(1);
    }

    //float to binary
    public static String floatToBinary(float value){
        float x= (float) ((value-a)/((b-a)/((Math.pow(2,calculateLength()))-1)));
        return to24Bytes(decimalToBinary(x));
    }

    private static String decimalToBinary(float value){
        return Integer.toBinaryString((int) value);
    }
}
