/**
 * 例子：创建三个窗口卖票，总票数100张，存在线程安全问题，待解决
 */

package javaThread;

class SellingWindow extends Thread {
    private static int tickets = 100;//票数
    private static final Object lock = new Object();//同步锁


    /*
    //利用同步代码块解决线程安全问题
        @Override
        public void run() {

                while (true) {
                    //SellingWindow.class是JVM在加载SellingWindow类时生成的唯一一个Class类对象
                    //此时注意synchronized代码块不能把外面的while循环也包裹起来，这将导致一个线程一直在执行循环将票卖完了导致其他线程没有机会操作了
                    synchronized (SellingWindow.class)//注意不能用当前对象this充当锁，因为锁必须是唯一的，而继承方式实现线程时对象不唯一
                    {
                        if (tickets > 0) {

                            System.out.println(getName() + "卖票：票号=" + tickets);
                            tickets--;
                        } else break;

                    }
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }


        }
    */

    //利用同步方法解决线程安全问题
    @Override
    public void run() {

        while (true) {
            if (tickets > 0) {
                System.out.println(getName() + "卖票：票号=" + tickets);
                tickets--;
            } else break;
        }
        try {
            sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}

public class WindowSellTickets {
    public static void main(String[] args) {
        //创建三个窗口
        SellingWindow window1 = new SellingWindow();
        SellingWindow window2 = new SellingWindow();
        SellingWindow window3 = new SellingWindow();
        //给三个窗口线程起名字
        window1.setName("窗口1");
        window2.setName("窗口2");
        window3.setName("窗口3");
        //三个窗口分别开始卖票
        window1.start();
        window2.start();
        window3.start();
    }
}
