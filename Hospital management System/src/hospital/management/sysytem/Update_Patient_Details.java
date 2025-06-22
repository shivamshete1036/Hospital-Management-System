package hospital.management.sysytem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_Patient_Details extends JFrame {

    Update_Patient_Details(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,940,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/updated.png"));
        Image image = i1.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon i2 =new ImageIcon(image);//contaains scaled image
        JLabel label = new JLabel(i2);
        label.setBounds(500,60,300,300);
        panel.add(label);// this image will add to the panel1


        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124,11,262,25);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Name");
        label2.setBounds(25,88,50,15);
        label2.setFont(new Font("Tahoma",Font.PLAIN,14));
        label2.setForeground(Color.white);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248,85,150,25);
        panel.add(choice);

        conn c = new conn();
        try{
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_Info ");
            while(resultSet.next()){
                choice.add(resultSet.getString("Name"));
            }
        }catch(Exception E){
            E.printStackTrace();
        }

        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(25,129,150,15);
        label3.setFont(new Font("Tahoma",Font.PLAIN,14));
        label3.setForeground(Color.white);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248,129,150,20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In Time  :");
        label4.setBounds(25,174,150,15);
        label4.setFont(new Font("Tahoma",Font.PLAIN,14));
        label4.setForeground(Color.white);
        panel.add(label4);

        JTextField textFieldInTime = new JTextField();
        textFieldInTime.setBounds(248,174,150,20);
        panel.add(textFieldInTime);


        JLabel label5 = new JLabel("Amount Paid (RS)  :");
        label5.setBounds(25,216,150,15);
        label5.setFont(new Font("Tahoma",Font.PLAIN,14));
        label5.setForeground(Color.white);
        panel.add(label5);

        JTextField textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248,216,150,20);
        panel.add(textFieldAmount);


        JLabel label6 = new JLabel("Pending Amount (RS) :");
        label6.setBounds(25,261,150,15);
        label6.setFont(new Font("Tahoma",Font.PLAIN,14));
        label6.setForeground(Color.white);
        panel.add(label6);

        JTextField textFieldPending = new JTextField();
        textFieldPending.setBounds(248,261,150,20);
        panel.add(textFieldPending);

        JButton check = new JButton("CHECK");
        check.setBounds(281,378,89,23);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String q = "select * from Patient_Info where Name= '"+id+"'";
                try{
                    conn c = new conn();
                    ResultSet resultSet =c.statement.executeQuery(q);
                    while(resultSet.next()){
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldInTime.setText(resultSet.getString("Time"));
                        textFieldAmount.setText(resultSet.getString("Deposit"));

                    }

                    ResultSet resultSet1 = c.statement.executeQuery("select * from Room where Room_No = '"+textFieldR.getText()+"'");
                      while(resultSet1.next()){
                        String price = resultSet1.getString("Price");
                        int amountPaid =Integer.parseInt(price)- Integer.parseInt(textFieldAmount.getText());
                        textFieldPending.setText(""+amountPaid);
                      }
                }catch(Exception ee){
                    ee.printStackTrace();
                }
            }
        });

        JButton Update = new JButton("Update");
        Update.setBounds(56,378,89,23);
        Update.setBackground(Color.black);
        Update.setForeground(Color.white);
        panel.add(Update);
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    conn c = new conn();
                    String q = choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldInTime.getText();
                    String amount = textFieldAmount.getText();
                    c.statement.executeUpdate("update Patient_Info set Room_Number = '"+room+"',Time = '"+time+"', Deposit = '"+amount+"' where Name= '"+q+"'");
                    JOptionPane.showMessageDialog(null,"Update SuccesSfully");
                    setVisible(false);
                }catch(Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        JButton Back = new JButton("Back");
        Back.setBounds(168,378,89,23);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.white);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });



        setUndecorated(true);
        setSize(950,500);
        setLayout(null);
        setLocation(400,250);
        setVisible(true);
    }

    public static void main(String[] args){
        new Update_Patient_Details();
    }
}
