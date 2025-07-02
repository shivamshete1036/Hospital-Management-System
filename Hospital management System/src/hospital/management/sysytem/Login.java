package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;

    Login() {
        // Make full screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false); // Keep title bar
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screenSize.width;
        int h = screenSize.height;

        // Main panel to center content (70% width, 70% height)
        int panelWidth = (int) (w * 0.7);
        int panelHeight = (int) (h * 0.7);
        int panelX = (w - panelWidth) / 2;
        int panelY = (h - panelHeight) / 2;

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(panelX, panelY, panelWidth, panelHeight);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(255, 245, 235));
        add(mainPanel);

        // Left Welcome Panel (30% of mainPanel width)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(40, 60, 90));
        leftPanel.setBounds(0, 0, panelWidth / 2, panelHeight);
        leftPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("<html><center>🏥<br>Welcome to<br>HealthCare+</center></html>");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 52));
        welcomeLabel.setBounds(130, panelHeight / 4, panelWidth / 2 - 60, 300);
        leftPanel.add(welcomeLabel);

        mainPanel.add(leftPanel);

        // Right Form Panel
        int rightStartX = panelWidth / 2 + 30;

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.DARK_GRAY);
        titleLabel.setBounds(rightStartX, 20, 200, 30);
        mainPanel.add(titleLabel);

        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(rightStartX, 80, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        namelabel.setForeground(Color.BLACK);
        mainPanel.add(namelabel);

        textField = new JTextField();
        textField.setBounds(rightStartX, 110, 250, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(new Color(255, 220, 180));
        mainPanel.add(textField);

        JLabel password = new JLabel("Password");
        password.setBounds(rightStartX, 160, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        password.setForeground(Color.BLACK);
        mainPanel.add(password);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(rightStartX, 190, 250, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 220, 180));
        mainPanel.add(jPasswordField);

        // Optional image
//        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
//        Image i1 = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//        JLabel label = new JLabel(new ImageIcon(i1));
//        label.setBounds(rightStartX + 75, 240, 100, 100);
//        mainPanel.add(label);

        // Buttons
        b1 = new JButton("Login");
        b1.setBounds(rightStartX, 360, 120, 30);
        b1.setFont(new Font("Serif", Font.BOLD, 15));
        b1.setBackground(new Color(40, 60, 90));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        mainPanel.add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(rightStartX + 140, 360, 120, 30);
        b2.setFont(new Font("Serif", Font.BOLD, 15));
        b2.setBackground(new Color(120, 30, 30));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        mainPanel.add(b2);

        setVisible(true);
    }

    public static void main(String args[]) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                conn c = new conn();
                String user = textField.getText();
                String pass = jPasswordField.getText();

                String q = "select * from login where ID='" + user + "' and PW = '" + pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);

                if (resultSet.next()) {
                    new Reception();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            System.exit(0);
        }
    }
}
