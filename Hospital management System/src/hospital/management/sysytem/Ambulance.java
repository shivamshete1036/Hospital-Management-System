package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class Ambulance extends JFrame {

    Ambulance() {
        // Fullscreen setup
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header panel
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1920, 100);
        header.setBackground(new Color(40, 60, 90));
        add(header);

        JLabel title = new JLabel("🚑 Ambulance Availability");
        title.setBounds(50, 25, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(80, 120, 1760, 750);
        panel.setBackground(new Color(235, 245, 250));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(109, 164, 170), 2),
                "Ambulance Details",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(109, 164, 170)
        ));
        add(panel);

        // Table setup
        JTable table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 80, 1700, 600);
        panel.add(scrollPane);

        // Back Button
        JButton backBtn = new JButton("⬅ Back");
        backBtn.setBounds(1620, 700, 100, 30);
        backBtn.setBackground(new Color(255, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(backBtn);

        backBtn.addActionListener((ActionEvent e) -> setVisible(false));

        // Fetch & Display Data
        try {
            conn c = new conn();
            String q = "SELECT * FROM Ambulance";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Optional: Resize columns
            if (table.getColumnCount() >= 4) {
                table.getColumnModel().getColumn(0).setPreferredWidth(300); // Name
                table.getColumnModel().getColumn(1).setPreferredWidth(200); // Gender
                table.getColumnModel().getColumn(2).setPreferredWidth(200); // Available
                table.getColumnModel().getColumn(3).setPreferredWidth(300); // Location
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new Ambulance();
    }
}
