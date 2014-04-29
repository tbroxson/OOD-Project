package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Joey
 * This is the item class that is designed to build and item to be stored
 * in the inventory for the project. The Item class will implement
 * serializable interface (Decarotor Pattern). The item also acts as the model
 * in the MVC pattern.
 */
public class Item implements Serializable {
	
	private String name;
	private int quantity;
	private double sellingPrice;
	private double invoicePrice;
	private String description;
	
	
	//Hash map to contain the the items that will make up the inventory
	private HashMap<String, Item> inventory = new HashMap<>();
	
	public Item(){
		//empty
	}
	
	
	/**
	 * Constructor that will create an Item object
	 * @param namec
	 * @param qty
	 * @param sellingpricec
	 * @param invoiceprice
	 */
	public Item(String namec, int qty, double sellingpricec, double invoiceprice){
		
		setName(namec);
		quantity = qty;
		sellingPrice = sellingpricec;
		invoicePrice = invoiceprice;
		

		//adds the first item into the hashmap
		if(inventory.containsKey(namec)){
			
			System.out.println("Item is alreaday inventory...use update");	
		}
		
		else{
			
			inventory.put(namec, this);
			
		}
	}
	
	
	/**
	 * update method that allows to update an Item object
	 * @precondition the item must exist in the inventory
	 * @param namec
	 * @param qty
	 * @param pricec
	 * @param ipricec
	 */
	public void update(String namec, int qty, double pricec, double ipricec){
		
		if(inventory.containsKey(namec)){

			//removes the item from the inventory
			inventory.remove(namec);
			
			
			//updates the values for the item
			setName(namec);
			quantity = qty;
			sellingPrice = pricec;
			invoicePrice = ipricec;
			
			//adds the updated item back to the 
			inventory.put(namec, this);
		}
				
		else{
			
			System.out.println("Item not found in inventory...use add item.");
			
		}		
	}
	
	
	/**
	 * Delete method that removes a specific item from the inventory
	 * @precondition the item to be deleted must exist within the inventory
	 * @param namec
	 */
	public void delete(String namec){
		
		if(inventory.containsKey(namec)){
			
			inventory.remove(namec);
			
		}
		else{
			
			System.out.println("Delete failed...item is no longer contained in the inventory.");
		}
		
	}
	
	/**method returns a string decribe quantity of the item
	 * @return
	 */
	public String getQuantity(){
		
		return String.valueOf(quantity);
	}
	
	/**
	 * method returns the selling price of an item
	 * @return
	 */
	public String getsellingPrice(){
		
		return String.valueOf(sellingPrice);
	}
	
	/**Method returns the invoice price of the method
	 * @return
	 */
	public String getinvoicePrice(){
		
		return String.valueOf(invoicePrice);
	}

	/**
	 * Method that returns the name of the item
	 * @return
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Method that sets the name of the item
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
	
	/**
	 * method that allows to set the selling price of the item
	 * @param prc
	 */
	public void setSellPrice(double prc){
		
		sellingPrice = prc;
	}
	
	/**
	 * method that allows to set the invoice price of an Item
	 * @param prc
	 */
	public void setInvoicePrice(double prc){
		
		invoicePrice = prc;
	}
	
	/**
	 * method that allows to set the quantity of the Item
	 * @param qty
	 */
	public void setQuantity(int qty){
		
		quantity = qty;
	}
	
	/**
	 * This method allows for the description to be added
	 */
	public void addDescription(String desc){
		
		description = desc;
		
	}
	
	/**
	 * This method gets the description of the product
	 * @return
	 */
	public String getDescription(){
		
		return description;
	}

}
