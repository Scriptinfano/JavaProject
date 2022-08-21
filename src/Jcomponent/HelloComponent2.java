package Jcomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
