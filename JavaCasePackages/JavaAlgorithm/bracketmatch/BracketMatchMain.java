package bracketmatch;

import java.util.Stack;
import java.util.Vector;


/**
 * 括号匹配器类，可以检测传入的括号序列是否是相互匹配的
 *
 * @author Mingxiang
 * @date 2022/09/12
 */
class BracketMatcher {
    private Stack<Character> matchStack;
    private Vector<Character> bracketStack;

    private boolean isInitialized = false;

    public BracketMatcher(char[] brackets) {
        if (brackets.length == 0) throw new EmptyCharArrayException();
        matchStack = new Stack<>();
        bracketStack = new Stack<>();
        for (char bracket : brackets) {
            //检查每一个符号是否是括号
            if (!isBracket(bracket)) throw new InvalidCharArrayException();
            bracketStack.add(bracket);
        }
        isInitialized = true;
    }

    public BracketMatcher() {
        matchStack = new Stack<>();
        bracketStack = new Stack<>();
    }

    /**
     * 重置内部的括号匹配序列
     *
     * @param brackets char数组，代表括号的匹配序列
     */
    public void reset(char[] brackets) {
        if (brackets.length == 0) throw new EmptyCharArrayException();
        bracketStack = new Stack<>();
        for (char bracket : brackets) {
            if (!isBracket(bracket)) throw new InvalidCharArrayException();
            bracketStack.add(bracket);
        }
        isInitialized = true;
    }

    public boolean run() {
        if (!isInitialized) throw new UnInitializeException();
        for (Character character : bracketStack) {
            if (character == '{' || character == '[' || character == '(')
                matchStack.add(character);
            else {
                boolean match = bracketMatch(matchStack.pop(), character);
                if (!match) return false;
            }
        }
        return matchStack.empty();
    }

    /**
     * 检查传入的符号是不是代表括号
     *
     * @param bracket 要检查的字符
     * @return boolean 返回true则代表是，返回false则代表不是
     */
    private boolean isBracket(char bracket) {
        if (bracket == '{' || bracket == '}' || bracket == '[' || bracket == ']' || bracket == '(' || bracket == ')')
            return true;
        else return false;
    }

    private boolean bracketMatch(char matchElement, char bracketElement) {

        switch (matchElement) {
            case '{':
                return bracketElement == '}';
            case '(':
                return bracketElement == ')';
            case '[':
                return bracketElement == ']';
            default:
                throw new IllegalArgumentException("有非括号的字符出现在匹配序列中");
        }
    }
}

public class BracketMatchMain {
    public static void main(String[] args) {
        BracketMatcher matcher = new BracketMatcher();
        char[] bracketElement = {'{', '{', '(', '(', ')', ')', '[', ']', '}', '}'};
        matcher.reset(bracketElement);
        if (matcher.run()) System.out.println("匹配成功");
        else System.out.println("匹配失败");
    }
}
