package model;

/**
 * @author Joey
 * The buyer class builds the buyer type of user.
 * 
 */
public class Buyer extends User {
	
	
	
    /**
     * Constructor for buyer object 
     */
    Buyer(){
    	
        super.setUserType("buyer");
    }
    
    
    /**
     * Constructor for a Buyer object
     * @param name
     * @param pass
     */
    public Buyer(String name, String pass){
    	
        super.setUsername(name);
        super.setPassword(pass);
        super.setUserType("buyer");
    }

}
