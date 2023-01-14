package JavaAlgorithm;

/**
 * 寻找最长公共前缀
 *
 * @author Mingxiang
 */
public class LongestCommonPrefixSolver {
    public String run(String[] stringArr) {
        if (stringArr.length == 0)
            return "";
        String answer = stringArr[0];
        for (int i = 1; i < stringArr.length; i++) {
            int j = 0;
            for (; j < answer.length() && j < stringArr[i].length(); j++) {
                if (answer.charAt(j) != stringArr[i].charAt(j))
                    break;
            }
            answer = answer.substring(0, j);
            if (answer.equals(""))
                return answer;
        }
        return answer;
    }
}

class Tester {
    public static void main(String[] args) {
        LongestCommonPrefixSolver solver = new LongestCommonPrefixSolver();
        String[] strings = new String[]{"apple", "applest", "apdef"};
        String result = solver.run(strings);
        System.out.println(result);
    }
}