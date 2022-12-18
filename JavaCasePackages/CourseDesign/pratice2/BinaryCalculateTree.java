package pratice2;

import myScannerAndPrinter.Iotransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * 用于计算表达式的二叉树
 *
 * @author Mingxiang
 */
public class BinaryCalculateTree {
    /**
     * 在将表达式转换为表达式树时所要用到的存储数字的栈
     */
    private final Stack<BinaryTreeNode<String>> numberStack = new Stack<>();
    /**
     * 在将表达式转换为表达式树时所要用到的存储运算符或者括号的栈
     */
    private final Stack<BinaryTreeNode<String>> operatorStack = new Stack<>();
    /**
     * 树的根节点
     */
    private BinaryTreeNode<String> root;
    /**
     * 存储中序序列的容器
     */
    private ArrayList<String> inOrderContainer = new ArrayList<>();

    /**
     * 空参构造器
     */
    public BinaryCalculateTree() {
    }

    /**
     * 检测传入的中缀表达式是否均由小数或整数或运算符组成
     *
     * @param expressionArray 代表待检测的中缀表达式
     * @return boolean 如果符合规则则返回true否则返回false
     */
    private static boolean verifyExpression(String[] expressionArray) {
        //运算符之前可以跟右括号和数字，运算符之后可以跟左括号和数字，运算符不能放在首部和尾部，左括号和右括号必须匹配，数字之前和数字之后必须紧跟运算符
        if(!isNumber(expressionArray[0]))
        for (int i=0;i<expressionArray.length;i++) {
            String s=expressionArray[i];
            if (isNumber(s)){

            }else if(isOperator(s)){

            }else if(isBracket(s)){

            }else {
                return false;//遇到非法符号说明此表达式一定不符合要求
            }

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
     * 计算表达式树的递归函数
     *
     * @param theNode 节点
     * @return double
     */
    private static double calculateExpressionTree(BinaryTreeNode<String> theNode) {
        double leftValue = 0, rightValue = 0;
        if (theNode.getLeftChild() == null && theNode.getRightChild() == null)
            return Double.parseDouble(theNode.getElement());
        else {
            leftValue = calculateExpressionTree(theNode.getLeftChild());
            rightValue = calculateExpressionTree(theNode.getRightChild());
            return operateTwoValue(leftValue, theNode.getElement(), rightValue);
        }
    }

    /**
     * 运算两个值，返回theLeftValue theOperator theRightValue所代表的表达式的运算结果
     *
     * @param theLeftValue  左值
     * @param theOperator   操作符
     * @param theRightValue 右值
     * @return double
     */
    private static double operateTwoValue(double theLeftValue, String theOperator, double theRightValue) {
        if (theOperator.equals("+")) {
            return theLeftValue + theRightValue;
        } else if (theOperator.equals("-")) {
            return theLeftValue - theRightValue;
        } else if (theOperator.equals("*")) {
            return theLeftValue * theRightValue;
        } else if (theOperator.equals("/")) {
            return theLeftValue / theRightValue;
        } else {
            throw new RuntimeException("未知的运算符");
        }
    }

    /**
     * 比较两个运算符的优先级
     *
     * @param operatorA 扫描到的当前运算符
     * @param operatorB 从操作符栈中弹出的运算符
     * @return boolean 返回为true时，第一个参数所代表的运算符优先级小于第二个参数所代表的运算符的优先级，也就是操作符栈顶
     * 的运算符的优先级大于等于当前扫描到的运算符优先级，否则返回false
     */
    private static boolean priorityComparison(String operatorA, String operatorB) {
        if (operatorA.equals("+") || operatorA.equals("-"))
            return true;//此时operatorA的优先级一定比较低，即运算符栈顶的操作符的优先级一定大于或等于当前扫描到的运算符的优先级，即符合要求
        else {
            return operatorB.equals("*") || operatorB.equals("/");//此时operatorA是×或÷，如果operatorB也是×或÷，那么说明此时栈顶的操作符的优先级一定等于当前扫描到的运算符的优先级，也满足要求
        }
    }

    /**
     * 判断树是否为空
     *
     * @return boolean 为true则表示树是空树，否则不为空树
     */
    public boolean treeIsEmpty() {
        return root == null;
    }

    /**
     * 检查表达式是否已设定
     *
     * @return boolean
     */
    public boolean expressionIsEmpty() {
        return inOrderContainer.isEmpty();
    }

    /**
     * 设置要计算的表达式
     *
     * @param theExpression 传入的要计算的表达式
     * @return boolean 若为true，表示设置成功，否则设置失败
     */
    public boolean setExpression(String[] theExpression) {
        if (!inOrderContainer.isEmpty()) {
            while (true) {
                Iotransformer.printer.print("表达式已设置，是否重设表达式？（Y/N）:");
                char selection = Iotransformer.scanner.nextChar();
                if (selection == 'N')
                    return false;
                else if (selection == 'Y') {
                    break;
                } else {
                    System.out.println("你的输入不符合要求，请重新输入");//换行提示
                }
            }
        }
        if (verifyExpression(theExpression)) {
            //表达式符合要求，执行转换
            inOrderContainer = new ArrayList<String>(Arrays.asList(theExpression));//将String[]转换为ArrayList<String>
            return true;
        } else return false;
    }

    /**
     * 在内部生成表达式树并计算表达式的值，若未设定表达式则会抛出异常
     *
     * @return double
     * @throws RuntimeException 发生运行时错误，未设置表达式，无法计算，
     */
    public double calculate() throws RuntimeException {
        //先构造一颗表达式树，然后根据表达式树计算结果
        if (inOrderContainer.isEmpty())
            throw new RuntimeException("发生运行时错误，未设置表达式，无法计算");
        generateExpressionTree();//先利用表达式生成表达式树
        return calculateExpressionTree(root);
    }

    /**
     * 生成表达式树，该树的根的引用会赋值给该对象的root变量
     */
    private void generateExpressionTree() {
        for (String symbol : inOrderContainer) {
            if (isNumber(symbol)) {
                //该符号代表的是数字时，压入数字节点栈
                numberStack.add(new BinaryTreeNode<>(symbol));
            } else {
                //该符号是运算符节点或是界限节点
                if (isBracket(symbol)) {
                    //该符号是界限符的情况
                    if (symbol.equals("(")) {
                        //界限符是左括号的情况
                        operatorStack.add(new BinaryTreeNode<>(symbol));
                    } else {
                        //界限符是右括号的情况
                        while (!Objects.equals(operatorStack.peek().getElement(), "(")) {
                            operateTwoNode();
                        }
                        operatorStack.pop();//弹出此时运算符栈顶的左括号
                    }
                } else {
                    //如果当前扫描到的是运算符
                    if (!operatorStack.isEmpty()) {
                        //运算符栈不为空，可能有三种情况：1、运算符栈顶是左括号，此时直接压栈 2、运算符栈顶的运算符的优先级小于当前运算符的优先级，此时直接压栈 3、运算符栈顶的运算符优先级大于等于当前运算符的优先级，不断执行构建过程，每次执行前都要判断栈顶运算符优先级和当前操作符优先级，只有满足要求时才能继续执行
                        if (operatorStack.peek().getElement().equals("(") || !priorityComparison(symbol, operatorStack.peek().getElement())) {
                            //第一种情况和第二种情况执行的操作相同
                            operatorStack.push(new BinaryTreeNode<>(symbol));
                        } else {
                            while (!operatorStack.isEmpty() && priorityComparison(symbol, operatorStack.peek().getElement())) {
                                operateTwoNode();
                            }
                            operatorStack.push(new BinaryTreeNode<>(symbol));
                        }
                    } else {
                        //如果此时运算符栈中没有任何运算符，直接将当前扫描到的运算符压入运算符栈
                        operatorStack.push(new BinaryTreeNode<>(symbol));
                    }
                }
            }
        }
        while (!operatorStack.isEmpty()) {
            operateTwoNode();
        }
        root = numberStack.peek();//此时栈顶节点就是树的根节点
    }

    /**
     * 从操作树栈中弹出两个节点，再从操作符栈中弹出一个节点出来，令操作树栈中弹出的两个节点成为操作符栈中节点左右子树的过程
     */
    private void operateTwoNode() {
        BinaryTreeNode<String> numberNodeA = numberStack.pop();
        BinaryTreeNode<String> numberNodeB = numberStack.pop();
        BinaryTreeNode<String> operator = operatorStack.pop();
        operator.setLeftChild(numberNodeB);
        operator.setRightChild(numberNodeA);
        numberStack.push(operator);
    }

    /**
     * 取得内部存储的表达式
     *
     * @return {@link String} 若内部存储表达式序列的容器不为空，则返回表达式字符串，否则返回空引用
     */
    public String getExpression() {
        if (inOrderContainer.isEmpty())
            return null;
        StringBuilder builder = new StringBuilder();
        for (String str : inOrderContainer) {
            builder.append(str);
        }
        return builder.toString();
    }
}

