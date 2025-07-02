package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Department extends JFrame {

    Department() {
        // Fullscreen and undecorated settings
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header Panel
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1920, 100);
        header.setBackground(new Color(40, 60, 90));
        add(header);

        JLabel title = new JLabel("🏥 Department Details");
        title.setBounds(50, 25, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(235, 245, 250));
        panel.setBounds(80, 120, 1760, 750);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(90, 156, 163), 2),
                "All Departments",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(90, 156, 163)
        ));
        add(panel);

        // Table
        JTable table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Enable horizontal scroll

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 1700, 600);
        panel.add(scrollPane);

        // Back Button
        JButton backBtn = new JButton("⬅ Back");
        backBtn.setBounds(1600, 700, 100, 30);
        backBtn.setBackground(new Color(255, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(backBtn);
        backBtn.addActionListener((ActionEvent e) -> setVisible(false));

        // Fetch & set table data
        try {
            conn c = new conn();
            String q = "SELECT * FROM department";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Optional: Adjust column width if needed
            if (table.getColumnCount() > 1) {
                table.getColumnModel().getColumn(0).setPreferredWidth(500); // Department
                table.getColumnModel().getColumn(1).setPreferredWidth(500); // Phone No
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Department();
    }
}
