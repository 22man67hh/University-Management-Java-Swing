
package universitymgmtsyst;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
public class AddTeacher  extends JFrame implements ActionListener {
     JTextField tfname, tffname, tfaddress, tfphone, temail, tfx, txii, tcn;
    JComboBox<String> cbcourse, Cfac;
    JButton submit, cancel;
    JDateChooser dcdob;
    JLabel lblrollnn;
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    public AddTeacher(){
         setSize(950, 750);
        setLayout(null);
        setTitle("Add New Teacher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("New Teacher");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(180, 150, 150, 30);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name:");
        lblfname.setBounds(400, 150, 150, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 170, 30);
        add(tffname);

        JLabel lblrollno = new JLabel("Id No:");
        lblrollno.setBounds(50, 210, 150, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);

        lblrollnn = new JLabel("2081-" + first4);
        lblrollnn.setBounds(180, 210, 150, 30);
        lblrollnn.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollnn);

        JLabel lbldob = new JLabel("Date of Birth:");
        lbldob.setBounds(400, 210, 150, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(600, 210, 150, 30);
        add(dcdob);

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(50, 280, 150, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(180, 280, 170, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(400, 280, 150, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 280, 170, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email Id:");
        lblemail.setBounds(50, 350, 150, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        temail = new JTextField();
        temail.setBounds(180, 350, 170, 30);
        add(temail);

        JLabel lblx = new JLabel("Class X (%):");
        lblx.setBounds(400, 350, 150, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);

        tfx = new JTextField();
        tfx.setBounds(600, 350, 170, 30);
        add(tfx);

        JLabel lblxii = new JLabel("Class XII (%):");
        lblxii.setBounds(50, 420, 150, 30);
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);

        txii = new JTextField();
        txii.setBounds(180, 420, 170, 30);
        add(txii);

        JLabel lblcn = new JLabel("Citizenship No:");
        lblcn.setBounds(400, 420, 150, 30);
        lblcn.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcn);

        tcn = new JTextField();
        tcn.setBounds(600, 420, 170, 30);
        add(tcn);

        JLabel lblcourse = new JLabel("Qualification:");
        lblcourse.setBounds(50, 480, 150, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);

        String[] courses = {"BCA", "BBA", "BA", "MCA", "MA", "BSC", "MSC", "MIT", "BIT"};
        cbcourse = new JComboBox<>(courses);
        cbcourse.setBounds(180, 480, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);

        JLabel lblfac = new JLabel("Department:");
        lblfac.setBounds(400, 480, 150, 30);
        lblfac.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfac);

        String[] faculties = {"Humanities", "Management", "Science"};
        Cfac = new JComboBox<>(faculties);
        Cfac.setBounds(600, 480, 150, 30);
        Cfac.setBackground(Color.WHITE);
        add(Cfac);

        submit = new JButton("Submit");
        submit.setBounds(250, 600, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 600, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
        setLocation(80, 50);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String rollno = lblrollnn.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = temail.getText();
            String x = tfx.getText();
            String xii = txii.getText();
            String citizenno = tcn.getText();
            String course = (String) cbcourse.getSelectedItem();
            String branch = (String) Cfac.getSelectedItem();

            try {
                String query = "INSERT INTO teacher (name, fname, empId, dob, address, phone, email, class_x, class_xii, citizenno, education, department) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                Conn con = new Conn();
                PreparedStatement ps = con.c.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, fname);
                ps.setString(3, rollno);
                ps.setString(4, dob);
                ps.setString(5, address);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.setString(8, x);
                ps.setString(9, xii);
                ps.setString(10, citizenno);
                ps.setString(11, course);
                ps.setString(12, branch);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Teacher Details Inserted Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddStudent();
    }
    }

    
    
