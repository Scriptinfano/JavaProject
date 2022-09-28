package other.SuffixExpressionCalculation;

import java.util.Stack;

public class SuffixExpressionCalculator {
    private final Stack<String> numberStack = new Stack<>();//操作数栈
    private final Stack<String> operatorStack = new Stack<>();//运算符栈
    String[] expression;//代表中缀表达式

    /**
     * 后缀表达式计算器的构造器，传入一个存储中缀表达式的List容器
     *
     * @param theExpression 代表中缀表达式的容器
     */
    public SuffixExpressionCalculator(String[] theExpression) {
        expression = theExpression;
    }

    /**
     * 重置内部的中缀表达式
     *
     * @param theExpression 中缀表达式容器
     */
    public void resetExpression(String[] theExpression) {
        expression = theExpression;
    }

    /**
     * 整个运算过程的流程控制
     */
    public void run() {
        for (String symbol : expression) {
            //外层for循环从中缀表达式中扫描符号
            if (isNumber(symbol)) {
                //如果符号是数字则直接压入操作数栈
                numberStack.push(symbol);
            } else {
                //如果符号是运算符或者界限符

                if (isBracket(symbol)) {
                    //该符号是界限符的情况

                    //界限符是左括号则直接压入操作符栈
                    if (symbol.equals("(")) operatorStack.push(symbol);
                    else {
                        //扫描到的界限符是右括号

                        /*扫描到的界限符是右括号则执行下面的while循环描述的这样一个过程：
                         * 不断弹出运算符栈中的运算符，每弹出一个运算符就从操作数栈中弹出两个操作数进行运
                         * 算（注意后弹出的操作数作为运算符的前操作数）直到遇到左括号才终止上面的运算操作，
                         * 而且在终止操作之后一定要弹出此时运算符栈顶的左括号*/
                        while (!operatorStack.peek().equals("(")) {
                            double numA = Double.parseDouble(numberStack.pop());//从操作数栈中取出第一个操作数
                            double numB = Double.parseDouble(numberStack.pop());//从操作数栈中取出第二个操作数
                            double result = operateTwoNumber(numB, numA, operatorStack.peek());//运算两个操作数
                            operatorStack.pop();
                            numberStack.push(String.valueOf(result));
                        }
                        operatorStack.pop();//弹出此时栈顶的左括号
                    }

                } else {
                    /*该符号是运算符，依次弹出运算符栈中优先级高于或等于当前运算符的所有运算符，每弹出一个运算符就
                     * 另外从操作数栈中弹出两个操作数与弹出的运算符做运算，若遇到左括号或栈为空时终止，停止后将扫描
                     * 到的当前运算符压入运算符栈
                     * */
                    if (!operatorStack.isEmpty()) {
                        /*下面的while循环要满足三个条件才能执行：
                         * 1、运算符栈不为空
                         * 2、运算符栈定的元素不是左括号
                         * 3、运算符栈栈顶的运算符优先级要高于当前扫描的运算符的优先级*/
                        while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") && priorityComparison(symbol, operatorStack.peek())) {
                            String theOperator = operatorStack.pop();
                            double numA = Double.parseDouble(numberStack.pop());//从操作数栈中取出第一个操作数
                            double numB = Double.parseDouble(numberStack.pop());//从操作数栈中取出第二个操作数
                            double result = operateTwoNumber(numB, numA, theOperator);
                            numberStack.push(String.valueOf(result));
                        }
                        /*运行到此处有四种情况如下：
                         * 1、执行完while循环后运算符栈为空
                         * 2、执行while循环过程中栈顶出现了左括号
                         * 3、运算符栈顶的运算符的优先级低于当前扫描到的运算符的优先级
                         * 4、第一次执行while循环的过程中，运算符栈顶直接就是左括号
                         * */
                        if (!operatorStack.empty()) {
                            if (operatorStack.peek().equals("(")) {
                                //执行到此有两种情况：
                                //1、第一次执行while循环的过程中，运算符栈顶直接就是左括号
                                //2、执行while循环过程中栈顶出现了左括号
                                //注意此时不能弹出左括号，因为在遇到右括号之后进行一系列运算之后才能弹出左括号
                                operatorStack.push(symbol);
                            } else {
                                //遇到了优先级低于当前运算符的运算符
                                operatorStack.push(symbol);
                            }
                        } else {
                            //运算符栈为空的情况下直接将当前运算符压栈即可
                            operatorStack.push(symbol);
                        }

                    } else {
                        //运算符栈中目前没有任何运算符，直接将当前扫描到的运算符压入运算符栈
                        operatorStack.push(symbol);
                    }

                }
            }
        }
        //处理完所有扫描到的符号之后，需要继续检查运算符栈是否为空，进而继续执行运算
        while (!operatorStack.isEmpty()) {
            double numA = Double.parseDouble(numberStack.pop());//从操作数栈中取出第一个操作数
            double numB = Double.parseDouble(numberStack.pop());//从操作数栈中取出第二个操作数
            double result = operateTwoNumber(numB, numA, operatorStack.peek());//运算两个操作数
            operatorStack.pop();
            numberStack.push(String.valueOf(result));
        }
        //此时的运算结果就是操作数栈底部的元素
        System.out.println("整个中缀表达式的运算结果=" + numberStack.pop());

    }

    /**
     * 比较两个运算符的优先级
     *
     * @param operatorA 扫描到的当前运算符
     * @param operatorB 即将要弹出的运算符栈中的运算符
     * @return boolean 如果即将要弹出的运算符栈中的运算符(operatorB)的优先级大于扫描到的当前运算符(operatorA)优先级，则返回true否则返回false
     */
    private boolean priorityComparison(String operatorA, String operatorB) {
        if (operatorA.equals("+") || operatorA.equals("-")) {
            return true;
        } else
            return operatorB.equals("*") || operatorB.equals("/");
    }

    /**
     * 操作两个数字
     *
     * @param numA     运算符前的操作数
     * @param numB     运算符后的操作数
     * @param operator 操作符
     * @return double 返回运算结果
     */
    private double operateTwoNumber(double numA, double numB, String operator) {
        if (operator.equals("+")) {
            return numA + numB;
        } else if (operator.equals("-")) {
            return numA - numB;
        } else if (operator.equals("*")) {
            return numA * numB;
        } else if (operator.equals("/")) {
            return numA / numB;
        } else {
            throw new IllegalArgumentException("double operateTwoNumbers(const double &numA,const double &numB,const char &theOperator)中的第三个参数有误");
        }
    }

    /**
     * 判断符号是否是数字
     *
     * @param symbol 要判断的符号
     * @return boolean 如果符号代表数字则返回true否则返回false
     */
    private boolean isNumber(String symbol) {
        char character = symbol.charAt(0);
        return Character.isDigit(character);
    }

    /**
     * 判断传入的符号是否是左括号或者右括号
     *
     * @param symbol 传入的符号
     * @return boolean 如果是左括号或右括号返回true否则返回false
     */
    private boolean isBracket(String symbol) {
        return symbol.equals("(") || symbol.equals(")");
    }


}
