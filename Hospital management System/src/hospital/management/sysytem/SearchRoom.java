package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;

    SearchRoom() {
        // Frame setup
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header panel
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1920, 100);
        header.setBackground(new Color(40, 60, 90));
        add(header);

        JLabel title = new JLabel("🔍 Search Room");
        title.setBounds(50, 25, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title);

        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 100, width, height - 100);
        panel.setBackground(new Color(235, 245, 250));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(109, 164, 170), 2),
                "Room Search",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(109, 164, 170)
        ));
        add(panel);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(50, 50, 100, 30);
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(statusLabel);

        choice = new Choice();
        choice.setBounds(150, 50, 200, 30);
        choice.setFont(new Font("Tahoma", Font.BOLD, 16));
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        // Table setup
        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 150, width - 200, height - 300);
        panel.add(scrollPane);

        // Column headers
        String[] headers = {"Room No", "Availability", "Price", "Bed Type"};
        int[] xPos = {50, 300, 550, 800};
        for (int i = 0; i < headers.length; i++) {
            JLabel label = new JLabel(headers[i]);
            label.setBounds(xPos[i], 120, 200, 25);
            label.setFont(new Font("Tahoma", Font.BOLD, 16));
            label.setForeground(Color.BLACK);
            panel.add(label);
        }

        // Buttons
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(width / 2 - 160, height - 150, 150, 35);
        searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        searchBtn.setBackground(Color.BLACK);
        searchBtn.setForeground(Color.WHITE);
        panel.add(searchBtn);

        JButton backBtn = new JButton("⬅ Back");
        backBtn.setBounds(width / 2 + 10, height - 150, 150, 35);
        backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        backBtn.setBackground(new Color(255, 100, 100));
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);

        // Listeners
        searchBtn.addActionListener((ActionEvent e) -> {
            String q = "SELECT * FROM Room WHERE Availability = '" + choice.getSelectedItem() + "'";
            loadTableData(q);
        });

        backBtn.addActionListener(e -> setVisible(false));

        // Load initial data
        loadTableData("SELECT * FROM Room");

        setVisible(true);
    }

    private void loadTableData(String query) {
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            if (table.getColumnCount() >= 4) {
                table.getColumnModel().getColumn(0).setPreferredWidth(300);
                table.getColumnModel().getColumn(1).setPreferredWidth(300);
                table.getColumnModel().getColumn(2).setPreferredWidth(300);
                table.getColumnModel().getColumn(3).setPreferredWidth(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
