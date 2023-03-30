/**
 * 本程序可以计算中缀表达式
 */
package methmatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * 中缀表达式计算类，内部提供方法可直接计算该中缀表达式的结果
 * <h1>运算中缀表达式的全过程如下：</h1>
 * 总的过程即先将中缀表达式转换为后缀表达式，然后用栈实现后缀表达式的计算，但该类采用一边转化一边计算的方法得出中缀表达式的结构
 * 中缀表达式转换为后缀表达式的算法：初始化一个栈，用于保存暂时还不能确定运算顺序的运算符，从左到右处理各个元素，直到末尾，可能
 * 遇到三种情况<br/>
 * 1、遇到操作数，直接加入后缀表达式<br/>
 * 2、遇到界限符，遇到"("则直接入栈，遇到")"则依次弹出栈内运算符并直接加入后缀表达式，直到弹出"("为止，注意此时不用将
 * "("加入后缀表达式<br/>
 * 3、遇到运算符，则依次弹出栈中优先级高于或等于当前运算符的所有运算符，并加入后缀表达式，若遇到"("或栈空则停止，之后再将当前
 * 运算符入栈，按上述方法处理完所有字符后，将栈中剩余运算符依次弹出并加入后缀表达式<br/>
 * <p>用栈实现后缀表达式的计算</p>
 * 1、从左到右依次扫描元素，直到处理完所有元素<br/>
 * 2、若扫描到操作数则压栈，并回到第一步，否则执行第三步<br/>
 * 3、若扫描到运算符，则弹出操作数栈中的两个栈顶元素，执行相关运算，然后将运算结果压回栈顶<br/>
 * <p>直接用栈实现中缀表达式的计算</p>
 * 1、初始化两个栈，操作数栈和运算符栈<br/>
 * 2、若扫描到操作数，直接压入操作数栈<br/>
 * 3、若扫描到运算符或界限符，则按照中缀转后缀相同的逻辑压入运算符栈（期间回弹出运算符，每弹出一个运算符，就需要再弹出两个操作
 * 数栈的栈顶元素并执行相关运算，运算结果再压回栈顶）<br/>
 * <p>用栈将中缀表达式转换为后缀表达式</p>
 * 1、若扫描到操作数直接输出到后缀表达式
 * 2、若扫描到操作符，若栈为空则直接入栈，若栈不空，分情况：1》栈顶是左括号则直接入栈 2》栈顶是优先级比自己高的运算符则弹栈直到遇到左括号或者栈顶优先级比自己低或者栈空，此时入栈
 */
public class SuffixExpressionCalculator {
    /**
     * 操作数栈
     */
    private final Stack<String> numberStack;
    /**
     * 运算符栈
     */
    private final Stack<String> operatorStack;

    /**
     * 表达式数组
     */
    private ArrayList<String> expressionArray;

