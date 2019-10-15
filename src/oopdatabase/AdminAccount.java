package oopdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *this class extends to the superclass account it is has the
 * behavior of the administrator account.
 *
 * @author Joseph Chaves, Christian Mabao
 */

public class AdminAccount extends Account{
    Conn con = new Conn();
    
    /**
     * This methods returns result set and its behavior is to view the employees
     * that is in the query.
     * 
     * @param id get the id of the employee to be reference
     * @return it will return the result set to the database containing the employee informations
     */
    public ResultSet viewEmployee(String id){
        Connection conn;
	
        try {    
            conn =  con.getCon();
            PreparedStatement insert = conn.prepareStatement("SELECT * FROM employee JOIN hourswork ON employee.EmployeeId = hourswork.EmployeeId JOIN wage ON employee.EmployeeId = wage.EmployeeId WHERE employee.EmployeeId='" + id + "'");
            ResultSet rs = insert.executeQuery();
            
            
            return rs;
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
    }
    /**
     * This method returns a boolean expression has the behavior to add employee
     * to the query.
     * 
     * @param name the name of the employee to be addded
     * @param address the address of the employee to be addded
     * @param CN the contact number of the employee to be addded
     * @param ECN the emergency contact number  of the employee to be addded
     * @param Department the department name of the employee to be added
     * @param bd the birth date of the employee to be addded
     * @param sex the sex of the employee to be addded
     * @param rate employee's rate to be added
     * @return return a true if the employee was successfully added, otherwise false 
     * @throws SQLException throws any exception if an error happens
     */
    public boolean addEmployee(String name, String address, String CN, String ECN, String Department, 
            String bd, String sex, String rate) throws SQLException{
        
	Connection conn;
	try {
            
            conn =  con.getCon();
            boolean in;
            
            PreparedStatement insert = conn.prepareStatement("SELECT * FROM department WHERE DeptName='" + Department + "'");
            ResultSet rs = insert.executeQuery();
            
            if(rs.next()){
                //
            }else{
                insert = conn.prepareStatement("INSERT INTO department(DeptName) VALUES(?)");
                insert.setString(1, Department);
                in = insert.execute();
            }
            
            insert = conn.prepareStatement("INSERT INTO employee (Name, Address, ContactNumber, EmergencyNum, Sex, BirthDate, DeptName, Rate ) VALUES(?,?,?,?,?,?,?,?)");
            insert.setString(1, name);
            insert.setString(2, address);
            insert.setString(3, CN);
            insert.setString(4, ECN);
            insert.setString(5, sex);
            insert.setString(6, bd);
            insert.setString(7, Department);
            insert.setString(8, rate);
            in = insert.execute();
            
            insert = conn.prepareStatement("SELECT * FROM employee WHERE name ='" + name + "'");
            ResultSet rs2 = insert.executeQuery();
            
            String id = null;
            if(rs2.next()){
                id =  Integer.toString((int) rs2.getObject("EmployeeId"));
                JOptionPane.showMessageDialog( null, rs2.getObject("EmployeeId"), "Employee ID is:", JOptionPane.PLAIN_MESSAGE);
            }
             
            insert = conn.prepareStatement("INSERT INTO wage(EmployeeId) VALUES(?)");
            insert.setString(1, id);
            in = insert.execute();
            
            insert = conn.prepareStatement("INSERT INTO hourswork(EmployeeId) VALUES(?)");
            insert.setString(1, id);
            in = insert.execute();
            
            conn.close();
            
            return true;
        } catch (Exception e) {
         
           JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        } 
    }
    
    //delete employee
    public void deleteEmployee(String id){
        Connection conn;
	
        int i = JOptionPane.showConfirmDialog(null, "Confirm deletion?", "Admin", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
         
        if(i == 0){
            try {
                conn =  con.getCon();
            
                PreparedStatement insert = conn.prepareStatement("DELETE FROM deduction WHERE employeeId ='" + id + "'");
                boolean in = insert.execute();
            
                insert = conn.prepareStatement("DELETE FROM hourswork WHERE employeeId ='" + id + "'");
                in = insert.execute();
            
                insert = conn.prepareStatement("DELETE FROM password WHERE employeeId ='" + id + "'");
                in = insert.execute();
            
                insert = conn.prepareStatement("DELETE FROM wage WHERE employeeId ='" + id + "'");
                in = insert.execute();
            
                insert = conn.prepareStatement("DELETE FROM employee WHERE employeeId ='" + id + "'");
                in = insert.execute();
                
                insert = conn.prepareStatement("DELETE FROM deduction WHERE employeeId ='" + id + "'");
                in = insert.execute();
            
                conn.close();
            
                JOptionPane.showMessageDialog( null, "Deleted", "Admin", JOptionPane.PLAIN_MESSAGE);
            
            }
            catch (Exception e){
                JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }    
    
    /**
     * This method returns a result set and has a behavior to view the department and 
     * the employees on it.
     * 
     * @param department gets the department to be view
     * @return
     */
    public ResultSet viewDepartment(String department){
        Connection conn;
	
        try {    
            conn =  con.getCon();
            PreparedStatement insert = conn.prepareStatement("SELECT Name from employee where DeptName ='" + department + "'");
            ResultSet rs = insert.executeQuery();
            
            return rs;
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } 
    }
    
    /**
     * This method return the a result set to the database and has a behavior to
     * generate all the employee's payroll.
     * 
     * @return returns the result set containing the employees information
     */        
    public ResultSet generatePayroll(){
        int i = JOptionPane.showConfirmDialog(null, "Confirm?", "Admin", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        ResultSet rs;
        
        if (i == 0){
            Connection conn;
	
            try {    
                conn =  con.getCon();
                PreparedStatement insert = conn.prepareStatement("SELECT * FROM employee JOIN hourswork");
                rs = insert.executeQuery();
                
                return rs;
            } catch (Exception e) {    
                JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }else{
            JOptionPane.showMessageDialog( null, "Cancelled", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }    
    }

    /**
     * This method returns a boolean expression and has a behavior to check the
     * admin's name and password during log in. 
     * 
     * @param Name gets the admins name to be check
     * @param Password gets the password of the admin to be check
     * @return returns true if the name and password is correct and false if 
     * it is wrong
     */    
    public boolean logIn(String Name, char[] Password){
        Conn conn        = new Conn();
        Connection con;
	StringBuilder sb = new StringBuilder();
        boolean confirm  = false;
        
        for(int i = 0; i < Password.length; i++){
            sb.append(Password[i]);
        }
        String newpassword = sb.toString();
       
       
        try {
            con = conn.getCon();
            PreparedStatement insert = con.prepareStatement("SELECT admin.Name, admin.Password FROM admin");
            ResultSet rs = insert.executeQuery();
            
            while (rs.next()) {
                
                if ((rs.getObject("Name").equals(Name)) && (rs.getObject("Password").equals(newpassword))){
                    confirm = true;
                    break;
                }
            }
	} catch (Exception e) {
             JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return confirm;
    }
    /**
     * This method has a behavior to change the Administrator's
     * password.
     */
    public void changePassword(){
        Connection conn;
        
        String name     = JOptionPane.showInputDialog("Admin ");
        String password = JOptionPane.showInputDialog("New password");
        
        try {    
            conn =  con.getCon();
            PreparedStatement insert = conn.prepareStatement("UPDATE admin SET Password ='" + password + "'"  + "where Name ='" + name + "'" );
            boolean in = insert.execute();
            
            JOptionPane.showMessageDialog( null, "Updated", "Admin", JOptionPane.PLAIN_MESSAGE);
            conn.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            
        } 
    }
    
    /**
     * This adds deduction to the an employee's wage.  
     */   
    public void addDeduction(){
        Connection conn;
        
        int id        = Integer.parseInt(JOptionPane.showInputDialog("Employee id "));
        String name   = JOptionPane.showInputDialog("Deduction name");
        String month  = JOptionPane.showInputDialog("Month");
        String amount = JOptionPane.showInputDialog("Amount");
        
        try {    
            conn =  con.getCon();
            PreparedStatement insert = conn.prepareStatement("INSERT INTO deduction (EmployeeId, DeductionName, Month, Amount) VALUES(?,?,?,?)");
            insert.setInt(1, id);
            insert.setString(2, name);
            insert.setString(3, month);
            insert.setString(4, amount);
            
            boolean in = insert.execute();
            
            JOptionPane.showMessageDialog( null, "Deduction added", "Admin", JOptionPane.PLAIN_MESSAGE);
            conn.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            
        } 
    }
    /**
     * This method returns a linked list and has a behavior to 
     * calculate wages.
     * @return returns the calculated wage/s
     */
    public LinkedList calculate(){
        LinkedList wage = new LinkedList();
        double salary;
                
        Connection conn;
	
            try {    
                conn =  con.getCon();
                PreparedStatement insert = conn.prepareStatement("SELECT * FROM wage");
                ResultSet rs = insert.executeQuery();
             
                while(rs.next()){
                    insert = conn.prepareStatement("SELECT * FROM deduction WHERE EmployeeId ='" + rs.getString("EmployeeId") +"'");
                    ResultSet rs1 = insert.executeQuery();
                    
                    salary = rs.getDouble("WageAmount");
                    while(rs1.next()){
                        salary = salary - rs1.getDouble("Amount");
                    }
                    if(salary >= 2250 && salary < 3000 ){
                        salary = salary - 250;
                    }else if (salary >= 3000 && salary < 5000 ){
                        salary = salary - 600;
                    }else if (salary >= 5000 && salary < 10000 ){
                        salary = salary - 1200;
                    }else if (salary >= 10000 && salary < 15000 ){
                        salary = salary - 2000;
                    }else if (salary >= 15000 ){
                        salary = salary - 2400;
                    }
                    
                    wage.add(salary);
                }
                conn.close();
                clear();
                return wage;
            } catch (Exception e) {    
                JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
    }
    
    /**
     * This method clears all employees worked hours and deductions 
     * after generating a payroll
     * 
     */
    public void clear(){
        Connection conn;
        String id;
        
        try {    
            conn =  con.getCon();
            
            PreparedStatement insert = conn.prepareStatement("DELETE from deduction" );
             boolean in = insert.execute();
                
            insert = conn.prepareStatement("UPDATE hourswork SET Regular = 0, NonRegular = 0");
            in = insert.execute();
            
            insert = conn.prepareStatement("UPDATE wage SET WageAmount = 0");
            in = insert.execute();
           
            JOptionPane.showMessageDialog( null, "GENERATED", "Admin", JOptionPane.PLAIN_MESSAGE);
            conn.close();
         } catch (Exception e) {    
            JOptionPane.showMessageDialog( null, e, "Error", JOptionPane.ERROR_MESSAGE);
            
        } 
    }
}
