
package universitymgmtsyst;
import java.sql.*;
import java.awt.Choice;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class teacherDetails extends JFrame implements ActionListener {

    
    Choice crollno;
    JTable table;
    JButton search,print,update,add,cancel;
    public teacherDetails() throws HeadlessException {
        
        getContentPane().setBackground(Color.white);
        setLayout(null);
        JLabel heading=new JLabel("Search By Emp id");
        heading.setBounds(20,20,150,20);
        add(heading);
        
        crollno=new Choice();
        crollno.setBounds(180,20,150,20);
        add(crollno);
        try {
           
            
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from teacher");
            while(rs.next()){
                crollno.add(rs.getString("empId"));
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
        table= new JTable();
          try {
           
            
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from teacher");
          table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        JScrollPane jsp= new JScrollPane(table);
        jsp.setBounds(5,100,900,590);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);
        
          
        print = new JButton("print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);
        
          
        update = new JButton("update");
        update.setBounds(220,70,80,20);
        update.addActionListener(this);
        add(update);
          
        add = new JButton("add");
        add.setBounds(340,70,80,20);
        add.addActionListener(this);
        add(add);
          
        cancel = new JButton("cancel");
        cancel.setBounds(450,70,80,20);
        cancel.addActionListener(this);
        add(cancel);
        
        setSize(900,750);
        setLocation(300,100);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
    
    if(ae.getSource()==search){
        
        String query ="select * from teacher where empId='"+crollno.getSelectedItem()+"'";
        
        try {
            Conn c= new Conn();
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }else if(ae.getSource()==print){
        
        try {
          table.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }else if(ae.getSource()==update){

        setVisible(false);
    }else if(ae.getSource()==add){
        setVisible(false);
        new AddTeacher();

}else if(ae.getSource()==cancel){
    setVisible(false);
}

    
    }
    
    
    public static void main(String[] args) {
        new teacherDetails();
    }
    
}
