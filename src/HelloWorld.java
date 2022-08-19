/*
import javax.swing.*;
import java.awt.*;

class HelloComponent extends JComponent{
    public void paintComponent(Graphics graphics)
    {
        graphics.drawString("hello java",124,85);
    }
}
public class HelloWorld {
    public static void main(String[] args) {
        JFrame frame=new JFrame("hello world");//初始化创建一个窗口类对象，并将窗口的标题起名为hello world
        JLabel label=new JLabel("hello world!",JLabel.CENTER);//在框架上保存文本的标签类
        frame.add(new HelloComponent());
        frame.setSize(300,300);//配置窗口的大小
        frame.setVisible(true);//显示窗口

    }
}
*/

import javax.swing.*;
import java.net.*;
import java.io.*;
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

    public void paintComponent(Graphics graphics)
    {
        graphics.drawString(theMessage,messageX,messageY);
    }

    /*mouseDragged()函数接受一个事件，当用户用鼠标做某个动作时，java的GUI组件会生成一个mouseEvent事件对象
    * 用户拖动鼠标不放时，会在监听者上自动调用mouseDragged()方法来接受所生成的事件*/
    public void mouseDragged(MouseEvent event)
    {
        messageX=event.getX();//返回鼠标当前X坐标
        messageY=event.getY();//同上

        /*要求组件得到重画将导致 Java 窗口系统安排在下一个可能的时间调用 paintComponent()方法，Java 会提
        供必要的 Graphics 对象*/
        repaint();
    }
    //只要将鼠标移到某个区域上而没有按鼠标键时即调用mouseMoved()方法
    public void mouseMoved(MouseEvent event)
    {

    }
}

class HelloComponent3 extends JComponent implements MouseMotionListener,ActionListener
{
    String theMessage;
    int messageX=125;
    int messageY=95;
    JButton theButton;//按钮组件

    int colorIndex;//相当于Color对象数组的索引，用于指定当前颜色的位置

    /*Color对象数组，表示按下按钮时循环变化的颜色，Color其实并非对象，而是类。以下的Color.red等都是类中的静态变量
    * 不必创建 Color类的一个实例Color 类的实例表示一个可见的颜色。为方便起见，Color 类包含有一些静态的预定义对象。
    * GREEN 变量和 Color 的其他静态成员不能被修改（在其初始化之后），因此它们实际上是常量*/
    /*有关java数组*/
    static Color[] someColors={Color.black,Color.red,Color.green,Color.blue,Color.magenta};

    public HelloComponent3(String message)
    {
        theMessage=message;
        theButton=new JButton("change color");//重载的按钮构造函数，这个构造函数在构建按钮对象的同时将指定的文本放在按钮上

        /*setLayout()：通知HelloComponent3 应当如何对增加到其中的其他组件加以排列以便显示，在此例
        中，我们使用了一种叫做 FlowLayout 的布局，将按钮置于顶部中央位置*/
        setLayout(new FlowLayout());

        /*add():它将JButton 追加到 HelloJava3 容器所管理的组件列表的末尾。在此之后，HelloJava3
        就要对 JButton 负责，它将使此按钮得到显示，而且要确定此按钮应当置于其窗口的哪个位置*/
        add(theButton);//将按钮添加到组件中

        /*将其组件theButton自身注册为按钮动作事件的一个监听者*/
        theButton.addActionListener(this);

        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics graphics)
    {
        graphics.drawString(theMessage,messageX,messageY);
    }

    public void mouseDragged(MouseEvent event)
    {
        messageX=event.getX();
        messageY=event.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent event){}

    synchronized private Color currentColor()
    {
        return someColors[colorIndex];
    }

    synchronized private void changeColor(){
        if(++colorIndex==someColors.length)
            colorIndex=0;
        setForeground(currentColor());
        repaint();
    }

    /*ActionListener要求实现actionPerformed()，只要出现一个ActionEvent事件即会调用此方法并接受传入的事件对象
    * */
    public void actionPerformed(ActionEvent event)
    {
        /* Java 中，== 是对同一性（identity）进行比较，而不是比较相等性（equality）；如果事件源和 theButton 是
        同一个对象则为 true。明确相等性和同一性之间的差别非常重要。如果两个 String 对象有相同的字符，而且字符序列也
        相同，那么可以认为这两个 String 对象相等。不过，它们可能并不是同一个对象。*/
        if(event.getSource()==theButton)//判断该事件是哪个组件发出的
        {
            changeColor();
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

    public void testHelloComponent2()
    {
        JFrame frame =new JFrame("hello java2");
        frame.add(new HelloComponent2("hello java!"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//通知框架在按下关闭按钮时退出应用
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    public void testHelloComponent3()
    {
        JFrame frame =new JFrame("hello java2");//窗口框架
        frame.add(new HelloComponent3("hello java!"));//将自定义的组件容器添加到框架中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//通知框架在按下关闭按钮时退出应用
        frame.setSize(300,300);
        frame.setVisible(true);

    }

}

public class HelloWorld {
    public static void main(String[] args) {
        Test test = new Test();
        test.testHelloComponent3();
    }
}
















