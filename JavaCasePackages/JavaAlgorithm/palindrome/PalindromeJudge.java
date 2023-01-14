package JavaAlgorithm.palindrome;

import java.util.Stack;

/**
 * 判断字符串是否是回文
 *
 * @author Mingxiang
 * @date 2022/10/04
 */
public class PalindromeJudge {
    final String str;//待扫描的序列
    final Stack<Character> characters;

    PalindromeJudge(String theStr) {
        this.str = theStr;
        characters = new Stack<>();
    }

    public static void main(String[] args) {
        String str = "ABCDCBA";
        PalindromeJudge judge = new PalindromeJudge(str);
        System.out.print(str + (judge.run() ? "是回文字符串" : "不是回文字符串"));
    }

    public boolean run() {
        int i;
        for (i = 0; i <= str.length() / 2; i++) {
            Character c = str.charAt(i);
            characters.push(c);
        }
        characters.pop();
        for (int j = i; j < str.length(); j++) {
            if (!characters.pop().equals(str.charAt(j))) return false;
        }
        return true;
    }
}
