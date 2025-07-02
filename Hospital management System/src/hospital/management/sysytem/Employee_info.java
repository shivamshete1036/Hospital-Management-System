package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Employee_info extends JFrame {

    Employee_info() {
        // Fullscreen window
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 1920, 100);
        headerPanel.setBackground(new Color(40, 60, 90));
        add(headerPanel);

        JLabel title = new JLabel("👨‍⚕️ Employee Information");
        title.setBounds(50, 25, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerPanel.add(title);

        // Table panel with BorderLayout for scroll handling
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(235, 245, 250));
        tablePanel.setBounds(80, 120, 1760, 800);
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(109, 164, 170), 2),
                "All Employees",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(109, 164, 170)
        ));
        add(tablePanel);

        // Table setup
        JTable table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // enable horizontal scrolling
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        // Scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Back button
        JButton backBtn = new JButton("⬅ Back");
        backBtn.setBackground(new Color(255, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        backBtn.setBounds(1700, 940, 120, 35); // placed near bottom-right of screen
        add(backBtn);

        backBtn.addActionListener(e -> setVisible(false));

        // Fetch & display data
        try {
            conn c = new conn();
            String q = "SELECT * FROM EMP_INFO";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Adjust column widths
            table.getColumnModel().getColumn(0).setPreferredWidth(150);  // Name
            table.getColumnModel().getColumn(1).setPreferredWidth(100);  // Post
            table.getColumnModel().getColumn(2).setPreferredWidth(150);  // Phone Number
            table.getColumnModel().getColumn(3).setPreferredWidth(100);  // Salary
            table.getColumnModel().getColumn(4).setPreferredWidth(200);  // Gmail
            table.getColumnModel().getColumn(5).setPreferredWidth(200);  // Aadhar Number

        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
