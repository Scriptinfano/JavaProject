/*
 * Created by JFormDesigner on Tue Nov 21 10:29:01 CST 2023
 */

package bank_management_system.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Mingxiang
 */
public class RegisterPage extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Water
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JLabel label1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField textField1;
    private JPanel panel3;
    private JLabel label3;
    private JTextField textField2;
    private JPanel panel4;
    private JLabel label4;
    private JTextField textField3;
    private JPanel panel5;
    private JLabel label5;
    private JTextField textField4;
    private JPanel panel6;
    private JLabel label6;
    private JTextField textField5;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    public RegisterPage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Water
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        textField2 = new JTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        textField3 = new JTextField();
        panel5 = new JPanel();
        label5 = new JLabel();
        textField4 = new JTextField();
        panel6 = new JPanel();
        label6 = new JLabel();
        textField5 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u6ce8\u518c\u8d26\u6237");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
            . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder
            . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .
            awt .Font .BOLD ,12 ), java. awt. Color. red) ,dialogPane. getBorder( )) )
            ; dialogPane. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
            ) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
            ;
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- label1 ----
                    label1.setText("\u6ce8\u518c\u50a8\u6237\u8d26\u6237");
                    panel1.add(label1);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText("\u59d3\u540d");
                    panel2.add(label2);

                    //---- textField1 ----
                    textField1.setColumns(20);
                    panel2.add(textField1);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setLayout(new FlowLayout());

                    //---- label3 ----
                    label3.setText("\u8054\u7cfb\u4fe1\u606f");
                    panel3.add(label3);

                    //---- textField2 ----
                    textField2.setColumns(20);
                    panel3.add(textField2);
                }
                contentPanel.add(panel3);

                //======== panel4 ========
                {
                    panel4.setLayout(new FlowLayout());

                    //---- label4 ----
                    label4.setText("\u8eab\u4efd\u8bc1\u53f7");
                    panel4.add(label4);

                    //---- textField3 ----
                    textField3.setColumns(20);
                    panel4.add(textField3);
                }
                contentPanel.add(panel4);

                //======== panel5 ========
                {
                    panel5.setLayout(new FlowLayout());

                    //---- label5 ----
                    label5.setText("\u8d26\u6237\u5bc6\u7801");
                    panel5.add(label5);

                    //---- textField4 ----
                    textField4.setColumns(20);
                    panel5.add(textField4);
                }
                contentPanel.add(panel5);

                //======== panel6 ========
                {
                    panel6.setLayout(new FlowLayout());

                    //---- label6 ----
                    label6.setText("\u5f00\u6237\u624b\u7eed\u8d39");
                    panel6.add(label6);

                    //---- textField5 ----
                    textField5.setColumns(20);
                    panel6.add(textField5);
                }
                contentPanel.add(panel6);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u786e\u5b9a");
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        setSize(500, 380);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
