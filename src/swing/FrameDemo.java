package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * 基本窗口和按钮
 *
 * @author Mingxiang
 */
public class FrameDemo {
    private static Frame frame;

    static {
        frame = new Frame("这是一个窗口");
        //指定窗口的位置，大小
        frame.setLocation(100, 100);
        frame.setSize(500, 300);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            //这里用到了java的设计模式：适配器模式，不用重写多余的接口
        });
    }

    /**
     * 面板与按钮
     *
     * @param args arg游戏
     */
    public static void panel_button(String[] args) {


        //创建一个Panel对象，Panel及其他容器都不能独立存在，必须依附于window存在
        Panel pan = new Panel();
        pan.add(new TextField("这是一个测试文本"));
        pan.add(new JButton("这是一个测试按钮"));
        //将panel放入window中
        frame.add(pan);
        //设置window的位置
        frame.setBounds(100, 100, 500, 300);
        frame.setVisible(true);
    }

    /*private static void flushFrame() {
        frame = new Frame("窗口");
        frame.setLocation(100, 100);
        frame.setSize(500, 300);
    }*/

    /**
     * 滚动窗格
     */
    public static void scrollPane() {
        //传入参数使得总是有滚动条
        ScrollPane pane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        pane.add(new TextField("测试文本"));
        pane.add(new JButton("测试按钮"));
        frame.add(pane);
    }

    /**
     * 流式布局中，组件像水流一样向某个方向流动，遇到障碍就折回
     * 重头布局
     */
    public static void flowLayout() {
        //frame的默认布局管理器是borderLayout
        //第二个参数是水平间距，第三个参数是垂直间距

        //设置容器的布局管理器
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        //添加多个按钮
        for (int i = 0; i < 100; i++) {
            frame.add(new JButton("按钮" + i));
        }


    }

    /**
     * 边界布局管理器
     */
    public static void borderLayout() {
        frame.setLayout(new BorderLayout());
        frame.add(new JButton("北侧按钮"), BorderLayout.NORTH);//添加北侧按钮

        //往同一个区域添加多个组件会导致后添加的覆盖先添加的
    }

    /**
     * 卡片布局管理器
     */
    public static void cardLayout() {
        //创建一个Panel，来存储多张卡片
        Panel p1 = new Panel();
        //创建卡片布局管理器，并设给之前创建的容器
        CardLayout cardLay = new CardLayout();
        p1.setLayout(cardLay);
        //向Panel中存储多个组件
        String[] names = {"第1张", "第2张", "第3张"};
        for (int i = 0; i < names.length; i++) {
            p1.add(names[i], new JButton(names[i]));
        }
        //将Panel放在Frame的中间
        frame.add(p1);
        //创建另外一个panel p2 ，存储多个按钮组件
        Panel p2 = new Panel();
        //创建按钮组件
        JButton b1 = new JButton("上一张");
        JButton b2 = new JButton("下一张");
        JButton b3 = new JButton("第一张");
        JButton b4 = new JButton("最后一张");


        //创建一个事件监听器，监听按钮的点击
        /*ActionListener listener = e -> {
            String actionCommand = e.getActionCommand();//得到按钮上的文字
            switch (actionCommand) {
                case "上一张":
                    cardLay.previous();
                    break;
                case "下一张":
                    break;
                case "第一张":
                    break;
                case "最后一张":
                    break;

            }*/

        //将监听器和按钮绑定在一起

        //把按钮添加到容器p2中
    }


    public static void boxLayout() {
        //基于frame容器，创建一个BoxLayout对象，且该对象存放组件垂直存放
        BoxLayout layout = new BoxLayout(frame, BoxLayout.Y_AXIS);
        //把BoxLayout设置给frame
        frame.setLayout(layout);
        //向frame中添加两个按钮组件
        frame.add(new JButton("button1"));
        frame.add(new JButton("button2"));

    }

    public static void boxContainer() {
        Box hbox = Box.createHorizontalBox();
        hbox.add(new JButton("水平按钮1"));
        hbox.add(new JButton("水平按钮2"));
        Box hbox2 = Box.createVerticalBox();
        hbox2.add(new JButton("垂直1"));
        hbox2.add(new JButton("垂直2"));
        frame.add(hbox, BorderLayout.NORTH);
        frame.add(hbox2);

    }

    public static void test() {
        TextArea ta = new TextArea(5, 20);//文本框
        Choice colorChooser = new Choice();//选择框
        colorChooser.add("蓝色");
        colorChooser.add("黄色");
        frame.add(colorChooser);

        CheckboxGroup cbg = new CheckboxGroup();
        Checkbox box1 = new Checkbox("男", cbg, true);

        TextField tf = new TextField(50);

    }

    public static void dialog() {
        Dialog modalDialog = new Dialog(frame, "modal dialog", true);
        Dialog noneModal = new Dialog(frame, "none modal", false);

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(new TextField(20));
        verticalBox.add(new JButton("确认"));
        modalDialog.add(verticalBox);

        JButton button = new JButton("模式按钮");
        JButton button2 = new JButton("非模式按钮");

        //设置对话框的大小
        modalDialog.setBounds(20, 30, 300, 200);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modalDialog.setVisible(true);
            }
        });
        frame.add(button, BorderLayout.CENTER);

    }


    public static void menuBar() {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("文件");
        Menu edit = new Menu("编辑");
        file.add(new MenuItem("打开"));
        edit.add(new MenuItem("复制"));
        edit.add(new MenuItem("粘贴"));
        Menu format = new Menu("格式");
        format.add(new MenuItem("注释"));
        edit.add(format);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);

        //注意：将MenuBar放入窗口中不是用add方法
        frame.setMenuBar(menuBar);
        frame.add(new TextArea(100, 100));

    }


    public static void main(String[] args) {
        menuBar();
        frame.pack();//设置最佳大小,pack方法
        frame.setVisible(true);//使其可见
    }
}

