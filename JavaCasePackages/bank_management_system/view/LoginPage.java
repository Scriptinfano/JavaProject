/*
 * Created by JFormDesigner on Tue Nov 21 11:54:03 CST 2023
 */

package bank_management_system.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Mingxiang
 */
public class LoginPage extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Water
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel3;
    private JLabel label3;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textField2;
    private JPanel panel1;
    private JLabel label1;
    private JTextField textField1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton button2;
    private JButton cancelButton;
    public LoginPage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Water
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel3 = new JPanel();
        label3 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        textField2 = new JTextField();
        panel1 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        button2 = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u767b\u5f55");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
            0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
            . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
            red ) ,dialogPane. getBorder () ) ); dialogPane. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
            beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout());

                    //---- label3 ----
                    label3.setText("\u6b22\u8fce\u767b\u5f55\u94f6\u884c\u50a8\u84c4\u7ba1\u7406\u7cfb\u7edf");
                    panel3.add(label3);
                }
                contentPanel.add(panel3);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u8d26\u53f7");
                    panel2.add(label2);

                    //---- textField2 ----
                    textField2.setColumns(20);
                    panel2.add(textField2);
                }
                contentPanel.add(panel2);

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- label1 ----
                    label1.setText("\u5bc6\u7801");
                    panel1.add(label1);

                    //---- textField1 ----
                    textField1.setColumns(20);
                    panel1.add(textField1);
                }
                contentPanel.add(panel1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- okButton ----
                okButton.setText("\u767b\u5f55");
                buttonBar.add(okButton);

                //---- button2 ----
                button2.setText("\u6ce8\u518c");
                buttonBar.add(button2);

                //---- cancelButton ----
                cancelButton.setText("\u9000\u51fa");
                buttonBar.add(cancelButton);
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(400, 260);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
