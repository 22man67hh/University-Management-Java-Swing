package universitymgmtsyst;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ExamResult extends JFrame implements ActionListener {

    private JTextField search;
    private JButton submit, cancel,print;
    private JTable table;

    public ExamResult() {
        // Frame setup
        setTitle("Exam Results");
        setSize(1000, 475);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Check Result");
        heading.setBounds(50, 15, 400, 50);
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        add(heading);

        // Search Field
        search = new JTextField();
        search.setBounds(80, 90, 200, 30);
        search.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(search);

        // Submit Button
        submit = new JButton("Result");
        submit.setBounds(300, 90, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);

        // Cancel Button
        cancel = new JButton("Back");
        cancel.setBounds(440, 90, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        // Table for displaying results
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 140, 950, 280);
        add(jsp);

        // Populate table with all students initially
        populateTable();

        setVisible(true);
        setResizable(false);
    }

    private void populateTable() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
         table.addMouseListener(new MouseAdapter(){
        
             public void mouseClicked(MouseEvent me){
                 int row=table.getSelectedRow();
                 search.setText(table.getModel().getValueAt(row, 2).toString());
             }
    });
    }

   
    private void searchResults(String rollno) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM student WHERE rollno LIKE ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, "%" + rollno + "%");
            ResultSet rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error searching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String rollno = search.getText().trim();
            if (rollno.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a roll number to search.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                searchResults(rollno);
                new Marks(rollno);
                setVisible(false);
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false); // Close the current window
        }
    }

    public static void main(String[] args) {
        new ExamResult();
    }
}
