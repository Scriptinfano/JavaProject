
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.awt.*;//不会自动导入下面的event包，虽然层次命名机制有此暗示
import java.awt.event.*;//提供事件对象


class HelloComponent2 extends JComponent implements MouseMotionListener {
    String theMessage;
    int messageX = 125;//保存可移动消息的当前坐标的横坐标
    int messageY = 95;//纵坐标

    public HelloComponent2(String message) {
        this.theMessage = message;
        addMouseMotionListener(this);//将自己注册成为鼠标事件的监听者，表示我希望接收自己的鼠标移动事件
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawString(theMessage, messageX, messageY);
    }

    /*mouseDragged()函数接受一个事件，当用户用鼠标做某个动作时，java的GUI组件会生成一个mouseEvent事件对象
     * 用户拖动鼠标不放时，会在监听者上自动调用mouseDragged()方法来接受所生成的事件*/
    public void mouseDragged(MouseEvent event) {
        messageX = event.getX();//返回鼠标当前X坐标
        messageY = event.getY();//同上

        /*要求组件得到重画将导致 Java 窗口系统安排在下一个可能的时间调用 paintComponent()方法，Java 会提
        供必要的 Graphics 对象*/
        repaint();
    }

    //只要将鼠标移到某个区域上而没有按鼠标键时即调用mouseMoved()方法
    public void mouseMoved(MouseEvent event) {

    }
}

class HelloComponent3 extends JComponent implements MouseMotionListener, ActionListener {
    String theMessage;
    int messageX = 125;
    int messageY = 95;
    JButton theButton;//按钮组件

    int colorIndex;//相当于Color对象数组的索引，用于指定当前颜色的位置

    /*Color对象数组，表示按下按钮时循环变化的颜色，Color其实并非对象，而是类。以下的Color.red等都是类中的静态变量
     * 不必创建 Color类的一个实例Color 类的实例表示一个可见的颜色。为方便起见，Color 类包含有一些静态的预定义对象。
     * GREEN 变量和 Color 的其他静态成员不能被修改（在其初始化之后），因此它们实际上是常量*/
    /*有关java数组
     * 在索引一个数组时，所得到的值是一个对象引用*/
    static Color[] someColors = {Color.black, Color.red, Color.green, Color.blue, Color.magenta};

    public HelloComponent3(String message) {
        theMessage = message;
        theButton = new JButton("change color");//重载的按钮构造函数，这个构造函数在构建按钮对象的同时将指定的文本放在按钮上

        /*setLayout()：通知HelloComponent3 应当如何对增加到其中的其他组件加以排列以便显示，在此例
        中，我们使用了一种叫做 FlowLayout 的布局，将按钮置于顶部中央位置*/
        setLayout(new FlowLayout());

        /*add():它将JButton 追加到 HelloJava3 容器所管理的组件列表的末尾。在此之后，HelloJava3
        就要对 JButton 负责，它将使此按钮得到显示，而且要确定此按钮应当置于其窗口的哪个位置*/
        add(theButton);

        /*将其组件theButton自身注册为按钮动作事件的一个监听者*/
        theButton.addActionListener(this);

        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics graphics) {
        graphics.drawString(theMessage, messageX, messageY);
    }

    public void mouseDragged(MouseEvent event) {
        messageX = event.getX();
        messageY = event.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent event) {
    }

    /*synchronized 关键字：指出了这两个方法绝对不能同时运行。它们必须一前一后地运行*/

    synchronized private Color currentColor() {
        return someColors[colorIndex];
    }

    synchronized private void changeColor() {
        if (++colorIndex == someColors.length)//someColors.length代表数组元素的个数，当索引达到最大时将索引归零，目的是循环变化颜色
            colorIndex = 0;
        setForeground(currentColor());//currentColor()得到当前索引所指向的颜色，setForeground()方法修改了组件中用于绘制文本的颜色。
        repaint();//repaint()，从而导致组件用新颜色来重新绘制可拖动的消息。
    }

    /*ActionListener要求实现actionPerformed()，只要出现一个ActionEvent事件即会调用此方法并接受传入的事件对象
     * */
    public void actionPerformed(ActionEvent event) {
        /* Java 中，== 是对同一性（identity）进行比较，而不是比较相等性（equality）；如果事件源和 theButton 是
        同一个对象则为 true。明确相等性和同一性之间的差别非常重要。如果两个 String 对象有相同的字符，而且字符序列也
        相同，那么可以认为这两个 String 对象相等。不过，它们可能并不是同一个对象。*/
        if (event.getSource() == theButton)//判断该事件是哪个组件发出的
        {
            changeColor();
        }
    }

}

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

class Test {
    public void testSocket() {
        try {
            ServerSocket server = new ServerSocket(25);
            Socket newSocket = server.accept();//服务器监听请求，如果监听到请求则返回一个socket对象以便完成数据传输
            BufferedReader newBufferReader = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));

            while (true) {
                String newLine = newBufferReader.readLine();
                if (newLine.equals("exit")) {
                    break;
                }
                System.out.println("received from client:" + newLine);
            }

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void testIO() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String str1 = scanner.next();
                System.out.println("输入的字符串：" + str1);
            } else break;

        }
    }

    public void testHelloComponent2() {
        JFrame frame = new JFrame("hello java2");
        frame.add(new HelloComponent2("hello java!"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//通知框架在按下关闭按钮时退出应用
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void testHelloComponent3() {
        JFrame frame = new JFrame("hello java2");//窗口框架
        frame.add(new HelloComponent3("hello java!"));//将自定义的组件容器添加到框架中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//通知框架在按下关闭按钮时退出应用
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public void testHelloComponent4() {
        JFrame frame = new JFrame("hello java2");//窗口框架
        frame.add(new HelloComponent4("hello java!"));//将自定义的组件容器添加到框架中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//通知框架在按下关闭按钮时退出应用
        frame.setSize(300, 300);
        frame.setVisible(true);

    }

    public void testMultipleBreak() {
        int i = 0;
        labelOne:
        while (true) {
            labelTwo:
            while (true) {
                Scanner scanner = new Scanner(System.in);
                int judge = scanner.nextInt();
                if (judge == 1) {
                    break labelOne;
                } else {
                    break labelTwo;
                }
            }
            i++;
        }
        System.out.println(i);
    }

    public void testDate() {
        Calendar date=new GregorianCalendar();
        System.out.println(date.getTime());
    }

}

public class HelloWorld {
    public static void main(String[] args) {
        Test test = new Test();
        test.testDate();
    }
}
















