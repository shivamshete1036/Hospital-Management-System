package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class Room extends JFrame {
    JTable table;
    private JFrame previousFrame;

    Room(JFrame previousFrame) {
        this.previousFrame = previousFrame;

        // Frame setup
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

        JLabel title = new JLabel("🛏️ Room Details");
        title.setBounds(50, 25, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 120, 1700, 750);
        panel.setBackground(new Color(235, 245, 250));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(109, 164, 170), 2),
                "Room Information",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(109, 164, 170)
        ));
        add(panel);

        // Table
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 100, 1000, 330);
        panel.add(scrollPane);

        // Fetch data
        try {
            conn c = new conn();
            String q = "SELECT * FROM Room";
            ResultSet rs = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            if (table.getColumnCount() >= 4) {
                table.getColumnModel().getColumn(0).setPreferredWidth(150); // Room No
                table.getColumnModel().getColumn(1).setPreferredWidth(150); // Availability
                table.getColumnModel().getColumn(2).setPreferredWidth(150); // Price
                table.getColumnModel().getColumn(3).setPreferredWidth(200); // Room Type
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/roomm.png"));
        Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setBounds(1100, 180, 250, 250);
        panel.add(imageLabel);

        // Back Button
        JButton backBtn = new JButton("⬅ Back");
        backBtn.setBounds(1500, 650, 120, 30);
        backBtn.setBackground(new Color(255, 100, 100));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(backBtn);

        backBtn.addActionListener(e -> {
            setVisible(false);
            if (previousFrame != null) {
                previousFrame.setVisible(true);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Room(null);
    }
}
