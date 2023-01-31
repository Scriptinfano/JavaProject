package JavaAlgorithm.packProblem;
//背包问题
/*以下列背包问题为例
 * 物品  重量  价格
 * 吉他  1    1500
 * 音箱  4    3000
 * 电脑  3    2000
 * 要求达到的目标为装入的背包的总价值最大，并且重量不能超出
 * 要求装入的物品不能重复
 *
 * 背包问题的种类
 * 1、01背包：每个物品最多放一个
 * 2、无限背包：每种物品有无限件可以使用
 * 注意：无限背包可以转化为01背包
 *
 * 解决此类算法要用到动态规划算法
 * 动态规划算法介绍：
 * 1、将大的问题分解为小问题进行解决，从而一步步获取最优解的处理算法
 * 2、与分治算法不一样的地方：适用于动态规划求解的问题，经分解得到的子问题往往不是互相独立的
 * （下一阶段的求解建立在上一子阶段的基础上，进行进一步的求解）
 * 3、动态规划可以通过填表的方式逐步推进，得到最优解
 *
 * 思路分析：对于给定的n个物品，设v[i]和w[i]分别为第i个物品的价值和重量，C为背包的容量，
 * 令v[i][j]表示:编号范围在[0,i]之间的物品任取放进容量为j的背包里能产生的最大价值。则我们有下面的结果：
 * 1、v[i][0]=v[0][j]=0 表示填入表中第一行和第一列是0
 * 2、当w[i]>j时，v[i][j]=v[i-1][j] 当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
 * 3、当j>=w[i]时：v[i][j]=max{v[i-1][j], v[i-1][j-w[i]]+v[i]]} 当准备加入的新增的商品的容量小于等于当前背包的容量，
 * 装入的物品取下面两个中较大的那个值所代表的物品
 * v[i-1][j] 上一个单元格的装入的最大值
 * v[i-1][j-w[i]] 装入i-1商品，到剩余空间j-w[i]
 *
 * */

abstract class Product {
    protected Double weight;
    protected Double price;

    public Product(double weight, double price) {
        this.weight = weight;
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getPrice() {
        return price;
    }
}

class Guitar extends Product {

    public Guitar(double weight, double price) {
        super(weight, price);
    }
}

class Audio extends Product {
    public Audio(double weight, double price) {
        super(weight, price);
    }
}

class Computer extends Product {
    public Computer(double weight, double price) {
        super(weight, price);
    }
}

public class PackProblemSolver {
}
