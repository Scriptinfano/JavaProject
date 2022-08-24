package javaThread;

class Window implements Runnable {
    private int ticket = 100;
   static final Object obj = new Object();//保证锁的唯一性

    @Override
    public void run() {
        while (true) {
            //使用同步代码块的方式解决线程安全问题
            synchronized (obj)//此处也可以用当前对象作为锁，因为实现接口方式创建线程时对象只有一个
            {
                if (ticket > 0) {
                    //在加上sleep函数之后，CPU在此时切换线程的概率增大了
/*
                    try {
                        Thread.sleep(WindowSellTickets2.PER_SECONDS / 2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
*/
                    System.out.println(Thread.currentThread().getName() + "卖票，票号=" + ticket);
                    ticket--;

                } else break;

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

public class WindowSellTickets2 {
    public static final int PER_SECONDS = 1000;

    public static void main(String[] args) {
        Window window = new Window();
        //三个线程操作同一个对象，此时ticket也就不需要声明为static的了
        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);

        thread1.setName("窗口1");
        thread2.setName("窗口2");
        thread3.setName("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
