public class TestAnything {


    public static void main(String[] args) {
       /* StringBuilder builder = new StringBuilder("hello");
        builder.delete(0,5);
        System.out.println(builder);*/
        /*StringBuilder builder = new StringBuilder();
        List<Character> decodeList = new ArrayList<>();
        decodeList.add('a');
        decodeList.add('p');
        decodeList.add('p');
        decodeList.add('A');
        decodeList.add('A');
        decodeList.add('A');
        decodeList.add('A');
        decodeList.forEach(builder::append);
       System.out.println(builder);*/

        String str = "0111010000010000100010011111011100111110100001101011000111001101101011100001111011111101001101111010";
        String str2 = "0111010000010000100010011111011100111110100001101011000111001101101011100001111011111101001101111010";

        System.out.println(str.equals(str2));
        System.out.println();

    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;//先将byte转换成int
        if (flag)
            temp |= 256;
        String str = Integer.toBinaryString(temp);
        return str.substring(str.length() - 8);
    }


}

