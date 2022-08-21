package Jcomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
