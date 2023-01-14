package analyzer;

public class AlgorithmAnalyzer {
    /**
     * 输入两个以纳秒为单位的long型值，返回之间的间隔时间，以毫秒为单位
     *
     * @param begin 开始
     * @param end   结束
     * @return {@link double} 返回时间间隔，时间单位是毫秒
     */
    public static double getAlgorithmTime(long begin, long end) {
        return (end - begin) / 1000000d;
    }

    public static long getCurrentTime() {
        return System.nanoTime();
    }
}
