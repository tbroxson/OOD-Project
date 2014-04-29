package model;

/**
 * @author Joey
 * The seller class inherits from user, will have different
 * methods from the buyer
 */
public class Seller extends User {
	
private Inventory i;
    
    
    /**
     * Constructor for Seller object
     */
    Seller(){
    	
        super.setUserType("seller");
    }
    
    
    /**
     * constuctor that takes parameters for a Seller object
     * @param namec
     * @param pass
     */
    public Seller(String namec, String pass){
    	
        super.setUsername(namec);
        super.setPassword(pass);
        super.setUserType("seller");
    }
    
    
    /**
     * add method that will add an item to the inventory.
     * @param name
     * @param qty
     * @param price
     * @param iprice
     */
    public void add(String name, int qty, double price, double iprice){
    	
    	i.add(name, qty, price, iprice);
    }

}
