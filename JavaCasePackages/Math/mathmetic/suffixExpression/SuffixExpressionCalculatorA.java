/**
 * 本程序可以计算中缀表达式
 */
package mathmetic.suffixExpression;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
/**
 * 中缀表达式类，内部提供方法可直接计算该中缀表达式的结果
 * <p>运算中缀表达式的全过程如下：</p>
 * 总的过程即先将中缀表达式转换为后缀表达式，然后用栈实现后缀表达式的计算，但该类采用一边转化一边计算的方法得出中缀表达式的结构
 * 中缀表达式转换为后缀表达式的算法：初始化一个栈，用于保存暂时还不能确定运算顺序的运算符，从左到右处理各个元素，直到末尾，可能
 * 遇到三种情况
 * <p>1、遇到操作数，直接加入后缀表达式<p>
 * 2、遇到界限符，遇到"("则直接入栈，遇到")"则依次弹出栈内运算符并直接加入后缀表达式，直到弹出"("为止，注意此时不用将
 * "("加入后缀表达式<p>
 * 3、遇到运算符，则依次弹出栈中优先级高于或等于当前运算符的所有运算符，并加入后缀表达式，若遇到"("或栈空则停止，之后再将当前
 * 运算符入栈，按上述方法处理完所有字符后，将栈中剩余运算符依次弹出并加入后缀表达式
 * </p>
 * <p>用栈实现后缀表达式的计算
 * <p>1、从左到右依次扫描元素，直到处理完所有元素<p>
 * 2、若扫描到操作数则压栈，并回到第一步，否则执行第三步<p>
 * 3、若扫描到运算符，则弹出操作数栈中的两个栈顶元素，执行相关运算，然后将运算结果压回栈顶
 * </p>
 * <p>直接用栈实现中缀表达式的计算<p>
 * <p>
 * 1、初始化两个栈，操作数栈和运算符栈<p>
 * 2、若扫描到操作数，直接压入操作数栈<p>
 * 3、若扫描到运算符或界限符，则按照中缀转后缀相同的逻辑压入运算符栈（期间回弹出运算符，每弹出一个运算符，就需要再弹出
 * 两个操作数栈的栈顶元素并执行相关运算，运算结果再压回栈顶）
 * </p>
 */
public class SuffixExpressionCalculatorA {
    /**
     * 操作数栈
     */
    private Stack<String> numberStack;
    /**
     * 运算符栈
     */
    private Stack<String> operatorStack;

    private ArrayList<String> expressionArray;

    public SuffixExpressionCalculatorA() {
        numberStack = new Stack<String>();
        operatorStack = new Stack<String>();
    }

