public class FloatToBytes {
    int a,b;
    private static int binaryToDecimal(String bin) {
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
    private String floatToBinary(float value){
        float x= (float) ((value-a)/((b-a)/((Math.pow(2,calculateLength()))-1)));
        return decimalToBinary(x);
    }

    private String decimalToBinary(float value){
        return Integer.toBinaryString((int) value);
    }
}
