package thread;

public class ThreadMain implements Runnable {
    public static final int PERSECONDS_THREAD=1000;//每秒有1000毫秒

    /**
     * 声明静态主类对象*/
    public static ThreadMain mainObj;

    /**
     * 声明静态线程对象*/
    public static Thread mainObjThread;

    static {
        mainObj = new ThreadMain();//在静态代码块中初始化主类对象，静态代码块无需实例化即可在类加载时执行
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("mainThread");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);//设为最大优先级

        runMainObjThread();//启动主对象线程
        try {
            runMainThread();//完成main线程中的工作
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在main中输出");

    }


    /**
     * 运行主类对象创建的线程要干的事情*/
    public static void runMainObjThread() {
        mainObjThread = new Thread(mainObj);
        mainObjThread.setName("mainObjThread");
        mainObjThread.setPriority(Thread.NORM_PRIORITY);//将优先级设为5
        mainObjThread.start();
    }

    /**
     * 运行main()方法中的线程要干的事情*/
    public static void runMainThread() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
           if (i % 3 == 0)
           {
               System.out.println(Thread.currentThread().getName() + ":" + i);
                Thread.sleep(3*PERSECONDS_THREAD);
           }
            if (i == 20)
            {
                mainObjThread.join();//强行让mainObjThread线程接管CPU运行权，且该线程会一直占有运行权直到线程死亡其他线程才有权力接管运行权
            }
        }
        System.out.println("在主类的静态方法中输出");

    }

    /**
     * 运行匿名线程
     */
    public static void runAnonymousThread() {
        //创建匿名的两个线程对象

        new Thread(new AnonymousThread()).start();//调用匿名的实现了Runnable接口的Anonymous类的线程
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }

            }
        }.start();//创建Thread的匿名子类并重写其中的run方法来实现调用线程
    }

    /**
     * 主对象线程的run方法
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) System.out.println(Thread.currentThread().getName() + ":" + i);
/*
            if (i % 3 == 0)
                Thread.yield();//当前线程被强行放弃控制权
*/
        }
    }
}
class AnonymousThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 4 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

