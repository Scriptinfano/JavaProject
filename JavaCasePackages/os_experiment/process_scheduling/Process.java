package os_experiment.process_scheduling;

/**
 * 模拟进程的类<br/>
 * 进程调度算法：动态优先数+时间片轮转法<br/>
 * 基本原理和实现步骤：<br/>
 * 1、动态优先数调度<br/>
 * 1》每个进程被赋予一个动态优先数，可随时间推移而变化<br/>
 * 2》进程的初始动态优先数越小，优先级越高<br/>
 * 3》每个进程在运行之前根据优先数排队，就绪队列中的进程按照动态优先数从高到低排列<br/>
 * 2、时间片轮转调度：<br/>
 * 1》每个进程会分配一个时间片<br/>
 * 2》进程运行完一个时间片后，会被中断然后重新排队等待下一次执行<br/>
 *
 * @author Mingxiang
 */
public class Process implements Comparable<Process> {
    private String name;//进程名
    private int arriveTime;//进程的到达时间
    private int serveTime;//进程需要被CPU服务的时间
    private int priority;//进程的优先数，越大进程的优先级越低
    private int endTime;//进程的结束时间
    private int elapsedTime;//进程已经被CPU服务的时间
    private int startTime;//进程第一次被CPU服务的时间

    /**
     * @param theName      进程名称
     * @param thePriority  进程的初始优先级
     * @param theServeTime 进程需要被服务的总时间
     */
    private Process(String theName, int thePriority, int theServeTime) {
        name = theName;
        priority = thePriority;
        serveTime = theServeTime;
    }

    public static Process createInstance(String theName, int thePriority, int theServeTime) {
        if (thePriority <= 0)
            throw new IllegalArgumentException("优先级不得小于等于0");
        else return new Process(theName, thePriority, theServeTime);
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getServeTime() {
        return serveTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void updateElapsedTime(int elapsedTime) {
        this.elapsedTime += elapsedTime;
    }

    @Override
    public int compareTo(Process other) {
        return Integer.compare(this.priority, other.priority);
    }

    public void reducePriority() {
        priority++;
    }

    @Override
    public String toString() {
        return getName() + "        " + getArriveTime() + "        " + getStartTime() + "        " + getServeTime() + "        " + getElapsedTime() + "        " + (getServeTime() - getElapsedTime()) + "        " + getPriority() + "        " + (getEndTime() != 0 ? getEndTime() : "此时尚未结束");
    }
}
