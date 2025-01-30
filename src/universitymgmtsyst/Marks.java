package universitymgmtsyst;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.print.*;

public class Marks extends JFrame implements ActionListener {
    String rollno;
    JLabel shead;
    JButton print;

    Marks(String rollno) {
        this.rollno = rollno;

        // Frame settings
        setTitle("Student Marks");
        setSize(700, 800);
        setLocation(500, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Heading
        JLabel heading = new JLabel("Ratna Rajya Laxmi Campus");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel sheading = new JLabel("Result:-");
        sheading.setBounds(200, 50, 500, 20);
        sheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(sheading);

        shead = new JLabel();
        shead.setBounds(100, 80, 500, 20);
        shead.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(shead);

        JLabel lblroll = new JLabel("Roll No: " + rollno);
        lblroll.setBounds(60, 120, 500, 25);
        lblroll.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblroll);

        // Panel to display subjects and marks
        JPanel marksPanel = new JPanel();
        marksPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Dynamic rows with 2 columns
        marksPanel.setBackground(Color.WHITE);

        // Add scroll pane
        JScrollPane scrollPane = new JScrollPane(marksPanel);
        scrollPane.setBounds(30, 150, 420, 400);
        add(scrollPane);

        // Headers for the panel
        JLabel subjectHeader = new JLabel("Subject");
        subjectHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
        marksPanel.add(subjectHeader);

        JLabel marksHeader = new JLabel("Marks");
        marksHeader.setFont(new Font("Tahoma", Font.BOLD, 16));
        marksPanel.add(marksHeader);

        print = new JButton("Print");
        print.setBounds(60, 550, 120, 30);
        print.setBackground(Color.BLUE);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);

        // Fetch and display data
        try {
            Conn c = new Conn();

            // Fetch subjects
            String subjectQuery = "SELECT * FROM subject";
            PreparedStatement subjectStmt = c.c.prepareStatement(subjectQuery);
            ResultSet subjectRs = subjectStmt.executeQuery();

            // Fetch marks
            String marksQuery = "SELECT * FROM marks WHERE rollno = ?";
            PreparedStatement marksStmt = c.c.prepareStatement(marksQuery);
            marksStmt.setString(1, rollno);
            ResultSet marksRs = marksStmt.executeQuery();

            // Ensure both result sets have data
            if (subjectRs.next() && marksRs.next()) {
                shead.setText("Semester: " + marksRs.getString("semester"));
                for (int i = 1; i <= 6; i++) { // Assuming subject1 to subject6 and marks1 to marks6
                    String subjectColumn = "subject" + i;
                    String marksColumn = "marks" + i;

                    String subject = subjectRs.getString(subjectColumn);
                    String marks = marksRs.getString(marksColumn);

                    // Create and add labels to marksPanel
                    JLabel subjectLabel = new JLabel(subject != null ? subject : "N/A");
                    subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    marksPanel.add(subjectLabel);

                    JLabel marksLabel = new JLabel(marks != null ? marks : "N/A");
                    marksLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                    marksPanel.add(marksLabel);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No data found for the given roll number.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

            // Close resources
            subjectRs.close();
            subjectStmt.close();
            marksRs.close();
            marksStmt.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == print) {
            printComponent();
        }
    }

    // Method to print the JFrame content
    private void printComponent() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Student Marks");

//        job.setPrintable(new Printable() {
//            public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
//                if (page > 0) {
//                    return Printable.NO_SUCH_PAGE;
//                }
//
//                Graphics2D g2d = (Graphics2D) g;
//                g2d.translate(pf.getImageableX(), pf.getImageableY());
//
//                // Print the entire JFrame
//                getContentPane().printAll(g);
//                return Printable.PAGE_EXISTS;
//            }
//        });

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Printing failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Marks(""); // Pass roll number as argument for testing
    }
}
