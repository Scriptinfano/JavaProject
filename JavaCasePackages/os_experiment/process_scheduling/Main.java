package os_experiment.process_scheduling;

import java.util.Arrays;
import java.util.PriorityQueue;

class AllProcessEndSignal extends Exception {

}

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
class Process implements Comparable<Process> {
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

/**
 * 该进程用来模拟进程调度中的CPU
 * 假定所有进程在同一时刻到达CPU
 * 在进程调度中采用非抢占的方式
 * 进程只有种状态：运行中、就绪中、尚未被运行、运行完成
 *
 * @author Mingxiang
 */
class CPU {
    private static final int timeSlice = 5;//定义时间片的大小
    private final PriorityQueue<Process> readyQueue = new PriorityQueue<>();//就绪队列，采用优先级队列实现，确保每次都是优先级最大的进程在队首
    private int timer;//CPU内的全局计时器，记录CPU总的运行时间

    public CPU() {
        initProcess();
    }

    /**
     * 加载定义好的进程
     */
    public void initProcess() {
        Process[] processes = new Process[5];

        processes[0] = Process.createInstance("P1", 9, 8);
        processes[0].setArriveTime(0);

        processes[1] = Process.createInstance("P2", 8, 21);
        processes[1].setArriveTime(0);

        processes[2] = Process.createInstance("P3", 7, 33);
        processes[2].setArriveTime(0);

        processes[3] = Process.createInstance("P4", 6, 13);
        processes[3].setArriveTime(0);

        processes[4] = Process.createInstance("P5", 5, 42);
        processes[4].setArriveTime(0);

        readyQueue.addAll(Arrays.asList(processes));
    }

    /**
     * 将队首进程取出并运行
     */
    public void run() throws AllProcessEndSignal {
        if (readyQueue.isEmpty()) {
            throw new AllProcessEndSignal();
        }

        Process process = readyQueue.poll();
        System.out.println(process.getName() + "进程被调度，以下是调度之前的信息");
        System.out.println(process);
        int serveTime = process.getServeTime();
        int elapsedTime = process.getElapsedTime();
        if (process.getElapsedTime() == 0) {
            //队首的进程第一次运行
            process.setStartTime(timer);
        }
        //队首的进程不是第一次运行，
        if (serveTime > elapsedTime) {
            //进程尚需服务
            int neededTime = serveTime - elapsedTime;//进程还需要服务的时间
            if (neededTime > timeSlice) {
                //还需要服务的时间大于一个时间片
                timer += timeSlice;//CPU运行一个时间片的时间
                process.updateElapsedTime(timeSlice);//进程已经被服务的时间增加
                process.reducePriority();//进程优先级降低，动态优先级随着每一次运行而降低
                readyQueue.add(process);//进程之后仍然需要服务，所以将其压回就绪队列
            } else {
                //在本时间片之内，进程就可以完成所有服务
                timer += neededTime;
                process.updateElapsedTime(neededTime);
                process.setEndTime(timer);
                readyQueue.remove(process);//进程运行结束，从就绪队列中移除进程
                if (neededTime < timeSlice) {
                    //在一个时间片之内就运行结束，可以选择直接让调度程序调度下一个队首进程
                    System.out.println(process.getName() + "进程在时间片内运行结束");
                }
            }
        } else {
            //进程已经运行结束
            readyQueue.remove(process);
            process.setEndTime(timer);
            System.out.println(process.getName() + "进程正常运行结束");
        }

        System.out.println(process.getName() + "进程调度完成，以下是运行之后的信息");
        System.out.println(process);
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        CPU cpu = new CPU();
        System.out.println("进程名   到达时间   开始时间   服务时间   已被服务时间   尚需要被服务时间   优先级   结束时间");
        while (true) {
            try {
                cpu.run();
            } catch (AllProcessEndSignal e) {
                System.out.println("所有进程运行结束");
                break;
            }
        }
    }
}
