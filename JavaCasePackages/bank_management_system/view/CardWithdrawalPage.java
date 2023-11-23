/*
 * Created by JFormDesigner on Tue Nov 21 12:48:55 CST 2023
 */

package bank_management_system.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * @author Mingxiang
 */
public class CardWithdrawalPage extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Water
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JPanel panel1;
    private JPanel panel2;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JPanel panel3;
    private JLabel label2;
    private JTextField textField1;
    private JPanel panel4;
    private JLabel label1;
    private JScrollPane scrollPane3;
    private JTextPane textPane1;
    private JPanel panel5;
    private JButton button1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    public CardWithdrawalPage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Water
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        panel1 = new JPanel();
        panel2 = new JPanel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        panel3 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        panel4 = new JPanel();
        label1 = new JLabel();
        scrollPane3 = new JScrollPane();
        textPane1 = new JTextPane();
        panel5 = new JPanel();
        button1 = new JButton();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("\u9000\u5361");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,dialogPane. getBorder( )) ); dialogPane. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

                //======== scrollPane1 ========
                {

                    //---- table1 ----
                    table1.setModel(new DefaultTableModel(
                        new Object[][] {
                            {null, null, null, null, null, null, "", ""},
                        },
                        new String[] {
                            "\u94f6\u884c\u5361\u53f7", "\u5f53\u524d\u5229\u7387", "\u94f6\u884c\u5361\u7c7b\u578b", "\u94f6\u884c\u5361\u4f59\u989d", "\u5f53\u524d\u5229\u606f", "\u4fe1\u7528\u989d\u5ea6", "\u94f6\u884c\u5361\u5bc6\u7801", "\u6b20\u6b3e"
                        }
                    ));
                    {
                        TableColumnModel cm = table1.getColumnModel();
                        cm.getColumn(0).setMinWidth(60);
                        cm.getColumn(0).setMaxWidth(100);
                        cm.getColumn(0).setPreferredWidth(60);
                        cm.getColumn(1).setMinWidth(60);
                        cm.getColumn(1).setMaxWidth(100);
                        cm.getColumn(1).setPreferredWidth(60);
                        cm.getColumn(2).setMinWidth(70);
                        cm.getColumn(2).setMaxWidth(100);
                        cm.getColumn(2).setPreferredWidth(70);
                        cm.getColumn(3).setMinWidth(70);
                        cm.getColumn(3).setMaxWidth(100);
                        cm.getColumn(3).setPreferredWidth(70);
                        cm.getColumn(4).setMinWidth(60);
                        cm.getColumn(4).setMaxWidth(100);
                        cm.getColumn(4).setPreferredWidth(60);
                        cm.getColumn(5).setMinWidth(60);
                        cm.getColumn(5).setMaxWidth(100);
                        cm.getColumn(5).setPreferredWidth(60);
                        cm.getColumn(6).setMinWidth(70);
                        cm.getColumn(6).setMaxWidth(100);
                        cm.getColumn(6).setPreferredWidth(70);
                        cm.getColumn(7).setMinWidth(60);
                        cm.getColumn(7).setMaxWidth(100);
                        cm.getColumn(7).setPreferredWidth(60);
                    }
                    scrollPane1.setViewportView(table1);
                }
                contentPanel.add(scrollPane1);

                //======== panel1 ========
                {
                    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new FlowLayout());

                        //======== scrollPane2 ========
                        {

                            //---- textArea1 ----
                            textArea1.setText("\u5728\u5de6\u8fb9\u9009\u4e2d\u4f60\u60f3\u8981\u9000\u7684\u94f6\u884c\u5361\uff0c\u7136\u540e\u5728\u4e0b\u65b9\u8f93\u5165\u4f60\u7684\u8d26\u6237\u5bc6\u7801\u4ee5\u786e\u8ba4\u64cd\u4f5c\u3002\u70b9\u51fb\u786e\u8ba4\u9000\u5361\u63d0\u4ea4\u9000\u5361\u7533\u8bf7\uff0c\u5982\u679c\u4f60\u7684\u5361\u4e0a\u6ca1\u6709\u672a\u507f\u8fd8\u7684\u4f59\u989d\uff0c\u5219\u9000\u6b3e\u7533\u8bf7\u4f1a\u6210\u529f\uff0c\u5426\u5219\u8bf7\u5148\u8fd8\u6e05\u6240\u6709\u6b20\u503a\u4e4b\u540e\u518d\u63d0\u4ea4\u9000\u5361\u7533\u8bf7");
                            textArea1.setRows(10);
                            textArea1.setTabSize(4);
                            textArea1.setColumns(30);
                            textArea1.setLineWrap(true);
                            scrollPane2.setViewportView(textArea1);
                        }
                        panel2.add(scrollPane2);
                    }
                    panel1.add(panel2);

                    //======== panel3 ========
                    {
                        panel3.setLayout(new FlowLayout());

                        //---- label2 ----
                        label2.setText("\u8f93\u5165\u4f60\u7684\u8d26\u6237\u5bc6\u7801\u786e\u8ba4\u64cd\u4f5c");
                        panel3.add(label2);

                        //---- textField1 ----
                        textField1.setColumns(20);
                        panel3.add(textField1);
                    }
                    panel1.add(panel3);

                    //======== panel4 ========
                    {
                        panel4.setLayout(new FlowLayout());

                        //---- label1 ----
                        label1.setText("\u4f60\u5f53\u524d\u7684\u603b\u503a\u52a1\uff1a");
                        panel4.add(label1);

                        //======== scrollPane3 ========
                        {
                            scrollPane3.setViewportView(textPane1);
                        }
                        panel4.add(scrollPane3);
                    }
                    panel1.add(panel4);

                    //======== panel5 ========
                    {
                        panel5.setLayout(new FlowLayout());

                        //---- button1 ----
                        button1.setText("\u70b9\u51fb\u6253\u5f00\u652f\u4ed8\u9875\u9762\u8fd8\u6e05\u4f60\u6240\u6709\u7684\u503a\u52a1");
                        panel5.add(button1);
                    }
                    panel1.add(panel5);
                }
                contentPanel.add(panel1);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("\u786e\u8ba4\u9000\u5361");
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
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
