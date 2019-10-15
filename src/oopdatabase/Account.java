package oopdatabase;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 * This class is an abstract class and a parent class at the same time
 * and have a subclass like AdminAccount and Employee account.
 * This class only sets the password,name, and the login method.
 * 
 * @author Joseph Chaves, Christian Mabao
 */
public abstract class Account {
    Conn conn;
    /**
     * Data members name and paassword.
     */
    private String name;
    private String password;
    
   /**
    * This method returns the string of the password and has a behavior to get the 
    * String password
    * 
    * @return returns the String password
    */
    public String getPassword() {
        return password;
    }

    /**
     * This method returns the string of the name and has a behavior to get the
     * String name.    
     *  
     * @return returns the String name
     */
    public String getName() {
        return name;
    }

    /**
     * This method returns nothing and has a behavior to set the password.
     * 
     * @param password gets the password to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method returns nothing and has a behavior to set the name.
     * 
     * @param name gets the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This method returns a boolean expression and has a behavior to check
     * the id and password id valid during log in.
     *  
     * @param Id the id of the account to log in
     * @param Password the password of the account to be log in
     * @return returns true if the credentials are correct otherwise returns false
     */
    public boolean logIn(int Id, char[] Password){
        conn = new Conn();
        Connection con;
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < Password.length; i++){
            sb.append(Password[i]);
        }
        String newpassword = sb.toString();
         boolean confirm  = false;
        
	try {
            con = conn.getCon();
            PreparedStatement insert = con.prepareStatement("SELECT password.EmployeeId, password.Password FROM password");
            ResultSet rs = insert.executeQuery();
            
            while (rs.next()) {
                if ( (rs.getObject("EmployeeId").equals(Id)) && (rs.getObject("Password").equals(newpassword))){
                    confirm = true;
                }
            }
	} catch (Exception e) {
             JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return confirm;
    }
    
}
