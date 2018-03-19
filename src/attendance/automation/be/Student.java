/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

/**
 *
 * @author B
 */
public class Student extends User{

    private int id;
    
    public Student(int userType, String firstName, String lastName, String userName, String email, String passWord){

        super.setUserType(userType);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setUserName(userName);
        super.setEmail(email);
        super.setPassword(passWord);
        
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
}
