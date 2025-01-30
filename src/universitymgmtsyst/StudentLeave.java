package universitymgmtsyst;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class StudentLeave extends JFrame implements ActionListener {
    Choice crollno, ctime;
    JDateChooser dcdob;
    JButton submit, cancel;

    public StudentLeave() {
        // Frame properties
        setSize(500, 550);
        setLocation(550, 100);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(heading);

        // Roll Number Label and Choice
        JLabel lblrollno = new JLabel("Search By Roll Number");
        lblrollno.setBounds(60, 100, 200, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lblrollno);

        crollno = new Choice();
        crollno.setBounds(60, 130, 200, 20);
        add(crollno);

        // Populate Roll Numbers
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Date Label and Picker
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 180, 200, 20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lbldate);

        dcdob = new JDateChooser();
        dcdob.setBounds(60, 210, 200, 30);
        add(dcdob);

        // Time/Duration Label and Choice
        JLabel lbltime = new JLabel("Duration");
        lbltime.setBounds(60, 260, 200, 20);
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(lbltime);

        ctime = new Choice();
        ctime.setBounds(60, 300, 200, 20);
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);

        // Submit Button
        submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        // Cancel Button
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            
            String rollno=crollno.getSelectedItem();
            String date=((JTextField)dcdob.getDateEditor().getUiComponent()).getText();
            String duration=ctime.getSelectedItem();
            
            String query="insert into studentleave(rollno,date,duration) values(?,?,?)";
            try {
                Conn c=new Conn();
 PreparedStatement ps = c.c.prepareStatement(query);
 ps.setString(1, rollno);
  ps.setString(2, date);
  
            ps.setString(3, duration);
            int res=ps.executeUpdate();
             if (res > 0) {
                JOptionPane.showMessageDialog(this, "Leave applied successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Error applying leave. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            } catch (Exception e) {
            }
            
            JOptionPane.showMessageDialog(this, "submitted successfully!");
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentLeave();
    }
}
