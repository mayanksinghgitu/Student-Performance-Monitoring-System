package Student.Performance.monitoring.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_class extends JFrame {

    Main_class(){

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1120,630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0,0,1120,630);
        add(img);

//        JLabel heading = new JLabel("Welcome To");
//        heading.setBounds(400,120,500,40);
//        heading.setFont(new Font("Releway",Font.BOLD,25));
//        img.add(heading);

        JLabel heading1 = new JLabel("Student Permorfance Monitoring System");
        heading1.setBounds(280,150,500,40);
        heading1.setFont(new Font("Releway",Font.BOLD,25));
        img.add(heading1);

//        JLabel heading2 = new JLabel("©2024 Student Performance Monitoring System");
//        heading2.setBounds(290,500,580,40);
//        heading2.setFont(new Font("Releway",Font.BOLD,25));
//        heading2.setForeground(Color.BLACK);
//        heading2.setBackground(Color.BLACK);
//        img.add(heading2);

        JButton add = new JButton("Add Student");
        add.setBounds(335,270,150,40);
        add.setForeground(Color.BLACK);
        add.setBackground(Color.white);
        add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Addstud();
            }
        });
        img.add(add);
//
        JButton view = new JButton("View Student");
        view.setBounds(565,270,150,40);
        view.setForeground(Color.BLACK);
        view.setBackground(Color.white);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudents();
            }
        });
        img.add(view);
//
        JButton rem = new JButton("Remove Student");
        rem.setBounds(440,370,150,40);
        rem.setForeground(Color.BLACK);
        rem.setBackground(Color.white);
        rem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemoveStud();
            }
        });
       img.add(rem);

        setSize(1120, 630);
        setLocation(250, 100);
        setLayout(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new Main_class();

    }
}

