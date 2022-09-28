/*
 * 解决打印机任务管理问题，设计一个不带头结点的链队列作为打印任务管理器的数据结构，当新来一个打印任务，则将它加入队尾；当打印机空闲时
 * ，从队头取出新的打印任务进行打印*/
package other.printerManagement;

import java.util.LinkedList;

public class PrintTaskManager {
    private final LinkedList<PrintTask> printQueue = new LinkedList<PrintTask>();

    PrintTaskManager() {
    }

    /**
     * 将
     */
    public void addTask(int theId, String theText) {
        PrintTask task = new PrintTask(theId, theText);
        printQueue.add(task);
    }

    /**
     * 取出队列中的第一个打印任务进行打印，并将该打印任务从队头删除
     */
    public void printTask() {
        if (printQueue.isEmpty()) {
            System.out.println("当前打印队列没有任何待打印的内容");
            return;
        }
        PrintTask task = printQueue.poll();
        System.out.println(task.getPrintId() + " " + task.getText());
    }

    /**
     * 打印所有队列中等待打印的任务
     */
    public void printAllTask() {
        while (!printQueue.isEmpty()) {
            printTask();
        }
    }

    /**
     * 清空队列中所有打印任务
     */
    public void clearPrintTask() {
        printQueue.clear();
    }
}
