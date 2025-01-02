package Student.Performance.monitoring.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateStud extends JFrame implements ActionListener {
    JTextField tname, taddm, tclass, tsec, tsubject, tmarks;
    JComboBox Boxsec;

    JButton add,back;
    String number;
    UpdateStud(String number){

        this.number = number;
        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Add Students Details");
        heading.setBounds(320, 30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,25));
        add(heading);

        JLabel name = new JLabel("Name:");
        name.setBounds(50,150,150,30);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(name);

        JLabel tname = new JLabel();
        tname.setBounds(200,150,150,30);
        tname.setBackground(new Color(177,252,197));
        add(tname);

        JLabel fname = new JLabel("Addmission No:");
        fname.setBounds(400,150,150,30);
        fname.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(fname);

        taddm = new JTextField();
        taddm.setBounds(600,150,150,30);
        taddm.setBackground(new Color(177,252,197));
        add(taddm);

        JLabel roll = new JLabel("Class:");
        roll.setBounds(50,250,150,30);
        roll.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(roll);

        tclass = new JTextField();
        tclass.setBounds(200,250,150,30);
        tclass.setBackground(new Color(177,252,197));
        add(tclass);

        JLabel sec = new JLabel("Section:");
        sec.setBounds(400,250,200,30);
        sec.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(sec);

        String items[] = {"SELECT","1","2", "3", "4"};
        Boxsec = new JComboBox<>(items);
        Boxsec.setBackground(new Color(177,252,197));
        Boxsec.setBounds(600,250,150,30);
        add(Boxsec);

        JLabel subject = new JLabel("Subjects:");
        subject.setBounds(50,350,150,30);
        subject.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(subject);

        tsubject = new JTextField();
        tsubject.setBounds(200,350,150,30);
        tsubject.setBackground(new Color(177,252,197));
        add(tsubject);

        JLabel mark = new JLabel("Marks:");
        mark.setBounds(400,350,200,30);
        mark.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(mark);

        tmarks = new JTextField();
        tmarks.setBounds(600,350,150,30);
        tmarks.setBackground(new Color(177,252,197));
        add(tmarks);

//        JLabel email = new JLabel("Email:");
//        email.setBounds(50,300,150,30);
//        email.setFont(new Font("SAN_SERIF",Font.BOLD,20));
//        add(email);
//
//        temail = new JTextField();
//        temail.setBounds(200,300,150,30);
//        temail.setBackground(new Color(177,252,197));
//        add(temail);
//
//        JLabel education = new JLabel("Highest Education:");
//        education.setBounds(400,300,150,30);
//        education.setFont(new Font("SAN_SERIF",Font.BOLD,20));
//        add(education);
//
//        teducation = new JTextField();
//        teducation.setBounds(600,300,150,30);
//        teducation.setBackground(new Color(177,252,197));
//        add(teducation);

//
//        JLabel aadhar = new JLabel("Aadhar Number:");
//        aadhar.setBounds(400,350,150,30);
//        aadhar.setFont(new Font("SAN_SERIF",Font.BOLD,20));
//        add(aadhar);
//
//        JLabel taadhar = new JLabel();
//        taadhar.setBounds(600,350,150,30);
//        taadhar.setBackground(new Color(177,252,197));
//        add(taadhar);
//
//        JLabel empid = new JLabel("Employee ID:");
//        empid.setBounds(50,400,150,30);
//        empid.setFont(new Font("SAN_SERIF",Font.BOLD,20));
//        add(empid);
//
//        tempid = new JLabel();
//        tempid.setBounds(200,400,150,30);
//        tempid.setFont(new Font("SAN_SERIF",Font.BOLD,20));
//        tempid.setForeground(Color.RED);
//        add(tempid);
//
//        JLabel designation = new JLabel("Designation:");
//        designation.setBounds(50,350,150,30);
//        designation.setFont(new Font("SAN_SERIF",Font.BOLD,20));
//        add(designation);
//
//        tdesignation = new JTextField();
//        tdesignation.setBounds(200,350,150,30);
//        tdesignation.setBackground(new Color(177,252,197));
//        add(tdesignation);

        try{
            conn c = new conn();
            String query = "select * from students where addm = '"+number+"'";
            ResultSet resultSet = c.statement.executeQuery(query);
            while (resultSet.next()){
                tname.setText(resultSet.getString("name"));
                taddm.setText(resultSet.getString("fname"));
                tclass.setText(resultSet.getString("dob"));
                tsec.setText(resultSet.getString("address"));
                tsubject.setText(resultSet.getString("salary"));
                tmarks.setText(resultSet.getString("phone"));
//                temail.setText(resultSet.getString("email"));
//                teducation.setText(resultSet.getString("education"));
//                taadhar.setText(resultSet.getString("aadhar"));
//                tempid.setText(resultSet.getString("empid"));
//                tdesignation.setText(resultSet.getString("designation"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        add = new JButton("UPDATE");
        add.setBounds(250,550,150,30);
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(450,550,150,30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);



        setSize(900,700);
        setLayout(null);
        setLocation(300,50);
        setVisible(true);





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add){
            String name = tname.getText();
            String addm = taddm.getText();
            String class1 = tclass.getText();
            String sec = tsec.getText();
            String subject = tsubject.getText();
            String marks = tmarks.getText();
            //String designation = tdesignation.getText();

            try {
                conn c = new conn();
                String query = "update students set fname = '"+name+"', salary = '"+addm+"', address = '"+ class1 +"', phone = '"+sec+"', email = '"+subject+"', education = '"+marks+"'";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                setVisible(false);
                new Main_class();
            }catch (Exception E){
                E.printStackTrace();
            }
        }else {
            setVisible(false);
            new ViewStudents();
        }
    }

    public static void main(String[] args) {
        new UpdateStud("");

    }
}
