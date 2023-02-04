package methmatic;

public class PascalTriangle {

    final int[][] pascalTriangle;

    /**
     * 帕斯卡三角形构造器
     *
     * @param rows 指定输出的行数
     */
    public PascalTriangle(int rows) {
        pascalTriangle = new int[rows][];
        generate();
    }

    /**
     * 输出rows行杨辉三角阵,rows由该类构造函数指定
     */
    private void generate() {
        //将杨辉三角每行的第一个元素和最后一个元素置为0，便于进一步的计算
        for (int i = 0; i < pascalTriangle.length; i++) {
            pascalTriangle[i] = new int[i + 1];
            int lastIndex = pascalTriangle[i].length - 1;
            pascalTriangle[i][0] = 1;
            pascalTriangle[i][lastIndex] = 1;
        }
        for (int i = 2; i < pascalTriangle.length; i++) {
            for (int j = 1; j < i; j++) {
                pascalTriangle[i][j] = pascalTriangle[i - 1][j - 1] + pascalTriangle[i - 1][j];
            }
        }
    }

    /**
     * 显示内部生成的帕斯卡三角
     */
    public void show() {
        for (int[] ints : pascalTriangle) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }
}
