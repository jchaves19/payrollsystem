package oopdatabase;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * This class creates connection to the database.
 *
 * @author Joseph Chaves, Christian Mabao
 */
public class Conn {
    
    /**
     * This method returns the connection and has a behavior to connect the data to the
     * query.	
     * 
     * @return returns the connection to the database
     */
    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/payrolldb", "joseph", "@Joseph19");
           
            return con;
            } catch (Exception e) {		
		JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
	return null;
    }
}
