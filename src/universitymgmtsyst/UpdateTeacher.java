package universitymgmtsyst;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class UpdateTeacher extends JFrame implements ActionListener {
    JTextField tfname, tffname, tfaddress, tfphone, temail, tfx, txii, tcn,tfcourse,tfac;
    
    JButton submit,addTeacher, cancel;
    JLabel lblrollnn;
  Choice crollno;
    public UpdateTeacher() {
        setSize(950, 700);
        setLayout(null);
        setTitle("Update Teachert");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading = new JLabel("Update Teacher");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

         JLabel lblroll=new JLabel("Select  Emp ID");
        lblroll.setBounds(50,100,200,20);
        heading.setFont(new Font("serif", Font.PLAIN, 30));

        add(lblroll);
        
        crollno=new Choice();
        crollno.setBounds(250,100,200,20);
        add(crollno);
          boolean isEmpIdPresent = false;
        try {
           
            
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from teacher");
            while(rs.next()){
                crollno.add(rs.getString("empId"));
                 isEmpIdPresent = true;
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        
       if(isEmpIdPresent){ 
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        JLabel labelname = new JLabel();
        labelname.setBounds(180, 150, 150, 30);
                labelname.setFont(new Font("Tahoma", Font.PLAIN, 17));

        add(labelname);

        JLabel lblfname = new JLabel("Father's Name:");
        lblfname.setBounds(400, 150, 150, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

         JLabel labelfname = new JLabel();
          labelfname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelfname.setBounds(600, 150, 170, 30);
        add(labelfname);

        JLabel lblrollno = new JLabel("Roll No:");
        lblrollno.setBounds(50, 210, 150, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);

        lblrollnn = new JLabel();
         lblrollnn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblrollnn.setBounds(180, 210, 150, 30);
        lblrollnn.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollnn);

        JLabel lbldob = new JLabel("Date of Birth:");
        lbldob.setBounds(400, 210, 150, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);

           JLabel lbldoc = new JLabel();
           lbldoc.setFont(new Font("serif", Font.PLAIN, 20));

        lbldoc.setBounds(600, 210, 150, 30);
        lbldoc.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoc);

       

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(50, 280, 150, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);

        tfaddress = new JTextField();
     tfaddress.setFont(new Font("serif", Font.PLAIN, 20));

        tfaddress.setBounds(180, 280, 170, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(400, 280, 150, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setFont(new Font("serif", Font.PLAIN, 20));
        tfphone.setBounds(600, 280, 170, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email Id:");
        lblemail.setBounds(50, 350, 150, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        temail = new JTextField();
        temail.setFont(new Font("serif", Font.PLAIN, 20));
        temail.setBounds(180, 350, 170, 30);
        add(temail);

        JLabel lblx = new JLabel("Class X (%):");
        lblx.setBounds(400, 350, 150, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);

        JLabel lbl10= new JLabel();
        lbl10.setFont(new Font("serif", Font.PLAIN, 20));
        lbl10.setBounds(600, 350, 170, 30);
        add(lbl10);

        JLabel lblxii = new JLabel("Class XII (%):");
        lblxii.setBounds(50, 420, 150, 30);
        
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);

       JLabel lbl12= new JLabel();
      lbl12.setFont(new Font("serif", Font.PLAIN, 20));
        lbl12.setBounds(180, 420, 170, 30);
        add(lbl12);

        JLabel lblcn = new JLabel("Citizenship No:");
        lblcn.setBounds(400, 420, 150, 30);
        lblcn.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcn);

         JLabel lblcitizen = new JLabel("Citizenship No:");
         lblcitizen.setFont(new Font("serif", Font.PLAIN, 20));
        lblcitizen.setBounds(600, 420, 170, 30);
        add(lblcitizen);

        JLabel lblcourse = new JLabel("Course:");
        lblcourse.setBounds(50, 480, 150, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);

       tfcourse = new JTextField();
       tfcourse.setFont(new Font("serif", Font.PLAIN, 20));
        tfcourse.setBounds(180, 480, 150, 30);
        add(tfcourse);

        JLabel lblfac = new JLabel("Faculty:");
        lblfac.setBounds(400, 480, 150, 30);
        
        lblfac.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfac);

        tfac = new JTextField();
        tfac.setFont(new Font("serif", Font.PLAIN, 20));
        tfac.setBounds(600, 480, 150, 30);
        add(tfac);

        
        try {
            Conn c= new Conn();
            String query="select * from teacher where empId='"+crollno.getSelectedItem()+"'";
            ResultSet rs=c.s.executeQuery(query);
            while(rs.next()){
                labelname.setText(rs.getString("name"));
                labelfname.setText(rs.getString("fname"));
                lblrollnn.setText(rs.getString("rollno"));
                lbldoc.setText(rs.getString("dob"));
                 tfaddress.setText(rs.getString("address"));
                  tfphone.setText(rs.getString("phone"));
                   temail.setText(rs.getString("email"));
                   lbl10.setText(rs.getString("class_x"));
                   lbl12.setText(rs.getString("class_xii"));
                   lblcitizen.setText(rs.getString("citizenno"));
                   tfcourse.setText(rs.getString("education"));
                   tfac.setText(rs.getString("branch"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        
   crollno.addItemListener(new ItemListener(){


      public void itemStateChanged(ItemEvent e){
              try {
            Conn c= new Conn();
            String query="select * from student where rollno='"+crollno.getSelectedItem()+"'";
            ResultSet rs=c.s.executeQuery(query);
            while(rs.next()){
                labelname.setText(rs.getString("name"));
                labelfname.setText(rs.getString("fname"));
                lblrollnn.setText(rs.getString("rollno"));
                lbldoc.setText(rs.getString("dob"));
                 tfaddress.setText(rs.getString("address"));
                  tfphone.setText(rs.getString("phone"));
                   temail.setText(rs.getString("email"));
                   lbl10.setText(rs.getString("class_x"));
                   lbl12.setText(rs.getString("class_xii"));
                   lblcitizen.setText(rs.getString("citizenno"));
                  tfcourse.setText(rs.getString("education"));
                   tfac.setText(rs.getString("branch"));
            }
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        
        
      }});
            
   
   
        submit = new JButton("Update");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true);
        setLocation(80, 50);
    
    }else{
    
      addTeacher = new JButton("Add Teacher");
            addTeacher.setBounds(250, 550, 120, 30);
            addTeacher.setBackground(Color.BLACK);
            addTeacher.setForeground(Color.WHITE);
            addTeacher.addActionListener(e -> {
                setVisible(false);
                new AddTeacher(); // Open AddTeacher form
            });
            add(addTeacher);
        }

        setVisible(true);
        setLocation(80, 50);
    }

    


    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            
            String rollno = lblrollnn.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = temail.getText();
           
            String course = tfcourse.getText();
            String branch = tfac.getText();

            try {
 String query = "UPDATE teacher SET address = ?, phone = ?, email = ?, education = ?, branch = ? WHERE empId = ?";                Conn con = new Conn();
                PreparedStatement ps = con.c.prepareStatement(query);
   
            
                ps.setString(1, address);
                ps.setString(2, phone);
                ps.setString(3, email);
            
                ps.setString(4, course);
                ps.setString(5, branch);
                ps.setString(6, rollno);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Teacher Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateTeacher();
    }
}
