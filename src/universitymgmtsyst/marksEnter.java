package universitymgmtsyst;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class marksEnter extends JFrame implements ActionListener {

    private Choice crollno;
    private JComboBox<String> cbsem;
    private JTextField[] subjectFields, marksFields;
    private JButton submit, cancel;

    public marksEnter() {
        // Frame setup
        setSize(1000, 600);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Enter Student Marks");
        heading.setBounds(50, 0, 500, 50);
        heading.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(heading);

        // Roll number dropdown
        JLabel lblroll = new JLabel("Select Roll Number:");
        lblroll.setBounds(50, 70, 200, 20);
        lblroll.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblroll);

        crollno = new Choice();
        crollno.setBounds(250, 70, 200, 20);
        add(crollno);
        populateRollNumbers();

        crollno.addItemListener(e -> populateSubjects());

        // Semester dropdown
        JLabel lblsemester = new JLabel("Select Semester:");
        lblsemester.setBounds(50, 110, 150, 20);
        lblsemester.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblsemester);

        String[] sem = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth"};
        cbsem = new JComboBox<>(sem);
        cbsem.setBounds(250, 110, 150, 20);
        cbsem.setBackground(Color.WHITE);
        add(cbsem);

        // Labels for subjects and marks
        JLabel lblsub = new JLabel("Subjects:");
        lblsub.setBounds(50, 150, 200, 20);
        lblsub.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblsub);

        JLabel lblmarks = new JLabel("Marks:");
        lblmarks.setBounds(320, 150, 200, 20);
        lblmarks.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblmarks);

        // Text fields for subjects and marks
        subjectFields = new JTextField[6];
        marksFields = new JTextField[6];

        for (int i = 0; i < 6; i++) {
            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(50, 190 + i * 30, 200, 20);
            add(subjectFields[i]);

            marksFields[i] = new JTextField();
            marksFields[i].setBounds(320, 190 + i * 30, 150, 20);
            add(marksFields[i]);
        }

        // Buttons
        submit = new JButton("Submit");
        submit.setBounds(70, 400, 150, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(320, 400, 150, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Image placeholder
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/exam.jpg"));
        Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(550, 50, 400, 300);
        add(image);

        setVisible(true);
    }

    private void populateRollNumbers() {
        try {
            Conn c = new Conn();
        
             ResultSet rs = c.s.executeQuery("SELECT rollno FROM student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching roll numbers: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateSubjects() {
        try {
            String rollNo = crollno.getSelectedItem();
            Conn c = new Conn();
            String query = "SELECT c.subject1, c.subject2, c.subject3, c.subject4, c.subject5, c.subject6 " +
                           "FROM student s JOIN course c ON s.courseId = c.id WHERE s.rollno = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, rollNo);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                for (int i = 0; i < 6; i++) {
                    subjectFields[i].setText(rs.getString("subject" + (i + 1)));
                }
            } else {
                JOptionPane.showMessageDialog(this, "No subjects found for the selected roll number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            ps.close();
            c.c.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching subjects: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            try {
                String rollNo = crollno.getSelectedItem();
                String semester = (String) cbsem.getSelectedItem();
                String[] subjects = new String[6];
                String[] marks = new String[6];

                for (int i = 0; i < 6; i++) {
                    subjects[i] = subjectFields[i].getText();
                    marks[i] = marksFields[i].getText();
                }

                Conn c = new Conn();
                String query1 = "INSERT INTO subject (rollno, semester, subject1, subject2, subject3, subject4, subject5, subject6) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                String query2 = "INSERT INTO marks (rollno, semester, marks1, marks2, marks3, marks4, marks5, marks6) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps1 = c.c.prepareStatement(query1);
                PreparedStatement ps2 = c.c.prepareStatement(query2);

                ps1.setString(1, rollNo);
                ps1.setString(2, semester);
                ps2.setString(1, rollNo);
                ps2.setString(2, semester);

                for (int i = 0; i < 6; i++) {
                   ps1.setString(i + 3, subjects[i]);
                    ps2.setString(i + 3, marks[i]);
                }

               ps1.executeUpdate();
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(this, "Marks Inserted Successfully");

               ps1.close();
                ps2.close();
                c.c.close();
                setVisible(false);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new marksEnter();
    }
}
