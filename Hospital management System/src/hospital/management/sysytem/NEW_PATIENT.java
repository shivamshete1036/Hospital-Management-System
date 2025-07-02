package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {

    JComboBox<String> comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldDeposit;
    JRadioButton r1, r2;
    Choice c1;
    JLabel date;
    JButton b1, b2;

    NEW_PATIENT() {
        // Fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(235, 245, 250));
        setLayout(null);

        // Header panel
        JPanel header = new JPanel();
        header.setBackground(new Color(40, 60, 90));
        header.setLayout(null);
        header.setBounds(0, 0, 1920, 100);
        add(header);

        JLabel heading = new JLabel("🏥 New Patient Admission Form");
        heading.setBounds(50, 25, 600, 40);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        header.add(heading);

        // Main form panel
        JPanel form = new JPanel();
        form.setLayout(null);
        form.setBackground(new Color(90, 156, 163));
        form.setBounds(300, 130, 900, 550);
        add(form);

        JLabel labelTitle = new JLabel("Patient Registration");
        labelTitle.setBounds(320, 10, 300, 30);
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelTitle.setForeground(Color.WHITE);
        form.add(labelTitle);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        JLabel patientImage = new JLabel(new ImageIcon(image));
        patientImage.setBounds(700, 160, 180, 180);
        form.add(patientImage);

        String[] labels = {
                "ID Proof", "Number", "Name", "Gender", "Disease",
                "Room No", "Time", "Deposit"
        };
        int y = 60;
        for (String label : labels) {
            JLabel l = new JLabel(label + " :");
            l.setBounds(40, y, 200, 25);
            l.setForeground(Color.WHITE);
            l.setFont(new Font("Segoe UI", Font.BOLD, 16));
            form.add(l);
            y += 50;
        }

        comboBox = new JComboBox<>(new String[]{"Aadhar Card", "Voter ID", "Driving License"});
        comboBox.setBounds(250, 60, 200, 25);
        comboBox.setBackground(Color.WHITE);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        form.add(comboBox);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(250, 110, 200, 25);
        form.add(textFieldNumber);

        textName = new JTextField();
        textName.setBounds(250, 160, 200, 25);
        form.add(textName);

        r1 = new JRadioButton("Male");
        r2 = new JRadioButton("Female");
        r1.setBackground(new Color(90, 156, 163));
        r2.setBackground(new Color(90, 156, 163));
        r1.setForeground(Color.WHITE);
        r2.setForeground(Color.WHITE);
        r1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        r2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        r1.setBounds(250, 210, 80, 25);
        r2.setBounds(340, 210, 80, 25);
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1); bg.add(r2);
        form.add(r1); form.add(r2);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(250, 260, 200, 25);
        form.add(textFieldDisease);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.statement.executeQuery("select * from Room");
            while (rs.next()) {
                c1.add(rs.getString("Room_No"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(250, 310, 200, 25);
        form.add(c1);

        Date date1 = new Date();
        date = new JLabel(date1.toString());
        date.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        date.setForeground(Color.WHITE);
        date.setBounds(250, 360, 300, 25);
        form.add(date);

        textFieldDeposit = new JTextField();
        textFieldDeposit.setBounds(250, 410, 200, 25);
        form.add(textFieldDeposit);

        // Buttons
        b1 = new JButton("➕ Add");
        b2 = new JButton("↩ Back");
        b1.setBounds(200, 470, 120, 35);
        b2.setBounds(350, 470, 120, 35);
        b1.setBackground(new Color(0, 123, 255));
        b2.setBackground(new Color(255, 80, 80));
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b2.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        b1.addActionListener(this);
        b2.addActionListener(this);
        form.add(b1); form.add(b2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            conn c = new conn();
            String gender = r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : "");

            String s1 = (String) comboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = gender;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposit.getText();

            try {
                String q = "insert into Patient_info values ('" + s1 + "', '" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                String q1 = "update Room set Availability = 'Occupied' where Room_No= " + s6;
                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Added Successfully");
                setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}
