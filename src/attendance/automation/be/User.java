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

public abstract class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String passWord;
    private boolean isStudent;
    
   
    /**
     * Get the id of this user 
     * @return int id
     */
    public abstract int getId();
    
    /**
     * Set the id of this specific user
     * @param id 
     */
    public abstract void setId(int id);
    
    /**
     * Get the first name of this user
     * @return String firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the firstname of this specific user
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the last name of this user
     * @return String lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the lastname of this specific user
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Get this users username
     * @return String username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the username of this user
     * @param userName 
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the email address of the user
     * @return String email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email of this specific user
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the password of this user
     * @return String password
     */
    public String getPassword() {
        return passWord;
    }

    /**
     * Set the password of this specific user
     * @param password 
     */
    public void setPassword(String password) {
        this.passWord = password;
    }

    /**
     * Get the type of user determined of a boolean, 
     * if true the user is a student, 
     * else if false the user is a teacher
     * @return boolean usertype/is a student
     */
    public boolean getIsStudent() {
        return isStudent;
    }
    
    /**
     * Set the user type
     * @param UserType 
     */
    public void setIsStudent(boolean UserType) {
        this.isStudent = UserType;
    }
    

}
