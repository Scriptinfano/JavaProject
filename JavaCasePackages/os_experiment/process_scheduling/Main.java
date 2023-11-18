package os_experiment.process_scheduling;

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
