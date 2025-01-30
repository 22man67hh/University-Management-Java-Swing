
package universitymgmtsyst;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class StudentFormFees extends JFrame implements ActionListener {
Choice crollno;JTextField search; JLabel labelname, labelfname,labelcname,labelsemester,labelfee;
JButton submit,pay,unpay;
    public StudentFormFees() throws HeadlessException {
        
        setSize(900,600);
        setLocation(300,100);
        setLayout(null);
        setTitle("Student Fees ");
        getContentPane().setBackground(Color.white);
        
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/fee.jpg"));
        Image i2=i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(400, 50, 450, 300);
        add(image);
        
        
          JLabel lblroll=new JLabel("Select Roll No:");
        lblroll.setBounds(50,80,200,20);
        lblroll.setFont(new Font("serif", Font.PLAIN, 20));

        add(lblroll);
        
          search = new JTextField();
        search.setBounds(180, 80, 180, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        // Submit Button
        submit = new JButton("Result");
        submit.setBounds(50, 15, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
        
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

       labelname = new JLabel();
        labelname.setBounds(180, 150, 150, 30);
                labelname.setFont(new Font("Tahoma", Font.PLAIN, 17));

        add(labelname);

        JLabel lblfname = new JLabel("Father's Name:");
        lblfname.setBounds(50, 200, 150, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

        
      labelfname = new JLabel();
          labelfname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelfname.setBounds(210, 200, 170, 30);
        add(labelfname);
        
        
        JLabel lblc= new JLabel("Course Name:");
        lblc.setBounds(50, 250, 150, 30);
        lblc.setFont(new Font("serif", Font.BOLD, 20));
        add(lblc);

        
      labelcname = new JLabel();
          labelcname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelcname.setBounds(210, 250, 170, 30);
        add(labelcname);
        
         JLabel lblsem= new JLabel("Semester Name:");
        lblsem.setBounds(50, 300, 150, 30);
        lblsem.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsem);

        
      labelsemester = new JLabel();
          labelsemester.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelsemester.setBounds(210, 300, 170, 30);
        add(labelsemester);
        
        
          JLabel lblfee= new JLabel("Semester Fees:Rs.");
        lblfee.setBounds(50, 350, 200, 30);
        lblfee.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfee);

        
      labelfee = new JLabel();
          labelfee.setFont(new Font("Tahoma", Font.PLAIN, 17));
        labelfee.setBounds(210, 350, 170, 30);
        add(labelfee);
        
        
        
        
        
        pay = new JButton("Pay");
        pay.setBounds(250, 450, 120, 30);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        unpay = new JButton("Unpaid");
        unpay.setBounds(450, 450, 120, 30);
        unpay.setBackground(Color.BLACK);
        unpay.setForeground(Color.WHITE);
        unpay.addActionListener(this);
        add(unpay);
        
        
        setVisible(true);
    }
    
     private void searchResults(String rollno) {
        try {
            Conn c = new Conn();
            String query = "SELECT s.rollno,s.name,s.fname,s.semester,s.pay,c.fees,c.courses from student s join course c ON s.courseId=id where s.rollno LIKE ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, "%" + rollno + "%");
            ResultSet rs = ps.executeQuery();
              if (rs.next()) { // Move cursor to the first row
                labelname.setText(rs.getString("name"));
                labelfname.setText(rs.getString("fname"));
                 labelcname.setText(rs.getString("courses"));
                  labelsemester.setText(rs.getString("courses"));
                  labelfee.setText(rs.getString("fees"));
                 boolean paid=rs.getBoolean("pay");
                 if(paid){
                     pay.setText("Paid");
                 }else{
                     pay.setText("Pay Now");
                 }
            } else {
                JOptionPane.showMessageDialog(this, "No records found for Roll No: " + rollno, "No Results", JOptionPane.INFORMATION_MESSAGE);
                labelname.setText("");
                labelfname.setText("");
                 labelcname.setText("");
                  labelsemester.setText("");
                    labelfee.setText("");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void actionPerformed(ActionEvent ae){
        String msg=ae.getActionCommand();
        if(ae.getSource()==submit){
             String rollno = search.getText().trim();
             if (rollno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a roll number to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                searchResults(rollno);
                
                
            }
        }
        if(msg.equalsIgnoreCase("Pay Now")){
            try{
            Conn con= new Conn();
            String query="Update student set pay =? where rollno=?";
PreparedStatement ps = con.c.prepareStatement(query);

ps.setBoolean(1, true);
ps.setString(2, search.getText());
 ps.executeUpdate();
pay.setText("Paid");
JOptionPane.showMessageDialog(null,"Fee paid succesfully");
    

            
            }catch(Exception aye){
               aye.printStackTrace();
        }    
        }
        if(msg.equalsIgnoreCase("Paid")){
           JOptionPane.showMessageDialog(null,"NO Due");
     
        }
        
    }
    
    public static void main(String[] args) {
       new StudentFormFees();
    }
}
