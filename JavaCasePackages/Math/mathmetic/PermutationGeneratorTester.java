package mathmetic;

import java.util.Scanner;

class PermutationGeneratorTester {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        test2();

    }

    public static void test() {
        System.out.print("输入一元排列的尾数：");
        int dimension = scanner.nextInt();
        PermutationsGenerator generator = new PermutationsGenerator(dimension);
        generator.run();
        int[][] result = generator.getPermutations();
        System.out.println("一共生成" + MathUtil.factorial(dimension) + "种排列");
        generator.showResult();
        System.out.println("递归调用次数=" + generator.getCounter());

    }

    public static void test2() {
        PermutationGeneratorPlus generator = new PermutationGeneratorPlus();
        generator.setDimension(5);
        generator.setSelectSize(3);
        generator.run(PermutationGeneratorPlus.mode.SELECT);
        System.out.println(generator.toString());
        System.out.println("一共生成了"+generator.getCounter()+"种排列");

    }
}
