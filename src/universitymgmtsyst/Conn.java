package universitymgmtsyst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
   public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish the connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagement", "root", "");

            // Create a statement
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  
}
