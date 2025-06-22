package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RectangularShape;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {
    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1 ,b2;

    Login(){

    // if we want to show some text in frame we use LABEL
       JLabel namelable = new JLabel("Username");
       namelable.setBounds(40,20,100,30);
       namelable.setFont(new Font("Tahoma", Font.BOLD,16));
       namelable.setForeground(Color.black);
       add(namelable);//add namelable to frame

        JLabel password = new JLabel("Password");
        password.setBounds(40,60,100,30);
        password.setFont(new Font("Tahoma", Font.BOLD,16));
        password.setForeground(Color.black);
        add(password);//add namelable to frame

        //textfield  is used for user to enter his name in the
        textField = new JTextField();
        textField.setBounds(150,20,150,30);
        textField.setFont(new Font("Tahoma",Font.PLAIN , 15));
        textField.setBackground(new Color(255,179,0));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150,70,150,30);
        jPasswordField.setFont(new Font("Tahoma",Font.PLAIN,15));
        jPasswordField.setBackground(new Color(255,179,0));
        add(jPasswordField);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(i1);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(320,-30,400,300);
        add(label);

        b1 = new JButton("Login");
        b1.setBounds(40,140,120,30);
        b1.setFont(new Font("Serif",Font.BOLD,15));
        b1.setBackground(Color.black);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180,140,120,30);
        b2.setFont(new Font("Serif",Font.BOLD,15));
        b2.setBackground(Color.black);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(250,140,100));
        setSize(800,300);
        setLocation(400,270);
        setLayout(null);
        setVisible(true);

    }

    public static void main(String args[]){
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    // contains login logic
        if(e.getSource() == b1){
           try{
               conn c = new conn();
               String user = textField.getText();// this line will get the user input and store it in 'user'
               String pass = jPasswordField.getText();// this will contains password entered by the user

               //query
               String q = "select * from login where ID='"+user+"' and PW = '"+pass+"'";
               //query executing
               ResultSet resultSet = c.statement.executeQuery(q);//if the id and password match it will stored on the resultSet

                   if(resultSet.next()){
                       new Reception();
                       setVisible(false);
                   }else{
                       JOptionPane.showMessageDialog(null,"Invalid");
                   }

           }
           catch(Exception E){
               E.printStackTrace();
           }

        }
        //cancel button clicked
        else{
            System.exit(0);
        }


    }
}
