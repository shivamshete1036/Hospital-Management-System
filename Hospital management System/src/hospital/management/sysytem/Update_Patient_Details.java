package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_Patient_Details extends JFrame {

    Update_Patient_Details() {
        // Frame setup
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Header
        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1920, 100);
        header.setBackground(new Color(40, 60, 90));
        add(header);

        JLabel title = new JLabel("📝 Update Patient Details");
        title.setBounds(50, 25, 600, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(title);

        // Panel
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        JPanel panel = new JPanel();
        panel.setBounds(50, 120, width - 100, height - 150);
        panel.setBackground(new Color(235, 245, 250));
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(109, 164, 170), 2),
                "Edit Patient",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(109, 164, 170)
        ));
        add(panel);

        JLabel label1 = new JLabel("Select Patient:");
        label1.setBounds(50, 40, 200, 30);
        label1.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(label1);

        Choice choice = new Choice();
        choice.setBounds(250, 40, 250, 30);
        choice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(choice);

        JLabel[] labels = {
                new JLabel("Room Number"),
                new JLabel("In Time"),
                new JLabel("Amount Paid (RS)"),
                new JLabel("Pending Amount (RS)")
        };
        int y = 100;
        for (JLabel label : labels) {
            label.setBounds(50, y, 200, 30);
            label.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(label);
            y += 60;
        }

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(250, 100, 250, 30);
        panel.add(textFieldR);

        JTextField textFieldInTime = new JTextField();
        textFieldInTime.setBounds(250, 160, 250, 30);
        panel.add(textFieldInTime);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(250, 220, 250, 30);
        panel.add(textFieldAmount);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(250, 280, 250, 30);
        panel.add(textFieldPending);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = i1.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(image));
        imgLabel.setBounds(600, 100, 300, 300);
        panel.add(imgLabel);

        // Buttons
        JButton check = new JButton("CHECK");
        check.setBounds(250, 340, 100, 30);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);

        JButton update = new JButton("UPDATE");
        update.setBounds(100, 400, 100, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        panel.add(update);

        JButton back = new JButton("⬅ BACK");
        back.setBounds(250, 400, 100, 30);
        back.setBackground(new Color(255, 100, 100));
        back.setForeground(Color.WHITE);
        panel.add(back);

        // Load patient names
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from Patient_Info");
            while (rs.next()) {
                choice.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Actions
        check.addActionListener(e -> {
            String id = choice.getSelectedItem();
            try {
                conn c = new conn();
                ResultSet rs = c.statement.executeQuery("select * from Patient_Info where Name='" + id + "'");
                while (rs.next()) {
                    textFieldR.setText(rs.getString("Room_Number"));
                    textFieldInTime.setText(rs.getString("Time"));
                    textFieldAmount.setText(rs.getString("Deposit"));
                }
                ResultSet rs1 = c.statement.executeQuery("select * from Room where Room_No='" + textFieldR.getText() + "'");
                if (rs1.next()) {
                    int total = Integer.parseInt(rs1.getString("Price"));
                    int paid = Integer.parseInt(textFieldAmount.getText());
                    textFieldPending.setText(String.valueOf(total - paid));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        update.addActionListener(e -> {
            try {
                conn c = new conn();
                String q = "update Patient_Info set Room_Number='" + textFieldR.getText() + "', Time='" + textFieldInTime.getText() + "', Deposit='" + textFieldAmount.getText() + "' where Name='" + choice.getSelectedItem() + "'";
                c.statement.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Updated Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        back.addActionListener(e -> setVisible(false));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_Patient_Details();
    }
}
