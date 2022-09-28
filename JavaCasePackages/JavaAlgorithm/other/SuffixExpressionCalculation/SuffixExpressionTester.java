package other.SuffixExpressionCalculation;

public class SuffixExpressionTester {
    public static void main(String[] args) {
        String[] expressions = {"2", "+", "3", "-", "4", "*", "3", "/", "4", "+", "7"};
        String[] expressions2 = {"85", "+", "14", "*", "(", "14", "/", "208", "+", "26", ")", "*", "21", "+", "(", "327", "-", "23", ")", "/", "19"};
        SuffixExpressionCalculator calculator = new SuffixExpressionCalculator(expressions);
        calculator.run();
    }
}
