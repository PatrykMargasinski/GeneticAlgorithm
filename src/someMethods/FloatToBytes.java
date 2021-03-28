package someMethods;


//modyfikowalem tu troche. Moglem popsuć, jest taka opcja xd
public class FloatToBytes {
    int a;
    int b;

    public FloatToBytes(int a, int b)
    {
        this.a=a;
        this.b=b;
    }

    private String to24Bytes(String binary)
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
    public float binaryToFloat(String binary){
        return (float) (a+binaryToDecimal(binary)*(b-a)/(Math.pow(2,binary.length())-1));
    }
    private int binaryToDecimal(String bin) {
        return Integer.parseInt(bin,2);
    }

    //calculating length
    private int log2(int N)
    {
        return (int)(Math.log(N) / Math.log(2));
    }
    private int calculateLength(){
        return log2((int) ((b - a) * Math.pow(10, 6))) + log2(1);
    }

    //float to binary
    public String floatToBinary(float value){
        float x= (float) ((value-a)/((b-a)/((Math.pow(2,calculateLength()))-1)));
        return to24Bytes(decimalToBinary(x));
    }

    private static String decimalToBinary(float value){
        return Integer.toBinaryString((int) value);
    }
}
