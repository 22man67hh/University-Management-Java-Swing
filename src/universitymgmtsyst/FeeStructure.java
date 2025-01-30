package universitymgmtsyst;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.util.List;
import java.util.Arrays;
public class FeeStructure extends JFrame implements ItemListener {
    JTable table;
    Choice crollno;

    public FeeStructure() {
        // Frame settings
        setSize(1000, 700);
        setLocation(250, 50);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        // Dropdown for courses
        JLabel lblroll = new JLabel("Select Course:");
        lblroll.setBounds(50, 50, 150, 20);
        lblroll.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblroll);

        crollno = new Choice();
        crollno.setBounds(200, 50, 150, 20);
        crollno.addItemListener(this); // Add item listener for dropdown
        add(crollno);

        // Populate courses into dropdown
        populateCourses();

        // Table for fee structure
        table = new JTable();
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 100, 900, 500);
        add(jsp);

        // Initialize table with data for the first course
        if (crollno.getItemCount() > 0) {
            loadTableData(crollno.getItem(0));
        }
        
      try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT id,faculty,courses,semester,fees from course");
             table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching courses: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setVisible(true);
    }

    // Populate courses into the dropdown
    private void populateCourses() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM course");
            while (rs.next()) {
                crollno.add(rs.getString("courses"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching courses: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Load fee structure data into the table based on the selected course
    private void loadTableData(String course) {
        try {
            Conn c = new Conn();
            String query = "SELECT id,faculty,courses,semester,fees FROM course WHERE courses = ?";
            PreparedStatement stmt = c.c.prepareStatement(query);
            stmt.setString(1, course);
            ResultSet rs = stmt.executeQuery();
            List<String> columns=Arrays.asList("id,faculty,courses,semester,fees");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching fee structure: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle course selection change
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == crollno) {
            loadTableData(crollno.getSelectedItem());
        }
    }

    public static void main(String[] args) {
        new FeeStructure();
    }
}
