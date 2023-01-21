public class TestAnything {


    public static void main(String[] args) {

System.out.println(byteToBitString(false,(byte)-15));


    }
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//先将byte转换成int
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }



}