class EventDemo {
    /* 事件绑定的基本步骤：
        1、创建事件源组件对象
        2、自定义类，实现XXXListener接口，重写方法
        3、创建事件监听器对象（自定义类对象）
        4、调用事件源组件对象的addXXXListener方法完成注册监听
     */

    TextField textField = new TextField(30);
    JButton sure = new JButton("确定");

    Frame frame = new Frame("测试事件处理");

    public EventDemo() {
        frame.setLocation(100, 100);
        frame.setSize(500, 300);
        sure.addActionListener(new MyListener());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(sure, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        EventDemo demo = new EventDemo();
        demo.run();

    }

    public void run() {
        frame.setVisible(true);
    }

    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("hello world");
        }

    }


}


class ListenerDemo2 {


    public static void main(String[] args) {
        Frame frame = new Frame("测试WindowListener");
        frame.setBounds(200, 200, 500, 300);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            //这里用到了java的设计模式：适配器模式，不用重写多余的接口
        });


        frame.pack();
        frame.setVisible(true);
    }
}

class SwingTest {

    private static JFrame frame = new JFrame("swing窗口");

    static {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击右上角关闭按钮时执行的动作
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);//默认居中显示
    }


    public static void testUI() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(javax.swing.plaf.multi.MultiLookAndFeel.class.getName());
        SwingUtilities.updateComponentTreeUI(frame);
        //UIManager.setLookAndFeel(java.desktop.UIManager.plaf.WindowsLookAndFeel.class.getName());
        JTextField f = new JTextField("输入");
        JButton button = new JButton("确认");

        frame.add(f, BorderLayout.NORTH);
        frame.add(button, BorderLayout.SOUTH);
    }

    public static void test2() {
        JMenu file = new JMenu("file");
        JMenu edit = new JMenu("edit");
        JMenuBar bar = new JMenuBar();
        bar.add(file);
        bar.add(edit);
        frame.setJMenuBar(bar);


        JMenuItem autoChangeLine = new JMenuItem("auto change line");
        JMenuItem copy = new JMenuItem("copy");
        JMenuItem paste = new JMenuItem("paste");

        edit.add(autoChangeLine);
        edit.addSeparator();
        edit.add(copy);
        edit.add(paste);
        edit.addSeparator();


        JMenu format = new JMenu("format");
        JMenuItem annotation = new JMenuItem("annotation");
        format.add(annotation);
        edit.add(format);

        JTextArea jTextArea = new JTextArea(8, 20);

        //Jlist的使用方法：先创建一个String[]数组，然后将其传入JList的构造函数
        String[] colors = {"红色", "蓝色", "绿色"};
        JList<String> colorList = new JList<>(colors);


        JComboBox<String> colorSelect = new JComboBox<>();
        colorSelect.setToolTipText("选择框");
        //向选择框中插入可选项使用addItem接口
        colorSelect.addItem("红色");
        colorSelect.addItem("蓝色");

        ButtonGroup item = new ButtonGroup();//只有将选择按钮都放到ButtonGroup中才能实现单选效果
        JRadioButton male = new JRadioButton("男", true);//可以被选择的按钮组件
        JRadioButton female = new JRadioButton("女", false);
        item.add(male);
        item.add(female);

        JCheckBox isMarried = new JCheckBox("是否", true);

        JTextField tf = new JTextField(40);
        JButton ok = new JButton("确定");
        //为图标设置大小
        Image image = new ImageIcon("src/main/resources/start.png").getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ok.setIcon(new ImageIcon(image));


        //组装视图
        JPanel bootomJPanel = new JPanel();
        bootomJPanel.add(tf);
        bootomJPanel.add(ok);
        frame.add(bootomJPanel, BorderLayout.SOUTH);

        JPanel selectPanel = new JPanel();
        selectPanel.add(colorSelect);
        //这里不是把按钮组放入
        selectPanel.add(male);
        selectPanel.add(female);
        selectPanel.add(isMarried);

        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(jTextArea);
        verticalBox.add(selectPanel);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(colorList);
        horizontalBox.add(verticalBox);
        frame.add(horizontalBox);

        //frame.getContentPane() 获取frame的顶层容器
        JPopupMenu newMenu = new JPopupMenu();
        newMenu.add(new JMenuItem("风格1"));
        newMenu.add(new JMenuItem("风格2"));
        jTextArea.setComponentPopupMenu(newMenu);//通过此接口直接将弹出式菜单绑定到该组件上，无需添加鼠标右键事件

    }

    public static void testTable() {
        //创建一个一维数组，来标识每一列的标题
        //创建一个二维数组，存储表格中每一行的数据，其中二维数组内部的每一个一维数组
        //根据第一步和第二步创建的一维数组和二维数组，创建JTable对象
        //将其添加到其他容器中显示
        Object[] titles = {"姓名", "年龄", "性别"};
        Object[][] data = {
                new Object[]{"张三", 52, "男"},
                new Object[]{"苏格拉滴", 12, "女"}
        };
        JTable table = new JTable(data, titles);

        //设置表格的选择模式
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //设置表格的最小列宽或最大列宽
        table.getColumn(titles[0]).setMinWidth(40);

        //为JTable添加滚动条内容
        frame.add(new JScrollPane(table));


    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        testTable();
        frame.pack();
        frame.setVisible(true);
    }


}

