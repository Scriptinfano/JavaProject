package LeetCode;

/**
 * 给你一个整数数组 prices ，prices[i] 表示某支股票第i天的价格。在每一天，你可以决定是
 * 否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天
 * 出售。返回你能获得的最大利润
 * 作者：LeetCode
 * 链接：<a href="https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2zsx1/">...</a>
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MaxProfit {
    /**
     * 动态规划优化之后的代码，
     * 当天的利润只和前一天有关，没必要使用一个二维数组，只需要使用两个变量，一个记录当天交易完之后手里持有股票的最大利润，一个记录当天交易完之后手里没有股票的最大利润
     *
     * @param prices 价格
     * @return int
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int length = prices.length;
        int hold = -prices[0];
        int unHold = 0;
        for (int i = 1; i < length; i++) {
            unHold = Math.max(unHold, hold + prices[i]);//今天手里不准备持有股票：之前没有持有今天也不买、之前持有但今天卖了
            hold = Math.max(hold, unHold - prices[i]);//今天手里准备持有股票：之前就有今天也不买、之前没有今天要买入
        }
        return unHold;
    }

    /**
     * 贪心算法解决
     * 找到股票上涨的最大值和股票开始上涨的最小值，计算他们的差就是这段时间内股票的最大利润。如果股票下跌就不用计算，最终只需要把所有股票上涨的时间段内的利润累加就是我们所要求的结果
     *
     * @param prices 价格
     * @return int
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int index = 0, total = 0, length = prices.length;
        while (index < length) {
            while (index < length - 1 && prices[index] >= prices[index + 1])
                index++;//若股票下跌则一直找到开始上升的地方
            int min = prices[index];//开始上升时的最小值
            while (index < length - 1 && prices[index] <= prices[index + 1])
                index++;//股票在上升阶段要找到上升阶段最后的最大值
            total += prices[index++] - min;//累加上升阶段的最大值和开始上升时的最小值就是该上升阶段所造成的利润
        }
        return total;
    }

    /**
     * 采用相邻两天高抛低吸的思想，只要股票价格上涨就累加上涨部分的值，下降就不管，这样将所有上升区间加起来就能获得最大利润
     *
     * @param prices 价格
     * @return int
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[i + 1])
                sum += prices[i + 1] - prices[i];
        }
        return sum;
    }
}