    /**
     * 该函数用在{@link SuffixExpressionCalculatorA#setExpressionArray(ArrayList<String>)}中，目的
     * 是检测传入的中缀表达式是否均由小数或整数或运算符组成
     *
     * @param expressionArray 代表待检测的中缀表达式
     * @return boolean 如果符合规则则返回true否则返回false
     */
    private static boolean verifyExpression(ArrayList<String> expressionArray) {
        for (String s : expressionArray) {
            if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/") && !s.equals("(") && !s.equals(")")) {
                try {
                    double test = Double.parseDouble(s);//如果在这里转换失败会抛出一个异常
                } catch (NumberFormatException e) {
                    return false;//转换失败说明其中包含有非法字符
                }
            }
        }
        return true;
    }

    /**
     * @param element 表示传入的符号
     * @implNote 判断是否是数字，如果是则返回true，如果不是则返回false
     */
    private static boolean isNumber(String element) {
        return !element.equals("+") && !element.equals("-") && !element.equals("*") && !element.equals("/") && !element.equals("(") && !element.equals(")");
    }

    /**
     * @param element 表示传入的符号
     * @implNote 判断是不是括号，如果是则返回true，如果不是则返回false。
     */
    private static boolean isBracket(String element) {
        return element.equals("(") || element.equals(")");
    }

    /**
     * @param numB        第二个操作数
     * @param numA        第一个操作数
     * @param theOperator 代表四则运算符中的一种运算符
     * @implNote 对两个操作数执行运算操作
     */
    private static double operateTwoNumbers(double numA, double numB, String theOperator) {
        if (theOperator.equals("+"))
            return numA + numB;
        else if (theOperator.equals("-"))
            return numA - numB;
        else if (theOperator.equals("*"))
            return numA * numB;
        else if (theOperator.equals("/"))
            return numA / numB;
        else throw new IllegalArgumentException();
    }

    /**
     * @param operatorA 扫描到的当前运算符
     * @param operatorB 从操作符栈中弹出的运算符
     * @return boolean 返回为true时，第一个参数所代表的运算符优先级小于或等于第二个参数所代表的运算符的优先级，否则返回false
     * @implNote 比较两个运算符的优先级
     */
    private static boolean priorityComparison(String operatorA, String operatorB) {
        if (operatorA.equals("+") || operatorB.equals("-"))
            return true;//此时operatorA的优先级一定比较低，所以代表了扫描到的当前运算符的优先级低于或等于运算符栈中的运算符
        else {
            return operatorB.equals("*") || operatorB.equals("/");//此时operatorA是×或÷，如果operatorB也是×或÷，那么说明此时operatorA的优先级满足小于或等于operatorB的优先级，可以返回true
        }
    }

    /**
     * InfixExpression类的setter方法，主要用途将传入容器的引用赋给内部的中缀表达式容器
     *
     * @param expressionArray 代表传入的内部存储中缀表达式各个符号的容器的引用
     */
    public void setExpressionArray(ArrayList<String> expressionArray) {
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
                            Double resultObj = operateTwoNumbers(numB, numA, operatorStack.pop());//将结果包装成对象，可以调用其中的函数再次将其转换为String类型
                            numberStack.push(resultObj.toString());
                        }
                        operatorStack.pop();//不要忘记弹出此时栈顶的左括号
                    }

                } else {
                    //当前扫描到的是运算符，则依次弹出运算符栈中优先级高于或等于当前运算符的所有运算符，每弹出一个运算符就弹出两个操作数做一次运算
                    //如果遇到左括号或运算符栈空时则停止，停止后将扫描到的当前运算符压入运算符栈
                    if (!operatorStack.empty())//先判断当前运算符栈是否为空，如果不为空则进入运算，为空则直接将当前运算符栈压栈，然后返回
                    {
                        if (operatorStack.peek().equals("(") || !priorityComparison(symbol, operatorStack.peek())) {
                            //遇到了左括号或者优先级不满足条件的情况，扫描到的运算符比当前栈顶的运算符的优先级高
                            if (operatorStack.peek().equals("(")) {
                                operatorStack.push(symbol);
                            } else {
                                //遇到了优先级不满足的运算符，直接将当前运算符压栈
                                operatorStack.push(symbol);
                            }
                            continue;//两种情况处理之后重新开始最外侧的for循环
                        }
                        double numA = Double.parseDouble(numberStack.pop());
                        double numB = Double.parseDouble(numberStack.pop());
                        Double resultObj = operateTwoNumbers(numB, numA, operatorStack.pop());
                        numberStack.push(resultObj.toString());
                        operatorStack.push(symbol);
                    } else {
                        operatorStack.push(symbol);
                    }
                }
            }
        }
        //for循环结束并不代表运算结束，此时已经把中缀表达式数组中的所有数和运算符以及界限符按照一定规则压入了两个栈中，现在只需要不断弹出运算符栈中的运算符和操作数栈中的数字做运算即可得出结果
        while (!operatorStack.empty()) {
            double numA = Double.parseDouble(numberStack.pop());
            double numB = Double.parseDouble(numberStack.pop());
            Double resultObj = operateTwoNumbers(numB, numA, operatorStack.pop());
            numberStack.push(resultObj.toString());
        }
        //最后运算结果一定会在操作数栈底，只需将其弹出即可得到结果
        return Double.parseDouble(numberStack.pop());
    }
}

