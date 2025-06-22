package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Ambulance extends JFrame {

    Ambulance(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,900,490);
        panel.setLayout(null);
        panel.setBackground(new Color(109,164,170));
        panel.setVisible(true);
        add(panel);

        JTable table = new JTable();
        table.setBounds(10,34,800,450);
        table.setBackground(new Color(109,164,170));
        table.setFont(new Font("Tahoma",Font.BOLD,12));
        panel.add(table);

        try{
            conn c = new conn();
            String q = "select * from Ambulance";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label1 = new JLabel("Name");
        label1.setBounds(30,9,70,20);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);

        JLabel label2 = new JLabel("Gender");
        label2.setBounds(220,9,70,20);
        label2.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label2);

        JLabel label3 = new JLabel("Available");
        label3.setBounds(446,9,70,20);
        label3.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label3);

        JLabel label4 = new JLabel("Location");
        label4.setBounds(626,9,70,20);
        label4.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label4);


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
        setSize(900,500);
        setLayout(null);
        setLocation(200,230);
        setVisible(true);
    }
    public static void main(String args[]){
        new Ambulance();
    }
}
