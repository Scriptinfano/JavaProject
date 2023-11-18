package os_experiment.process_scheduling;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 该进程用来模拟进程调度中的CPU
 * 假定所有进程在同一时刻到达CPU
 * 在进程调度中采用非抢占的方式
 * 进程只有种状态：运行中、就绪中、尚未被运行、运行完成
 *
 * @author Mingxiang
 */
public class CPU {
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
