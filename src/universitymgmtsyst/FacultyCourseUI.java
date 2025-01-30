
package universitymgmtsyst;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.awt.event.*;
import java.sql.*;

public class FacultyCourseUI extends JFrame implements ActionListener {

    private JTextField tfFaculty, tfCourse,tfsem,tfees;
    private JTextArea taSubject;
    private JButton btnSubmit, btnCancel;
    JComboBox cbsem;
    public FacultyCourseUI() {
        // Frame setup
        setTitle("Add Faculty, Course, and Subject");
        setSize(600, 500);
        setLocation(400, 200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Faculty label and text field
        JLabel lblFaculty = new JLabel("Faculty:");
        lblFaculty.setBounds(50, 50, 100, 25);
        lblFaculty.setFont(new Font("Serif", Font.PLAIN, 18));
        add(lblFaculty);

        tfFaculty = new JTextField();
        tfFaculty.setBounds(150, 50, 250, 25);
        add(tfFaculty);

        // Course label and text field
        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(50, 100, 100, 25);
        lblCourse.setFont(new Font("Serif", Font.PLAIN, 18));
        add(lblCourse);

        tfCourse = new JTextField();
        tfCourse.setBounds(150, 100, 250, 25);
        add(tfCourse);
        
        
       JLabel lblsemester = new JLabel("Semester:");
        lblsemester.setBounds(50, 150, 100, 25);
        lblsemester.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblsemester);

        String[] sem = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth"};
        cbsem = new JComboBox<>(sem);
        cbsem.setBounds(150, 150, 150, 20);
        cbsem.setBackground(Color.WHITE);
        add(cbsem);

        // Subject label and text area
        JLabel lblSubject = new JLabel("Subjects:");
        lblSubject.setBounds(50, 200, 100, 25);
        lblSubject.setFont(new Font("Serif", Font.PLAIN, 18));
        add(lblSubject);

        taSubject = new JTextArea();
        taSubject.setLineWrap(true);
        taSubject.setWrapStyleWord(true);
        JScrollPane spSubject = new JScrollPane(taSubject);
        spSubject.setBounds(150, 200, 250, 100);
        add(spSubject);
        
        
         JLabel lblfee = new JLabel("Fees:");
        lblfee.setBounds(50, 320, 100, 25);
        lblfee.setFont(new Font("Serif", Font.PLAIN, 18));
        add(lblfee);

        tfees = new JTextField();
        tfees.setBounds(150, 320, 250, 25);
        add(tfees);
        

        // Submit button
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(100, 380, 100, 30);
        btnSubmit.setBackground(Color.BLACK);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);
        add(btnSubmit);

        // Cancel button
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(250, 380, 100, 30);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(this);
        add(btnCancel);

        // Apply auto-capitalization to text fields
        applyAutoCapitalize(tfFaculty);
        applyAutoCapitalize(tfCourse);

        setVisible(true);
    }

    private void applyAutoCapitalize(JTextField textField) {
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new UppercaseDocumentFilter());
    }

    static class UppercaseDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null) {
                string = string.toUpperCase();
            }
            super.insertString(fb, offset, string, attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null) {
                text = text.toUpperCase();
            }
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btnSubmit) {
        String faculty = tfFaculty.getText();
        String course = tfCourse.getText();
        String subjects = taSubject.getText();
  String tfees =this.tfees.getText();
        if (faculty.isEmpty() || course.isEmpty() || subjects.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Split subjects by new line and save to database
        String[] subjectArray = subjects.split("\\n");

        // Ensure we only insert a maximum of 10 subjects (adjust according to your requirement)
        if (subjectArray.length > 10) {
            JOptionPane.showMessageDialog(this, "You can only enter up to 10 subjects!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Conn c = new Conn();
            // Prepare the query with placeholders for subject1 to subject10
            String query = "INSERT INTO course (faculty, courses, semester, subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8, subject9, subject10,fees) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";
            PreparedStatement ps = c.c.prepareStatement(query);
            
            ps.setString(1, faculty.trim());
            ps.setString(2, course.trim());
            ps.setString(3, (String) cbsem.getSelectedItem());
            

            // Set the subjects in their respective columns
            for (int i = 0; i < subjectArray.length; i++) {
                ps.setString(i + 4, subjectArray[i].trim()); // subject1 at index 4, subject2 at index 5, ...
            }

            // Fill the remaining subject fields with null if less than 10 subjects are entered
            for (int i = subjectArray.length; i < 10; i++) {
                ps.setString(i + 4, null); // Set the rest to null if fewer than 10 subjects
            }
            
            ps.setString(14, tfees);
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(this, "Course and subjects added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            tfFaculty.setText("");
            tfCourse.setText("");
            taSubject.setText("");
            

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error saving to database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else if (ae.getSource() == btnCancel) {
        setVisible(false);
    }
}
    public static void main(String[] args) {
        new FacultyCourseUI();
    }
}
