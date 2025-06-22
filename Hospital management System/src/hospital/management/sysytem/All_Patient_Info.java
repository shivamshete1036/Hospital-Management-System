package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class All_Patient_Info extends JFrame {

    All_Patient_Info(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,1100,590);
        panel.setLayout(null);
        panel.setBackground(new Color(109,164,170));
        panel.setVisible(true);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,34,1100,450);
        table.setBackground(new Color(109,164,170));
        table.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(table);

        try{
            conn c = new conn();
            String q = "select * from Patient_info";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("ID");
        label1.setBounds(30,9,70,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2 = new JLabel("Number");
        label2.setBounds(150,9,70,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3 = new JLabel("Name");
        label3.setBounds(290,9,70,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4 = new JLabel("Gender");
        label4.setBounds(430,9,70,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);

        JLabel label5 = new JLabel("Disesase");
        label5.setBounds(570,9,70,20);
        label5.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label5);

        JLabel label6= new JLabel("Room No");
        label6.setBounds(700,9,70,20);
        label6.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label6);

        JLabel label7= new JLabel("Time");
        label7.setBounds(840,9,70,20);
        label7.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label7);

        JLabel label8= new JLabel("Deposit");
        label8.setBounds(980,9,70,20);
        label8.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label8);

        JButton btn = new JButton("Back");
        btn.setBounds(450,510,120,30);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.white);
        panel.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(1100,600);
        setLayout(null);
        setLocation(200,230);
        setVisible(true);


    }

    public static void main(String [] args){
        new All_Patient_Info();
    }
}
