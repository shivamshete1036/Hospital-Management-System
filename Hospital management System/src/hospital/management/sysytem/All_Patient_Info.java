package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class All_Patient_Info extends JFrame {

    All_Patient_Info() {
        // Get screen dimensions dynamically
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 245, 250));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, screenWidth, 100);
        headerPanel.setBackground(new Color(40, 60, 90));
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel title = new JLabel("📝 All Patient Information");
        title.setBounds(50, 25, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headerPanel.add(title);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);
        tablePanel.setBackground(new Color(109, 164, 170));
        tablePanel.setBounds(50, 120, screenWidth - 100, screenHeight - 200);
        tablePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(70, 120, 130), 2),
                "Patient Records",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                Color.WHITE
        ));
        add(tablePanel);

        // Table
        JTable table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevents columns from being cut

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, tablePanel.getWidth() - 40, tablePanel.getHeight() - 140);
        tablePanel.add(scrollPane);

        // Load data
        try {
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM Patient_info");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            // Increase specific column widths (index starts from 0)
            table.getColumnModel().getColumn(0).setPreferredWidth(150); // ID
            table.getColumnModel().getColumn(1).setPreferredWidth(150); // Number
            table.getColumnModel().getColumn(2).setPreferredWidth(200); // Name
            table.getColumnModel().getColumn(3).setPreferredWidth(100); // Gender
            table.getColumnModel().getColumn(4).setPreferredWidth(200); // Disease
            table.getColumnModel().getColumn(5).setPreferredWidth(100); // Room No
            table.getColumnModel().getColumn(6).setPreferredWidth(220); // Time
            table.getColumnModel().getColumn(7).setPreferredWidth(120); // Deposit
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Back Button
        JButton backBtn = new JButton("⬅ Back");
        backBtn.setBounds(tablePanel.getWidth() - 140, tablePanel.getHeight() - 50, 100, 30);
        backBtn.setBackground(new Color(255, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setFocusPainted(false);
        tablePanel.add(backBtn);

        backBtn.addActionListener((ActionEvent e) -> setVisible(false));

        setVisible(true);
    }

    public static void main(String[] args) {
        new All_Patient_Info();
    }
}
