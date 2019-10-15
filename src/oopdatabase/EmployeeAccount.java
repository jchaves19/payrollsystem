package oopdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * This class is a child class that extends to the superclass class which is the account
 * it is also has the behavior of an employee's account.
 *
 * @author Joseph Chaves, Christian Mabao
 */
public class EmployeeAccount extends Account{
    
    /**
     * this methods returns nothing and it changes the 
     * password of the user.
     */    
    public void changePassword(){
        conn = new Conn();
        Connection con;
        
        String id       = JOptionPane.showInputDialog("Employe id");
        String password = JOptionPane.showInputDialog("New password");
        
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("UPDATE password SET Password ='" + password + "'"  + "where EmployeeId ='" + id + "'" );
            boolean in = insert.execute();
            
            JOptionPane.showMessageDialog( null, "Updated", "Employee", JOptionPane.PLAIN_MESSAGE);
            con.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            
        } 
    }
     /**
     * This methods returns the result set and add employees worked hours
     * both regular and non regular
     * 
     * @param id gets the id of the employee to be added
     * @param in gets the input added
     * @param out gets the output
     * @return it will return the result sets to the database
     */   
    public ResultSet addHours(String id, int in, int out){
        conn = new Conn();
        Connection con;
        int result = 0;
        int r  = 0;
        String reg = null;
        String non = null;
           
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("Select Regular, NonRegular FROM hourswork WHERE EmployeeId ='" + id + "'");
            ResultSet rs = insert.executeQuery();
           
            while(rs.next()){ 
               result = rs.getInt("Regular");
               r      = rs.getInt("NonRegular");
            }
            
            con.close();
        }catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        if(out > 17){
            r = r + out - 17;
            non = String.valueOf(r);
            result = result + 17 - in;
            reg = String.valueOf(result);
        }
        else{
            non = String.valueOf(r);
            result = result + 17 - in;
            reg = String.valueOf(result);
        }
        
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("UPDATE hourswork SET Regular ='" + reg + "', " + "NonRegular ='" + non + "'" + "WHERE EmployeeId ='" + id + "'");
            boolean i = insert.execute();
            
            JOptionPane.showMessageDialog( null, "Added", "Employee", JOptionPane.PLAIN_MESSAGE);
            con.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            
        } 
        
        double rate = 0;
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("Select Rate FROM employee WHERE EmployeeId ='" + id + "'");
            ResultSet rs = insert.executeQuery();
           
            while(rs.next()){
                rate =  rs.getDouble("Rate");
            }
            
            con.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
        
        }
       
        double amount = (rate * result) + (((rate * 0.25) + rate) * r);
        updateWage(id, amount);
        
        return printAdded(id);
        
    }
    /**
     * This method will updates 
     * the employee's wage.
     * 
     * @param id gets the id of the  employee to be updated
     * @param amount the amount of the wage
     */    
    public void updateWage(String id, double amount){
        conn = new Conn();
        Connection con;
        
        String m;
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("SELECT WageAmount FROM wage WHERE EmployeeId ='" + id + "'" );
            ResultSet rs = insert.executeQuery();
            
            double newamount = 0;
            while(rs.next()){
                 newamount = amount + (double) rs.getObject("WageAmount");
            }
            
            m = String.valueOf(newamount);
            insert = con.prepareStatement("UPDATE wage SET WageAmount ='" + m + "'" + "WHERE EmployeeId ='" + id + "'");
            boolean in = insert.execute();
            
            con.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
         }
        
    }
    /**
     * This method return result set and it prints the added it hours work regular and non-regular
     * and other informations.   
     * 
     * @param id the id of the employee to be reference
     * @return it will return the result set containing information of the employee
     */        
    public ResultSet printAdded(String id){
        conn = new Conn();
        Connection con;
        
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("SELECT hourswork.Regular, hourswork.NonRegular, wage.WageAmount FROM hourswork JOIN wage ON hourswork.EmployeeId = wage.EmployeeId WHERE hourswork.EmployeeId ='" + id + "'" );
            ResultSet rs = insert.executeQuery();
           
            return rs;
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
         }
    }
    /**
     * this methods setups the ui interface of the employee
     * 
     * @param id gets the employee's id to be referenced
     * @return it will return the result set to the database with the information to be set up
     */    
    public ResultSet setUp(String id){
        conn = new Conn();
        Connection con;
        
        try {    
            con =  conn.getCon();
            PreparedStatement insert = con.prepareStatement("SELECT EmployeeId, Name, Rate FROM employee WHERE EmployeeId ='" + id + "'" );
            ResultSet rs = insert.executeQuery();
            
            
            return rs;
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
         }
        
    }
    
    
}
