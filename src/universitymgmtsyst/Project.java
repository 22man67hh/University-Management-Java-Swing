
package universitymgmtsyst;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Project extends JFrame implements ActionListener{

    public Project() {
        setSize(1540,840);
        
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2=i1.getImage().getScaledInstance(1500, 750, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        add(image);
        
        JMenuBar mb= new JMenuBar();
        
        
        JMenu newInfo=new JMenu("New Information");
        newInfo.setForeground(Color.BLUE);
        mb.add(newInfo);
        
        JMenuItem facultyInfo=new JMenuItem("New Faculty Information");
        facultyInfo.setBackground(Color.white);
        facultyInfo.addActionListener(this);
        newInfo.add(facultyInfo);
        
        
        JMenuItem studentInfo=new JMenuItem("New Student Information");
        studentInfo.setBackground(Color.white);
        studentInfo.addActionListener(this);
        newInfo.add(studentInfo);
        
        
        
        
        JMenu details=new JMenu("View Details");
        details.setForeground(Color.red);
        mb.add(details);
        
        JMenuItem facultydetails=new JMenuItem("View Faculty Information");
        facultydetails.setBackground(Color.white);
        facultydetails.addActionListener(this);
        details.add(facultydetails);
        
        
        JMenuItem studentdetails=new JMenuItem("View Student Information");
        studentdetails.setBackground(Color.white);
        studentdetails.addActionListener(this);
        details.add(studentdetails);
        
        
        //leave
           
        JMenu leave=new JMenu("Apply Leave");
        leave.setForeground(Color.blue);
        mb.add(leave);
        
        JMenuItem facultyleave=new JMenuItem("Faculty Leave");
        facultyleave.setBackground(Color.white);
         facultyleave.addActionListener(this);
        leave.add(facultyleave);
        
        
        JMenuItem studentleave=new JMenuItem("Student Leave");
        studentleave.setBackground(Color.white);
          studentleave.addActionListener(this);

        leave.add(studentleave);
        
        
        
         //leave details
           
        JMenu leaveDetails=new JMenu("Leave Details");
        leaveDetails.setForeground(Color.red);
        mb.add(leaveDetails);
        
        JMenuItem facultyleavedetails=new JMenuItem("Faculty Leave Details");
        facultyleavedetails.setBackground(Color.white);
        facultyleavedetails.addActionListener(this);
        leaveDetails.add(facultyleavedetails);
        
        
        JMenuItem studentleavedetails=new JMenuItem("Student Leave Details");
        studentleavedetails.setBackground(Color.white);
        studentleavedetails.addActionListener(this);
        leaveDetails.add(studentleavedetails);
        
        
        
           
         //exam
           
        JMenu exam=new JMenu("Examination");
        exam.setForeground(Color.blue);
        mb.add(exam);
        
        JMenuItem examdetails=new JMenuItem("Examination Result");
        examdetails.setBackground(Color.white);
        exam.add(examdetails);
        
        
        JMenuItem entermarks=new JMenuItem("Enter marks");
        studentleavedetails.setBackground(Color.white);
        entermarks.addActionListener(this);
        exam.add(entermarks);
        
        
        
        
            //update info
           
        JMenu updateinfo=new JMenu("Upate details");
        updateinfo.setForeground(Color.red);
        mb.add(updateinfo);
        
        JMenuItem updatefacinfo=new JMenuItem("Update Faculty Details");
        updatefacinfo.setBackground(Color.white);
        updateinfo.add(updatefacinfo);
        
        
        JMenuItem updatestdinfo=new JMenuItem("Update Student Details");
        updatestdinfo.setBackground(Color.white);
        updateinfo.add(updatestdinfo);
        
        
              //fee info
           
        JMenu fee=new JMenu("Fee details");
        fee.setForeground(Color.blue);
        mb.add(fee);
        
        JMenuItem feeStructure=new JMenuItem("Faculty Fee Structure");
        feeStructure.setBackground(Color.white);
        fee.add(feeStructure);
        
        
        JMenuItem feeForm=new JMenuItem("Student Fee Form");
        feeForm.setBackground(Color.white);
        fee.add(updatestdinfo);
        
        
        
        
                   //utility
           
        JMenu utility=new JMenu("Utility");
        utility.setForeground(Color.red);
        mb.add(utility);
        
        JMenuItem notepad=new JMenuItem("Notepad");
        notepad.setBackground(Color.white);
        notepad.addActionListener(this);
        utility.add(notepad);
        
        
        JMenuItem calc=new JMenuItem("Calculator");
        calc.setBackground(Color.white);
        calc.addActionListener(this);
        utility.add(calc);
        
        
              //exit
           
        JMenu exit=new JMenu("Exit");
        exit.setForeground(Color.blue);
        mb.add(exit);
        
        JMenuItem ex=new JMenuItem("Exit");
        notepad.setBackground(Color.white);
        ex.addActionListener(this);
        exit.add(ex);
        
       
        
        
        setJMenuBar(mb);
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae){
    String msg=ae.getActionCommand();
    if(msg.equals("Exit")){
        setVisible(false);
    } else if(msg.equalsIgnoreCase("Calculator")){
        
        try {
            Runtime.getRuntime().exec("calc.exe");
            
        } catch (Exception e) {
            
        }

    }else if(msg.equalsIgnoreCase("notepad")){
        
        try {
            Runtime.getRuntime().exec("notepad.exe");
            
        } catch (Exception e) {
            
        }

    }else if(msg.equalsIgnoreCase("New Faculty Information")){
        new AddTeacher();
    }else if(msg.equalsIgnoreCase("New Student Information")){
        new AddStudent();
    }else if(msg.equalsIgnoreCase("View Faculty Information")){
        new teacherDetails();
    }else if(msg.equalsIgnoreCase("View Student Information")){
        new StudentDetail();
    }else if(msg.equalsIgnoreCase("Faculty Leave")){
        new TeacherLeave();
    }else if(msg.equalsIgnoreCase("Student Leave")){
        new StudentLeave();
    }else if(msg.equalsIgnoreCase("Faculty Leave Details")){
        new TeacherLeaveDetails();
    }else if(msg.equalsIgnoreCase("Student Leave Details")){
        new StudentLeaveDetails();
    }else if(msg.equalsIgnoreCase("Update Faculty Details")){
        new UpdateTeacher();
    }else if(msg.equalsIgnoreCase("Student Faculty Details")){
        new UpdateStudent();
    }else if(msg.equalsIgnoreCase("Enter marks")){
        new marksEnter();
    }else if(msg.equalsIgnoreCase("Examination Result")){
        new marksEnter();
    }else if(msg.equalsIgnoreCase("Enter marks")){
        new ExamResult();
    }else if(msg.equalsIgnoreCase("Student Fee Form")){
        new StudentFormFees();
    }else if(msg.equalsIgnoreCase("Faculty Fee Structure")){
        new StudentFormFees();
    }
    
   
    }
    
    public static void main(String[] args) {
        new Project();
    }
}
