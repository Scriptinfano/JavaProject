package JavaAlgorithm.dynamicProgame;

/*
给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。计算并返
回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/coin-change
*/
public class CoinProblem {
    private int[] coins;
    private int amount;


    public CoinProblem(int[] coins, int amount) {
        this.coins = coins;
        this.amount = amount;
    }

    public int solve() {
        int[] f = new int[amount + 1];//动态规划先开一个状态转移数组，注意大小
        f[0] = 0;//设定动态规划种的初始条件
        for (int i = 1; i <= amount; i++) {
            //i代表正在拼凑的面额，相当于每一个状态
            f[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i >= coin && f[i - coin] != Integer.MAX_VALUE)//如果不满足这个条件，说明当前正在拼凑的面额用已有的硬币是拼凑不出来的
                    f[i] = Math.min(f[i - coin] + 1, f[i]);
            }
        }
        if (f[amount] == Integer.MAX_VALUE)
            return -1;
        return f[amount];
    }

    public void setCoins(int[] coins) {
        this.coins = coins;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}

class TestCoin {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        CoinProblem solver = new CoinProblem(coins, amount);
        System.out.println(solver.solve());
    }
}
