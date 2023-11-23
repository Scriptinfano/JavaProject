/*
 * Created by JFormDesigner on Tue Nov 21 11:05:36 CST 2023
 */

package bank_management_system.view;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mingxiang
 */
public class ChangePasswordPage extends JDialog {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Water
    private JPanel panel3;
    private JLabel label3;
    private JTextField textField3;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField4;
    private JPanel panel5;
    private JButton button2;
    private JButton button3;
    public ChangePasswordPage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Water
        panel3 = new JPanel();
        label3 = new JLabel();
        textField3 = new JTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textField4 = new JTextField();
        panel5 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        setTitle("\u66f4\u6539\u8d26\u6237\u5bc6\u7801");
        var contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        //======== panel3 ========
        {
            panel3.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,panel3. getBorder( )) ); panel3. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );
            panel3.setLayout(new FlowLayout());

            //---- label3 ----
            label3.setText("\u8f93\u5165\u65b0\u5bc6\u7801");
            panel3.add(label3);

            //---- textField3 ----
            textField3.setColumns(20);
            panel3.add(textField3);
        }
        contentPane.add(panel3);

        //======== panel4 ========
        {
            panel4.setLayout(new FlowLayout());

            //---- label4 ----
            label4.setText("\u518d\u6b21\u8f93\u5165\u65b0\u5bc6\u7801");
            panel4.add(label4);

            //---- textField4 ----
            textField4.setColumns(20);
            panel4.add(textField4);
        }
        contentPane.add(panel4);

        //======== panel5 ========
        {
            panel5.setLayout(new FlowLayout());

            //---- button2 ----
            button2.setText("\u786e\u8ba4\u66f4\u6539");
            panel5.add(button2);

            //---- button3 ----
            button3.setText("\u53d6\u6d88\u66f4\u6539");
            panel5.add(button3);
        }
        contentPane.add(panel5);
        setSize(465, 230);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
