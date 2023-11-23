/*
 * Created by JFormDesigner on Tue Nov 21 13:02:54 CST 2023
 */

package bank_management_system.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * @author Mingxiang
 */
public class FinancialTablePage extends JFrame {
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Water
    private JScrollPane scrollPane1;
    private JTable table1;

    public FinancialTablePage() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Water
        scrollPane1 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        setTitle("\u8d22\u52a1\u62a5\u8868");
        var contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "\u62a5\u8868\u7f16\u53f7", "\u62a5\u8868\u65e5\u671f", "\u603b\u5b58\u6b3e\u91d1\u989d", "\u603b\u53d6\u6b3e\u91d1\u989d", "\u51c0\u4f59\u989d"
                }
            ));
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1);
        setSize(530, 495);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
