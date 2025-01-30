
package universitymgmtsyst;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Login extends JFrame implements ActionListener{
JButton login,Cancel;
JTextField tfusername;
JPasswordField tfpassword;
    public Login() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JLabel lblUsername=new JLabel("Username");
        lblUsername.setBounds(40, 20, 100, 20);
        add(lblUsername);
        
        tfusername=new JTextField();
        tfusername.setBounds(150,20,150,20);
        add(tfusername);
        
         JLabel lblpassword=new JLabel("Password");
        lblpassword.setBounds(40, 60, 100, 20);
        add(lblpassword);
        
         tfpassword=new JPasswordField();
        tfpassword.setBounds(150,60,150,20);
        add(tfpassword);
        
        
        login=new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.setFont(new Font("Tahoma",Font.BOLD,16));
        login.addActionListener(this);
        add(login);
        
         Cancel=new JButton("Cancel");
        Cancel.setBounds(180, 140, 120, 30);
        Cancel.setBackground(Color.black);
        Cancel.setForeground(Color.white);
        Cancel.setFont(new Font("Tahoma",Font.BOLD,16));
        Cancel.addActionListener(this);
        add(Cancel);
        
        
           ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2=i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        
        image.setBounds(350, 0, 200, 200);
        add(image);
        
        setSize(600,300);
        setLocation(500,250);
        setResizable(false);
        setVisible(true);
    }
    
   public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == login) {
        String username = tfusername.getText();
        String password = new String(tfpassword.getPassword());

        // Use a parameterized query for PreparedStatement
        String query = "SELECT * FROM login WHERE username = ? AND password = ?";
        
        try {
            Conn c = new Conn(); // Initialize your custom connection class
            PreparedStatement ps = c.c.prepareStatement(query); // Use PreparedStatement from Conn.c
            
            // Set parameters safely
            ps.setString(1, username);
            ps.setString(2, password);
            
            // Execute the query
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                setVisible(false);
                new Project(); // Assuming this opens the next window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
            }
            
            // Close resources
            rs.close();
            ps.close();
            c.c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else if (ae.getSource() == Cancel) {
        setVisible(false);
    }
}
    public static void main(String[] args) {
        new Login();
    }
    
}
