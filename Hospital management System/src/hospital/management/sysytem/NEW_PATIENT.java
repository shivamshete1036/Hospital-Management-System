package hospital.management.sysytem;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {

    JComboBox comboBox;
    //drop-down menu from which the user can select one item.

    JTextField textFieldNumber , textName , textFieldDisease , textFieldDeposit;
    //single line text input field, Getting input from the user
    JRadioButton r1 , r2;
    Choice c1;
    //dropdown list that lets the user choose one item from multiple options.
    JLabel date;
    //Used to display a short string or an image icon.

    JButton b1 , b2;
   // A clickable button that performs an action when clicked.


    NEW_PATIENT(){
        //JPanel in Java (Swing)
        //A JPanel is a generic lightweight container in Java Swing
        // used to group components together and manage layout more easily
        // within a window (JFrame). It doesn’t have its own window
        // or borders but can hold other components
        // like JButton, JTextField, JLabel, etc.

        JPanel panel = new JPanel();
        panel.setBounds(5,5,840,540);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/patient.png"));
        Image image = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(550,150,200,200);
        panel.add(label);

        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(118,11,260,53);
        labelName.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(labelName);

        JLabel labelID = new JLabel("ID");
        labelID.setBounds(35,76,200,14);
        labelID.setFont(new Font("Tahoma",Font.BOLD,14));
        labelID.setForeground(Color.white);
        panel.add(labelID);

//      for dropdown
       comboBox = new JComboBox(new String[] {"Aadhar Card" , "Voter ID" ,"Driving License"});
       comboBox.setBounds(271,73,150,20);
       comboBox.setBackground(new Color(3,45,48));
       comboBox.setForeground(Color.WHITE);
       comboBox.setFont(new Font("Tahoma",Font.BOLD,14));
       panel.add(comboBox);


        JLabel labelNumber = new JLabel("Number :");
        labelNumber.setBounds(35,111,200,14);
        labelNumber.setFont(new Font("Tahoma",Font.BOLD,14));
        labelNumber.setForeground(Color.white);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271,111,150,20);
        panel.add(textFieldNumber);


        JLabel labelName1= new JLabel("Name :");
        labelName1.setBounds(35,151,200,14);
        labelName1.setFont(new Font("Tahoma",Font.BOLD,14));
        labelName1.setForeground(Color.white);
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(271,151,150,20);
        panel.add(textName);

        JLabel labelGender= new JLabel("Gender :");
        labelGender.setBounds(35,191,200,14);
        labelGender.setFont(new Font("Tahoma",Font.BOLD,14));
        labelGender.setForeground(Color.white);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma",Font.BOLD,14));
        r1.setForeground(Color.white);
        r1.setBackground( new Color(109,164,170));
        r1.setBounds(271,191,80,15);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2 .setFont(new Font("Tahoma",Font.BOLD,14));
        r2 .setForeground(Color.white);
        r2 .setBackground( new Color(109,164,170));
        r2 .setBounds(350,191,80,15);
         panel.add(r2 );


        JLabel labelDisease= new JLabel("Disease :");
        labelDisease.setBounds(35,231,200,14);
        labelDisease.setFont(new Font("Tahoma",Font.BOLD,14));
        labelDisease.setForeground(Color.white);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(271,231,150,20);
        panel.add(textFieldDisease);


        JLabel labelRoom= new JLabel("Room :");
        labelRoom.setBounds(35,274,200,14);
        labelRoom.setFont(new Font("Tahoma",Font.BOLD,14));
        labelRoom.setForeground(Color.white);
        panel.add(labelRoom);

        c1  = new Choice();
        try{
            conn c = new conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Room");
            while(resultSet.next()){
                c1.add(resultSet.getString("Room_No"));// c1 store the data from our database. c1 is for choic so user will be able to choose the room no
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        c1.setBounds(271,274,150,20);
        c1.setFont(new Font("Tahoma",Font.BOLD,14));
        c1.setForeground(Color.white);
        c1.setBackground(new Color(3,45,48));
        panel.add(c1);
        // Room choice//

        JLabel labelDate= new JLabel("Time:");
        labelDate.setBounds(35,311,200,14);
        labelDate.setFont(new Font("Tahoma",Font.BOLD,14));
        labelDate.setForeground(Color.white);
        panel.add(labelDate);

        Date date1 = new Date();

        date = new JLabel(""+date1);
        date.setBounds(271,311,250,14);
        date.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(date);


        JLabel labelDeposit= new JLabel("Deposit :");
        labelDeposit.setBounds(35,359,200,17);
        labelDeposit.setFont(new Font("Tahoma",Font.BOLD,14));
        labelDeposit.setForeground(Color.white);
        panel.add(labelDeposit);

        textFieldDeposit = new JTextField();
        textFieldDeposit.setBounds(271,359,150,20);
        panel.add(textFieldDeposit);

        b1 = new JButton("ADD");
        b1.setBounds(100,430, 120,30);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.black);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("BACK ");
        b2.setBounds(260,430, 120,30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.black);
        b2.addActionListener(this);

        panel.add(b2);






        //initializing the frameL
        setUndecorated(true);
        setSize(850,550);
        setLayout(null);
        setLocation(300,250);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){//b1 button is clicked
            conn c = new conn();
            String radioBTN = null;
            if(r1.isSelected()){
                radioBTN = "Male";
            }
            else if(r2.isSelected()){
                radioBTN = "Female";
            }

            // retrive all the daata and stores in a string variables
            String s1 = (String)comboBox.getSelectedItem(); //comboBox contains identity proof that willl store to the strinng s1
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposit.getText();

            try{
                String q = "insert into Patient_info values ('"+s1+"', '"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"')";
                String q1 = "update Room set Availability = 'Occupied' where Room_No= "+s6;
                c.statement.executeUpdate(q);// we have to insert and extecute the query
                c.statement.executeUpdate(q1);
                JOptionPane.showMessageDialog(null,"Added Successfully");
                setVisible(false);


            }catch(Exception E){
                E.printStackTrace();
            }

        }else{
            setVisible(false);
        }

    }

    public static void main(String args[]){
           new NEW_PATIENT();
    }


}
