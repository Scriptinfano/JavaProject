package mathmetic.suffixExpression;

import java.util.ArrayList;
import java.util.Collections;

class Tester {

    public static void main(String[] args) {
    }

    private static ArrayList<String> strArrayToList(String[] strArray) {
        ArrayList<String> infixExpressionList = new ArrayList<String>();
        Collections.addAll(infixExpressionList, strArray);
        return infixExpressionList;
    }

    private static void testCalculatorA() {
        myformat.Formatter formatter = new myformat.Formatter();
        formatter.setPrecision(3);
        String[] infixExpressionArray = {"1.2", "+", "(", "2.7", "-", "3.4", ")", "*", "4.4", "+", "4.1", "/", "2.8"};
        ArrayList<String> infixExpressionList = strArrayToList(infixExpressionArray);
        SuffixExpressionCalculatorA expressionCalculator = new SuffixExpressionCalculatorA();
        expressionCalculator.setExpressionArray(infixExpressionList);
        double result = expressionCalculator.result();
        System.out.println(formatter.getFormatter().format(result));

    }

    private static void testCalculatorB() {
        String[] expressions = {"2", "+", "3", "-", "4", "*", "3", "/", "4", "+", "7"};
        String[] expressions2 = {"85", "+", "14", "*", "(", "14", "/", "208", "+", "26", ")", "*", "21", "+", "(", "327", "-", "23", ")", "/", "19"};
        SuffixExpressionCalculatorB calculator = new SuffixExpressionCalculatorB(expressions);
        calculator.run();
    }

    private static void testCalculatorC() {
        String expression = "85+14*(14/208+26)*21+(327-23)/19";
        SuffixExpressionCalculatorC calculator = new SuffixExpressionCalculatorC(expression);
        calculator.run();

    }
}
