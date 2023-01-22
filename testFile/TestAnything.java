public class TestAnything {


    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("hello mr.robot");
        builder.delete(1, 3);
        System.out.println(builder);
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//先将byte转换成int
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }


}

