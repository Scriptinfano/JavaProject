import java.util.Scanner;

public class Utility {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 返回用户输入的菜单选项*/
    public static String readMenuSelection() {
        String userInput;
        while (true) {
            userInput = stringRead(1);
            if (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3") && !userInput.equals("4")) {
                System.out.println("选择错误，请重新输入");
            } else break;
        }
        return userInput;
    }

    /**
     * 当用户输入Y或y时返回true，当用户输入n或N时返回false
     */
    public static boolean readConfirmSelection() {
        while (true) {
            String userInput = stringRead(1);
            if (!userInput.equals("Y") && !userInput.equals("N") && !userInput.equals("y") && !userInput.equals("n")) {
                System.out.println("选择错误，请重新输入");
            } else {
                return userInput.equals("Y") || userInput.equals("y");
            }
        }
    }

    /**
     * 读取用户输入的现金数，并返回该数*/
    public static int readNumber() {
        int cash;
        while (true) {
            String userCash = stringRead(10);
            try {
                cash = Integer.parseInt(userCash);
                break;
            } catch (NumberFormatException theException) {
                System.err.println("数字输入错误，请重新输入");
            }
        }
        return cash;
    }

    /**
     * 读取用户输入的字符串，如果字符串的长度大于指定长度则要求重新输入，若成功输入则返回该字符串*/
    public static String stringRead(final int limitLength) {
        String theStr;
        while (true) {
            theStr = scanner.nextLine();
            if (theStr.length() > limitLength) {
                System.err.println("输入长度不得大于" + limitLength + ",请重新输入");
            } else break;
        }
        return theStr;
    }


}
