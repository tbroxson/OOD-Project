package model;

import java.io.Serializable;

/**
 * @author Joey
 * The user class that builds a USer object.
 * The user object is the super class for the
 * user types in the system. This class implements
 * serializable (Decorator Pattern)
 */
public class User implements Serializable {
	
	//variables for a user in the system
    private String username = null;
    private String password = null;
    private String userType = null;
    
    
    /**
     * method that sets the name of the user
     * @param usernamec
     */
    public void setUsername(String usernamec){
    	
        username = usernamec;
    }
    

    /**
     * method that will set the password for the user
     * @param passwordc
     */
    public void setPassword(String passwordc){
    	
        password = passwordc;
    }
    
    
    /**
     * method that returns a username
     * @return
     */
    public String getUsername(){
    	
        return username;
    }
    
    
    /**
     * method that returns a user's passsword
     * @return
     */
    public String getPassword(){
    	
        return password;
    }
    
    /**
     * Method that will set the usertype to buyer or seller
     * @param userTypec
     */
    public void setUserType(String userTypec){
    	
        userType = userTypec;
        
    }
    
    /**
     * method that returns the usertype
     * @return
     */
    public String getUserType(){
    	
        return userType;
    }

}