    public SuffixExpressionCalculator() {
        numberStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    /**
     * 该函数用在{@link SuffixExpressionCalculator#setExpressionArray(String[])}中，目的
     * 是检测传入的中缀表达式是否均由小数或整数或运算符组成
     *
     * @param expressionArray 代表待检测的中缀表达式
     * @return boolean 如果符合规则则返回true否则返回false
     */
    private static boolean verifyExpression(ArrayList<String> expressionArray) {
        for (String s : expressionArray) {
            if (!isNumber(s) && !isBracket(s) && !isOperator(s))
                return false;//如果该符号既不是数字，也不是括号，也不是四则运算符号则返回false，说明该表达式不符合要求
        }
        return true;
    }

    /**
     * 判断是否是数字，如果是则返回true，如果不是则返回false
     *
     * @param element 表示传入的符号
     * @return boolean 若为true则是，否则不是
     */
    private static boolean isNumber(String element) {
        try {
            double test = Double.parseDouble(element);//如果在这里转换失败会抛出一个异常
        } catch (NumberFormatException e) {
            return false;//转换失败说明该字符不是数字，可能是其他字符
        }
        return true;
    }

    /**
     * 判断是不是括号，如果是则返回true，如果不是则返回false。
     *
     * @param element 表示传入的符号
     * @return boolean 若为true则是，否则不是
     */
    private static boolean isBracket(String element) {
        return element.equals("(") || element.equals(")");
    }

    /**
     * 判断该符号是否是四则运算符号
     *
     * @param element 待判断的符号
     * @return boolean 若为true则是，否则不是
     */
    private static boolean isOperator(String element) {
        return element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/");
    }

    /**
     * @param numB        第二个操作数
     * @param numA        第一个操作数
     * @param theOperator 代表四则运算符中的一种运算符
     * @implNote 对两个操作数执行运算操作
     */
    private static double operateTwoNumbers(double numA, double numB, String theOperator) {
        return switch (theOperator) {
            case "+" -> numA + numB;
            case "-" -> numA - numB;
            case "*" -> numA * numB;
            case "/" -> numA / numB;
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * @param operatorA 扫描到的当前运算符
     * @param operatorB 从操作符栈中弹出的运算符
     * @return boolean 返回为true时，第一个参数所代表的运算符优先级小于或等于第二个参数所代表的运算符的优先级，否则返回false
     * @implNote 比较两个运算符的优先级
     */
    private static boolean priorityComparison(String operatorA, String operatorB) {
        if (operatorA.equals("+") || operatorA.equals("-"))
            return true;//此时operatorA的优先级一定比较低，所以代表了扫描到的当前运算符的优先级低于或等于运算符栈中的运算符
        else {
            return operatorB.equals("*") || operatorB.equals("/");//此时operatorA是×或÷，如果operatorB也是×或÷，那么说明此时operatorA的优先级满足小于或等于operatorB的优先级，可以返回true
        }
    }

    /**
     * 设置表达式数组
     * InfixExpression类的setter方法，主要用途将传入容器的引用赋给内部的中缀表达式容器
     * 每次使用该类对象计算表达式，调用此方法重置内部的表达式
     *
     * @param stringArr 传入的字符串数组，代表传入的中缀表达式
     */
    public void setExpressionArray(String[] stringArr) {
        ArrayList<String> expressionArray = new ArrayList<>(Arrays.stream(stringArr).toList());
        if (!verifyExpression(expressionArray)) throw new IllegalStateException("传入的中缀表达式中有非法符号");
        this.expressionArray = expressionArray;
    }

    /**
     * 控制整个中缀表达式的运算过程，调用各个私有接口返回整个中缀表达式的运算结果
     */
    public double result() {
        for (String symbol : expressionArray) {
            if (isNumber(symbol))
                //如果该符号是操作数则直接压入操作数栈
                numberStack.push(symbol);
            else {
                //如果该符号是运算符或着是界限符号

                if (isBracket(symbol)) {
                    //该符号是界限符的情况
                    if (symbol.equals("("))
                        //当界限符是左括号的时候直接压栈
                        operatorStack.push(symbol);
                    else {
                        //当界限符是右括号的时候依次弹出运算符栈内的运算符，每弹出一个运算符就从操作数栈中取出两个操作数和这个运算符做运算，并将运算结果压回操作数栈
                        //直到遇到左括号就终止上面的操作，终止操作之后一定要将此时运算符栈顶的左括号弹出

                        while (!Objects.equals(operatorStack.peek(), "("))//利用工具类Objects中的静态方法比较两个字符串的内容是否相等
                        {
                            //各基本类型包装器中有一个静态方法可以将字符串转换为自己所代表的基本类型，比如下面的Double.parseDouble()可以将传入函数的String值转换为double值
                            double numA = Double.parseDouble(numberStack.pop());//注意java中的pop和C++中的pop不一样，java中的pop可以直接返回弹出的元素但C++中的不行
                            double numB = Double.parseDouble(numberStack.pop());
                            double resultObj = operateTwoNumbers(numB, numA, operatorStack.pop());//将结果包装成对象，可以调用其中的函数再次将其转换为String类型
                            numberStack.push(Double.toString(resultObj));

                        }
                        operatorStack.pop();//不要忘记弹出此时栈顶的左括号
                    }

                } else {
                    //当前扫描到的是运算符
                    if (!operatorStack.empty()) {
                        //运算符栈不为空，可能有三种情况：1、运算符栈顶是左括号，此时直接压栈 2、运算符栈顶的运算符的优先级小于当前运算符的优先级，此时直接压栈 3、运算符栈顶的运算符优先级大于等于当前运算符的优先级，不断执行构建过程，每次执行前都要判断栈顶运算符优先级和当前操作符优先级，只有满足要求时才能继续执行
                        if (operatorStack.peek().equals("(") || !priorityComparison(symbol, operatorStack.peek())) {
                            //第一种情况和第二种情况的操作相同
                            operatorStack.push(symbol);
                        } else {
                            while (!operatorStack.isEmpty() && priorityComparison(symbol, operatorStack.peek())) {
                                double numA = Double.parseDouble(numberStack.pop());
                                double numB = Double.parseDouble(numberStack.pop());
                                double resultObj = operateTwoNumbers(numB, numA, operatorStack.pop());
                                numberStack.push(Double.toString(resultObj));
                            }
                            operatorStack.push(symbol);
                        }
                    } else {
                        //如果此时运算符栈中没有任何运算符，直接将当前扫描到的运算符压入运算符栈
                        operatorStack.push(symbol);
                    }
                }
            }
        }
        //for循环结束并不代表运算结束，此时已经把中缀表达式数组中的所有数和运算符以及界限符按照一定规则压入了两个栈中，现在只需要不断弹出运算符栈中的运算符和操作数栈中的数字做运算即可得出结果
        while (!operatorStack.empty()) {
            double numA = Double.parseDouble(numberStack.pop());
            double numB = Double.parseDouble(numberStack.pop());
            double resultObj = operateTwoNumbers(numB, numA, operatorStack.pop());
            numberStack.push(Double.toString(resultObj));
        }
        //最后运算结果一定会在操作数栈底，只需将其弹出即可得到结果
        return Double.parseDouble(numberStack.pop());
    }
}

class TestSuffixCalculator {
    public static void main(String[] args) {
        SuffixExpressionCalculator calculator = new SuffixExpressionCalculator();
        String[] expression = {"2", "+", "3", "-", "4", "*", "3", "/", "4", "+", "7"};
        String[] expressions2 = {"85", "+", "14", "*", "(", "14", "/", "208", "+", "26", ")", "*", "21", "+", "(", "327", "-", "23", ")", "/", "19"};
        String[] expression3 = {"1.2", "+", "(", "2.7", "-", "3.4", ")", "*", "4.4", "+", "4.1", "/", "2.8"};
        calculator.setExpressionArray(expression3);
        double result = calculator.result();
        System.out.println("结果=" + result);
    }

}
