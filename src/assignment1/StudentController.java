/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author prasad
 */
public class StudentController {

   
    public static boolean addstudent(Student student) throws ClassNotFoundException, SQLException{
        PreparedStatement stm;
        stm = DBConnection.getInstance().getConnection().prepareStatement("insert into student values(?,?,?,?)");
        stm.setObject(1, student.getId());
        stm.setObject(2, student.getName());
        stm.setObject(3, student.getAddress());
        stm.setObject(4, student.getContact());
        int rst = stm.executeUpdate();
        return rst>0;
    
    }

   public  static Student searchStudent(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("select *from student where id=?"); 
        stm.setObject(1, id);
        ResultSet rst = stm.executeQuery();
        if(rst.next()){
             return new Student(id,rst.getString("name"),rst.getString("address"),rst.getString("contact"));
                
        }else{
                return null;
            }
        }

    static boolean updatestudent(Student student) throws ClassNotFoundException, SQLException {
       PreparedStatement stm;
        stm = DBConnection.getInstance().getConnection().prepareStatement("update Student set name=?,address=?,contact=? where id=?");
       
        stm.setObject(1, student.getName());
        stm.setObject(2, student.getAddress());
        stm.setObject(3, student.getContact());
         stm.setObject(4, student.getId());
        int rst = stm.executeUpdate();
        return rst>0;
    }

    static ArrayList <Student> getAllStudent() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("select *from student");
        
        ResultSet rst = stm.executeQuery();
        ArrayList <Student> allstudent = new ArrayList();
        while(rst.next()){
            Student student = new Student(Integer.parseInt(rst.getString("id")),rst.getString("name"),rst.getString("address"),rst.getString("contact"));
             allstudent.add(student);
        }
       return allstudent;
    }

    static boolean deleteStudent(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM student WHERE id=?");
        stm.setObject(1, id);
        return stm.executeUpdate()>0;
    }
               
    }

    
    

