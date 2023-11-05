package testpack;

public class TestAnyThing {
    int father = 13;

    public static void main(String[] args) {
        int a = 12;
        String str = String.valueOf(a);
        System.out.println(a);

        double b = 12.2342;
        String str2 = Double.toString(b);

        String str3 = "12.4323";
        Double c = Double.valueOf(str3);
        double c2 = Double.parseDouble(str3);
    }
}
