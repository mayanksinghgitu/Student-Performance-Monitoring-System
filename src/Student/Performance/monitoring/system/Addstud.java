package Student.Performance.monitoring.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Addstud extends JFrame implements ActionListener {
    JTextField tname, taddm, tclass, tsec, tsubject, tmarks;
    JComboBox Boxsec;
    JButton add, back;


    Addstud() {

        getContentPane().setBackground(new Color(163, 255, 188));

        JLabel heading = new JLabel("Add Students Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 25));
        add(heading);

        JLabel name = new JLabel("Name:");
        name.setBounds(50, 150, 150, 30);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(name);

        tname = new JTextField();
        tname.setBounds(200, 150, 150, 30);
        tname.setBackground(new Color(177, 252, 197));
        add(tname);

        JLabel addm = new JLabel("Addmission No:");
        addm.setBounds(400, 150, 200, 30);
        addm.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(addm);

        taddm = new JTextField();
        taddm.setBounds(600, 150, 150, 30);
        taddm.setBackground(new Color(177, 252, 197));
        add(taddm);

        JLabel roll = new JLabel("Class:");
        roll.setBounds(50, 250, 150, 30);
        roll.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(roll);

        tclass = new JTextField();
        tclass.setBounds(200, 250, 150, 30);
        tclass.setBackground(new Color(177, 252, 197));
        add(tclass);

        JLabel sec = new JLabel("Section:");
        sec.setBounds(400, 250, 200, 30);
        sec.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(sec);

        String items[] = {"SELECT", "1", "2", "3", "4"};
        Boxsec = new JComboBox<>(items);
        Boxsec.setBackground(new Color(177, 252, 197));
        Boxsec.setBounds(600, 250, 150, 30);
        add(Boxsec);

        JLabel subject = new JLabel("Subjects:");
        subject.setBounds(50, 350, 150, 30);
        subject.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(subject);

        tsubject = new JTextField();
        tsubject.setBounds(200, 350, 150, 30);
        tsubject.setBackground(new Color(177, 252, 197));
        add(tsubject);

        JLabel mark = new JLabel("Marks:");
        mark.setBounds(400, 350, 200, 30);
        mark.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(mark);

        tmarks = new JTextField();
        tmarks.setBounds(600, 350, 150, 30);
        tmarks.setBackground(new Color(177, 252, 197));
        add(tmarks);

        add = new JButton("ADD");
        add.setBounds(250, 550, 150, 30);
        add.setBackground(Color.black);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(450, 550, 150, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setSize(900, 700);
        setLayout(null);
        setLocation(300, 50);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String name = tname.getText();
            String addm = taddm.getText();
            String class1 = tclass.getText();
            String sec = (String) Boxsec.getSelectedItem();
            String subject = tsubject.getText();
            String marks = tmarks.getText();

            try {
                conn c = new conn();
                String query = "insert into students values('" + name + "','" + addm + "','" + class1 + "','" + sec + "','" + subject + "','" + marks + "')";
                c.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details Added Successfully");
                setVisible(false);
                new Main_class();

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else {
            setVisible(false);
            new Main_class();
        }
    }

        public static void main (String[] args){
            new Addstud();
        }
    }

