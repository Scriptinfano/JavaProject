/*
 * Created by JFormDesigner on Tue Nov 21 09:32:29 CST 2023
 */

package bank_management_system.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mingxiang
 */
public class MainMenu extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Water
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JScrollPane scrollPane1;
    private JTextPane textPane1;
    private JPanel panel3;
    private JLabel label2;
    private JScrollPane scrollPane2;
    private JTextPane textPane2;
    private JPanel panel4;
    private JLabel label3;
    private JScrollPane scrollPane3;
    private JTextPane textPane3;
    private JPanel panel5;
    private JLabel label4;
    private JScrollPane scrollPane4;
    private JTextPane textPane4;
    private JPanel panel9;
    private JButton button2;
    private JPanel panel7;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button4;
    private JPanel panel8;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button3;
    public MainMenu() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Water
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        scrollPane1 = new JScrollPane();
        textPane1 = new JTextPane();
        panel3 = new JPanel();
        label2 = new JLabel();
        scrollPane2 = new JScrollPane();
        textPane2 = new JTextPane();
        panel4 = new JPanel();
        label3 = new JLabel();
        scrollPane3 = new JScrollPane();
        textPane3 = new JTextPane();
        panel5 = new JPanel();
        label4 = new JLabel();
        scrollPane4 = new JScrollPane();
        textPane4 = new JTextPane();
        panel9 = new JPanel();
        button2 = new JButton();
        panel7 = new JPanel();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button4 = new JButton();
        panel8 = new JPanel();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        button13 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("\u50a8\u84c4\u7ba1\u7406\u7cfb\u7edf\u4e3b\u754c\u9762");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax .
            swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing .border
            . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .Font ( "Dia\u006cog"
            , java .awt . Font. BOLD ,12 ) ,java . awt. Color .red ) ,panel1. getBorder
            () ) ); panel1. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java
            . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName () ) )throw new RuntimeException
            ( ) ;} } );
            panel1.setLayout(new GridLayout(5, 0));

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("\u4f60\u5df2\u7ecf\u767b\u5165\u8d26\u6237");
                panel2.add(label1);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(textPane1);
                }
                panel2.add(scrollPane1);
            }
            panel1.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout());

                //---- label2 ----
                label2.setText("\u4f60\u7684\u7528\u6237\u540d\uff1a");
                panel3.add(label2);

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(textPane2);
                }
                panel3.add(scrollPane2);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout());

                //---- label3 ----
                label3.setText("\u4f60\u7684\u8054\u7cfb\u65b9\u5f0f\uff1a");
                panel4.add(label3);

                //======== scrollPane3 ========
                {
                    scrollPane3.setViewportView(textPane3);
                }
                panel4.add(scrollPane3);
            }
            panel1.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new FlowLayout());

                //---- label4 ----
                label4.setText("\u4f60\u7684\u8eab\u4efd\u8bc1\u53f7\uff1a");
                panel5.add(label4);

                //======== scrollPane4 ========
                {
                    scrollPane4.setViewportView(textPane4);
                }
                panel5.add(scrollPane4);
            }
            panel1.add(panel5);

            //======== panel9 ========
            {
                panel9.setLayout(new FlowLayout());

                //---- button2 ----
                button2.setText("\u66f4\u6539\u4e2a\u4eba\u4fe1\u606f");
                panel9.add(button2);
            }
            panel1.add(panel9);
        }
        contentPane.add(panel1, BorderLayout.CENTER);

        //======== panel7 ========
        {
            panel7.setLayout(new GridLayout(5, 1, 5, 5));

            //---- button6 ----
            button6.setText("\u8f6c\u8d26");
            panel7.add(button6);

            //---- button7 ----
            button7.setText("\u6539\u5bc6");
            panel7.add(button7);

            //---- button8 ----
            button8.setText("\u5f00\u5361");
            panel7.add(button8);

            //---- button9 ----
            button9.setText("\u9000\u5361");
            panel7.add(button9);

            //---- button4 ----
            button4.setText("\u67e5\u8be2\u7edf\u8ba1");
            panel7.add(button4);
        }
        contentPane.add(panel7, BorderLayout.EAST);

        //======== panel8 ========
        {
            panel8.setLayout(new GridLayout(5, 1, 5, 5));

            //---- button10 ----
            button10.setText("\u94f6\u884c\u5361\u4fe1\u606f");
            panel8.add(button10);

            //---- button11 ----
            button11.setText("\u5b58\u6b3e");
            panel8.add(button11);

            //---- button12 ----
            button12.setText("\u53d6\u6b3e");
            panel8.add(button12);

            //---- button13 ----
            button13.setText("\u6302\u5931");
            panel8.add(button13);

            //---- button3 ----
            button3.setText("\u8d22\u52a1\u62a5\u8868");
            panel8.add(button3);
        }
        contentPane.add(panel8, BorderLayout.WEST);
        setSize(495, 370);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
