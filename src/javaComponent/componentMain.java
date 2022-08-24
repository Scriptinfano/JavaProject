package javaComponent;

import javax.swing.*;

public class componentMain {
    public static void main(String[] args)
    {

    }
    public void testHelloComponent2() {
        JFrame frame = new JFrame("hello java2");
        frame.add(new HelloComponent2("hello java!"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//通知框架在按下关闭按钮时退出应用
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

        public static void testHelloComponent3() {
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

    }
