package hospital.management.sysytem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Department extends JFrame {

    Department(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

         JTable table = new JTable();
        table.setBounds(5,25,690,340);
        table.setBackground(new Color(90,156,163));
        panel.add(table);

        try{
            conn c = new conn();
            String q = "select * from department";
            ResultSet resultSet = c.statement.executeQuery(q);
            //executeupdate for inserting data
            //executeQuery forretriving data
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch(Exception e){
            e.printStackTrace();
        }

        JLabel label = new JLabel("Department");
        label.setBounds(5,10,100,15);
        label.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label);

        JLabel label1 = new JLabel("Phone_No");
        label1.setBounds(350,10,100,15);
        label1.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(label1);


        JButton btn = new JButton("Back");
        btn.setBounds(300,410,130,30);
        btn.setForeground(Color.white);
        btn.setBackground(Color.BLACK);
        panel.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(350,250);
        setVisible(true);


    }
    public static void main(String[] args){
        new Department();
    }
}
