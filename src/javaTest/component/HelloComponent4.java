package javaTest.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

class HelloComponent4 extends JComponent implements MouseMotionListener, ActionListener, Runnable {

    /*详解Runnable接口
     * 为了创建一个线程，HelloComponent4 对象将其自身（this）传递给 Thread 构造函数。这意味着，HelloComponent4 必须实现 Runnable 接口，可以通过实现 run()方法做到这点
     * */

    String theMessage;
    int messageX = 125;
    int messageY = 95;
    JButton theButton;//按钮组件

    int colorIndex;//相当于Color对象数组的索引，用于指定当前颜色的位置
    static Color[] someColors = {Color.black, Color.red, Color.green, Color.blue, Color.magenta};

    boolean blinkSkate;//与C++的bool不同，这是真正的真假变量不会和int互相转换

    public HelloComponent4(String message) {
        this.theMessage = message;
        this.theButton = new JButton("改变颜色");
        setLayout(new FlowLayout());
        add(theButton);
        theButton.addActionListener(this);
        addMouseMotionListener(this);
        /*java.lang.Thread 类的一个实例即对应一个线程,它包含了启动、控制和中断线程执行的一些方法。*/

        /*如果使用下面的构造函数来创建一个 Thread 对象，再使用此对象调用其 start()方法，该线程对象就会执行当初构造自己的构造函数的参数对象的 run()方法*/
        Thread thread = new Thread(this);//创建线程，并指定线程调用哪个对象的run方法，注意传入线程构造函数的对象必须实现Runnable接口

        /*先调用 Thread的 start()方法来开始执行。一旦线程开始，则会继续运行，直到发生以下情况为止，如完成了它的工作，
        我们将其中断，或者终止了应用。Thread对象总是希望执行一个名为run()的方法来完成线程的操作，其可以置于我们希望的
        任何类中*/
        thread.start();//启动线程，新线程将调用HelloComponent4的run方法
    }

    public void paintComponent(Graphics graphics) {
        /*paintComponent()函数只有在程序需要重新绘制组件的时候由程序自动调用，例如调用repaint()函数时
         * 此函数在继承JFrame时如果由有绘制组件的需求必须重写此方法*/

        /*Java绘图类中的Graphics类
         * Graphics类是Java提供的一个用于绘图和显示格式化文字的一个工具。
         * Graphics类在Java.awt包中被声明。awt的名称就是抽象窗口工具包，是提供窗口及其组件的类库。写字和画图是用Graphics的drawXXX方法实现的。如： drawString(String),drawLine(.)等。
         * 画图用的坐标系是原点在左上角，纵轴向下以象素为单位的坐标系
         */

        /*当 blinkState为 true 时，setColor()调用会以背景颜色绘制文本，以使文本消失*/
        graphics.setColor(blinkSkate ? getBackground() : currentColor());
        graphics.drawString(theMessage, messageX, messageY);
    }

    public void mouseDragged(MouseEvent event) {
        messageX = event.getX();
        messageY = event.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent event) {
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == theButton)
            changeColor();
    }

    /*synchronized 修饰符将通知 Java 要在其运行该方法前加锁（lock）。在任何给定时刻，对象中只能有一个方法可以得到锁，
    这说明该对象中一次只能有一个同步方法可以运行。这就允许方法修改数据，并在一个并发运行的方法能够访问数据之前，都使数据
    处于一种一致状态。方法执行完毕后，将释放对类所加的锁*/
    synchronized private void changeColor() {
        if (++colorIndex == someColors.length) {
            colorIndex = 0;
        }
        setForeground(currentColor());
        repaint();
    }

    synchronized private Color currentColor() {
        return someColors[colorIndex];
    }

    /*Thread 类本身有一个名为 run()的方法。要在一个单独的线程中执行一些 Java 代码，一种方法就是派生 Thread的子类，
    并覆盖其 run()方法来完成我们的工作。调用子类对象的 start()方法，将导致其 run()方法在一个单独的线程中执行*/
    public void run() {
        try {
            while (true)//线程将一直运行，直到应用退出而终止
            {
                //blinkSkate用以表示是否将闪烁打开或关闭
                blinkSkate = !blinkSkate;
                repaint();
                /*sleep()是 Thread 类的一个静态方法，参数以毫秒为单位。可以在任何地方调用此方法，其效果是令当前正在运行的线程睡眠指定的毫秒数
                 * 此处效果是每秒钟闪烁3次，因为300毫秒大约是1秒的三分之一*/
                Thread.sleep(300);
            }
        } catch (InterruptedException exception) {
            /*在上面的try中并未出现throw语句，为什么会有异常：
             * Thread 的 sleep() 方法可能会抛出一个InterruptedException 异常，表明它被另一个线程所中断*/
            exception.printStackTrace();
        }
    }
}
