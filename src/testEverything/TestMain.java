package testEverything;


import java.io.IOException;
import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) throws IOException {
        int x=0;
        while (true)
        {
            Scanner scan=new Scanner(System.in);

            if (scan.hasNextInt())
            {
                x= scan.nextInt();
                break;
            }
            else
            {
                System.out.println("输入的不是整数，重新输入：");
                scan.close();
            }
        }
        System.out.println(x);

    }
}
