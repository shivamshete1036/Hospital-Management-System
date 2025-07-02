package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame {

    Reception() {
        // Set fullscreen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Top Banner Panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setLayout(null);
        bannerPanel.setBounds(0, 0, 1920, 150);
        bannerPanel.setBackground(new Color(40, 60, 90));
        add(bannerPanel);

        JLabel title = new JLabel("\uD83C\uDFE5 Hospital Management Dashboard");
        title.setBounds(40, 40, 800, 40);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        bannerPanel.add(title);

        // Icons on right
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/dr.png"));
        Image image1 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel label1 = new JLabel(new ImageIcon(image1));
        label1.setBounds(1700, 20, 100, 100);
        bannerPanel.add(label1);

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icon/amb.png"));
        Image image2 = i2.getImage().getScaledInstance(130, 50, Image.SCALE_SMOOTH);
        JLabel label2 = new JLabel(new ImageIcon(image2));
        label2.setBounds(1520, 50, 130, 50);
        bannerPanel.add(label2);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(3, 4, 40, 30));
        navPanel.setBounds(100, 200, 1320, 350);
        navPanel.setBackground(new Color(245, 250, 255));
        navPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(109, 164, 170), 2),
                "Quick Navigation",
                0, 0,
                new Font("Segoe UI", Font.BOLD, 22),
                new Color(109, 164, 170)
        ));
        add(navPanel);

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);
        Color btnBg = new Color(246, 215, 118);

        JButton btn1 = createButton("➕ Add New Patient", btnFont, btnBg, () -> new NEW_PATIENT());
        JButton btn2 = createButton("🛏️ Room", btnFont, btnBg, () -> {
            new Room(this);
        });
        JButton btn3 = createButton("🏬 Department", btnFont, btnBg, () -> new Department());
        JButton btn4 = createButton("👩‍⚕️ All Employee Info", btnFont, btnBg, () -> new Employee_info());
        JButton btn5 = createButton("📋 Patient Info", btnFont, btnBg, () -> new All_Patient_Info());
        JButton btn6 = createButton("📤 Patient Discharge", btnFont, btnBg, () -> new Patient_Discharge());
        JButton btn7 = createButton("✏️ Update Patient Details", btnFont, btnBg, () -> new Update_Patient_Details());
        JButton btn8 = createButton("🚑 Hospital Ambulance", btnFont, btnBg, () -> new Ambulance());
        JButton btn9 = createButton("🔍 Search Room", btnFont, btnBg, () -> new SearchRoom());
        JButton btn10 = createButton("🔓 Logout", btnFont, new Color(255, 100, 100), () -> {
            setVisible(false);
            new Login();
        });

        navPanel.add(btn1);
        navPanel.add(btn2);
        navPanel.add(btn3);
        navPanel.add(btn4);
        navPanel.add(btn5);
        navPanel.add(btn6);
        navPanel.add(btn7);
        navPanel.add(btn8);
        navPanel.add(btn9);
        navPanel.add(btn10);
        navPanel.add(new JLabel());
        navPanel.add(new JLabel());

        setVisible(true);
    }

    private JButton createButton(String text, Font font, Color bg, Runnable action) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setForeground(Color.BLACK);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> action.run());
        return btn;
    }

    public static void main(String[] args) {
        new Reception();
    }
}
