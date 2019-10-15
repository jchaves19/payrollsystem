package oopdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * This class add a password of an employee
 * to the database.
 *	
 *
 * @author Joseph Chaves, Chirsitan Mabao
 */
public class Password {
    Conn con = new Conn();
    
    /**
     * This methods returns string and has a behavior 
     * to add password to the database.
     * 
     * @param name the parameter name presents as the reference
     * of who's password was add
     * @return returns the id as the reference of the password that has 
     * been added
     */
    public String addPassword(String name){
        Connection conn;
	try {
            
            conn =  con.getCon();
            String password = JOptionPane.showInputDialog("Create Password");
                
            PreparedStatement insert = conn.prepareStatement("SELECT * FROM employee WHERE name ='" + name + "'");
            ResultSet rs = insert.executeQuery();
            
            String id = null;
            if(rs.next()){
                id =  Integer.toString((int) rs.getObject("EmployeeId"));
            }
            insert = conn.prepareStatement("INSERT INTO password (EmployeeId, Password) VALUES(?,?)");
            insert.setString(1, id);
            insert.setString(2, password);
            boolean in = insert.execute();
            
            conn.close();
            JOptionPane.showMessageDialog( null, "Employee Added", "Admin", JOptionPane.PLAIN_MESSAGE);
            return id;
        } catch (Exception e) {
           JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
           return null;
        } 
    }
    
}
