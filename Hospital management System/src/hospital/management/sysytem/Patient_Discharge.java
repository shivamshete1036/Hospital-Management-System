package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_Discharge extends JFrame {

    Patient_Discharge() {
        // Fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(235, 245, 250));
        setLayout(null);

        // Header Panel
        JPanel header = new JPanel();
        header.setBackground(new Color(40, 60, 90));
        header.setBounds(0, 0, 1920, 100);
        header.setLayout(null);
        add(header);

        JLabel heading = new JLabel(" Patient Discharge Panel");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        heading.setBounds(50, 25, 600, 40);
        header.add(heading);

        // Form Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(100, 150, 900, 500);
        panel.setBackground(new Color(90, 156, 163));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255), 2),
                "Discharge Summary",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 20),
                Color.WHITE
        ));
        add(panel);

        // Labels and Fields
        String[] fieldLabels = {"Patient ID :", "Room Number :", "Check-in Time :", "Discharge Time :"};
        int y = 60;
        for (String text : fieldLabels) {
            JLabel lbl = new JLabel(text);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
            lbl.setForeground(Color.WHITE);
            lbl.setBounds(60, y, 150, 30);
            panel.add(lbl);
            y += 50;
        }

        Choice choice = new Choice();
        choice.setBounds(230, 60, 200, 25);
        panel.add(choice);

        JLabel RNo = new JLabel();
        JLabel InTime = new JLabel();
        JLabel OutTime = new JLabel(new Date().toString());

        JLabel[] values = {RNo, InTime, OutTime};
        y = 110;
        for (JLabel value : values) {
            value.setBounds(230, y, 400, 25);
            value.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            value.setForeground(Color.WHITE);
            panel.add(value);
            y += 50;
        }

        // Fill Choice with Patient IDs
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from Patient_Info");
            while (rs.next()) {
                choice.add(rs.getString("Number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons
        JButton checkBtn = new JButton("\uD83D\uDD0D Check");
        JButton dischargeBtn = new JButton("\uD83D\uDDD1 Discharge");
        JButton backBtn = new JButton("\u2B05 Back");

        checkBtn.setBounds(100, 320, 150, 35);
        dischargeBtn.setBounds(300, 320, 150, 35);
        backBtn.setBounds(500, 320, 150, 35);

        JButton[] buttons = {checkBtn, dischargeBtn, backBtn};
        for (JButton btn : buttons) {
            btn.setBackground(new Color(0, 123, 255));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
            btn.setFocusPainted(false);
            panel.add(btn);
        }

        // Button Actions
        checkBtn.addActionListener(e -> {
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery("select * from Patient_Info where Number = '" + choice.getSelectedItem() + "'");
                while (rs.next()) {
                    RNo.setText(rs.getString("Room_Number"));
                    InTime.setText(rs.getString("Time"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        dischargeBtn.addActionListener(e -> {
            try {
                conn c = new conn();
                c.statement.executeUpdate("delete from Patient_Info where Number = '" + choice.getSelectedItem() + "'");
                c.statement.executeUpdate("update Room set Availability = 'Available' where Room_No = '" + RNo.getText() + "'");
                JOptionPane.showMessageDialog(null, "\u2714 Patient Discharged Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backBtn.addActionListener(e -> setVisible(false));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_Discharge();
    }
}
