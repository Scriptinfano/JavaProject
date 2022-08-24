package javaThread;

public class ThreadMain implements Runnable {
    public static void main(String[] args) {
        Thread.currentThread().setName("mainThread");
        Thread.currentThread().setPriority(10);
        ThreadMain mainObj = new ThreadMain();
        Thread mainThread = new Thread(mainObj);
        mainThread.setName("mainObjThread");
        mainThread.setPriority(1);

        mainThread.start();

        for (int j = 0; j < 100; j++) {
            System.out.println(Thread.currentThread().getName() + ":" + j);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
