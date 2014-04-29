package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author Joey
 * This is the inventory class that will contain all the items
 * to be displayed for the user to purchase. The inventory implements
 * cloneable and serializable (using a variant of the decorator pattern
 * called stream decarators). The inventory also acts as the model in
 * the MVC pattern when the inventory is displayed by the GUI.
 */
public class Inventory implements Cloneable, Serializable {
	
		//Hash map to contain the the items that will make up the inventory
		private HashMap<String, Item> items = new HashMap<>();
		
		//variable to hold the amount of Items in the inventory
		private int inventorySize;

		private Business biz = new Business();
		
		//variables to calculate the profit for the items sold/in inventory
		private double revenue = 0, cost = 0 ;
		
		
			/**
			 * The add method that adds items to the inventory
			 * @param namec
			 * @param qty
			 * @param pricec
			 */
			public void add(String namec, int qty, double pricec, double iprice){
				
				if(items.containsKey(namec)){
					
					System.out.println(namec + " is alreaday inventory...use update");
					
				}
				
				else{
					
					Item item = new Item(namec, qty, pricec, iprice);
					items.put(namec, item);
					inventorySize ++;
					
					//adding to the cost of items in the inventory
					cost = iprice * qty;
					biz.setCost(cost);
					
					//Arraylist that holds the items in the inventory
					ArrayList<Item> itemList = new ArrayList<>();
					
					//Iterator that will iterate through the hashmap containing the items in the system
					Iterator<String> iter = items.keySet().iterator();
					
					//adding all the items in the inventory to the arraylist
					while (iter.hasNext()){
						
						String key = (String)iter.next();
						Item i = items.get(key);
						itemList.add(i);
						
					}
					
					//making the inventory serializable
					try {
						
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("items.dat"));
						out.writeObject(itemList);
						out.close();
					}
					
					catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Inside the Inventory object; output stream failed due to: " + e);
					}
					
					
				}
			}
			
			
			
			/**
			 * Method that prints the inventory contents
			 */
			public void print(){
				
				System.out.println("The inventory contains:\n");
				
				Iterator<Entry<String, Item>> it =  items.entrySet().iterator();
				while(it.hasNext()){
					Entry<?, ?> pairs = (Entry<?, ?>) it.next();
					@SuppressWarnings("unused")
					Item its = (Item) pairs.getValue();
					it.remove();
					inventorySize --;
					
				}
			}
			
			
			/* (non-Javadoc)
			 * @see java.lang.Object#clone()
			 */
			public Inventory clone(){
				
				try{
					
					Inventory cloned = (Inventory) super.clone();
					return cloned;
				}
				catch(Exception e){
					System.out.println("Inside clone method of Inventory, failed due to: " + e);
					return null;
				}
				
			}

			/**
			 * Method that returns the amount of items in the inventory
			 * @return
			 */
			public int getInventorySize() {
				
				return inventorySize;
			}

			/**
			 * Method will set the size of the inventory
			 * @param inventorySize
			 */
			public void setInventorySize(int inventorySize) {
				
				this.inventorySize = inventorySize;
			}
			
			
			/**
			 * This is the method the inventory must contain items
			 * @precondition this item must exist
			 * @return an array containing all items in the inventory
			 */
			public Item[] getItem(){
				
				int i = 0;
				
				Item[] itemArray = new Item[inventorySize];
				
				Iterator<String> iter = items.keySet().iterator();
				
				while(iter.hasNext()){
					String key = (String) iter.next();
					itemArray[i] = items.get(key);
					++i;
				}
				return itemArray;
			}
			
			/**
			 * The add item method adds items to the inventory
			 * it will also calculate the total cost of the current inventory
			 * @param itm
			 */
			public void addItem(Item itm){
				
				items.put(itm.getName(), itm);
				inventorySize ++;
				cost = (Double.parseDouble(itm.getinvoicePrice()) * Double.parseDouble(itm.getQuantity()));
				biz.setCost(cost);
			}
			
			
			/**
			 * This is the delete method that will allow for you to delete an item
			 * in the inventory. This is the delete that will be used by the user
			 * when they forcibly delete an item.
			 * @precondition the item you want to delete must be in the inventory
			 * @param namec
			 * @return
			 */
			public Boolean deleteItemU(String namec){
				
				if(items.containsKey(namec)){
					
					Item temp = items.get(namec);
					items.remove(namec);
					inventorySize --;
					
					//Arraylist that holds the items in the inventory
					ArrayList<Item> itemList = new ArrayList<>();
					
					//Iterator that will iterate through the hashmap containing the items in the system
					Iterator<String> iter = items.keySet().iterator();
					
					//updating the cost when deleting an item
					cost = (Double.parseDouble(temp.getinvoicePrice()) * Double.parseDouble(temp.getQuantity()));
					biz.subtractCost(cost);
					
					//adding all the items in the inventory to the arraylist
					while (iter.hasNext()){
						
						String key = (String)iter.next();
						Item i = items.get(key);
						itemList.add(i);
						
					}
					//making the inventory serializable
					try {
						
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("items.dat"));
						out.writeObject(itemList);
						out.close();
					}
					
					catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Inside the Inventory object; output stream failed due to: " + e);
					}
					
					return true;
				}
				else
					return false;
			}
			
			/**
			 * this delete method is invoked by the seller on the after checkout
			 * @precondition item must exist in the inventory
			 * @param namec
			 * @return
			 */
			public Boolean deleteItem(String namec){
				
				if(items.containsKey(namec)){
					
					Item temp = items.get(namec);
					items.remove(namec);
					inventorySize --;
					
					//Arraylist that holds the items in the inventory
					ArrayList<Item> itemList = new ArrayList<>();
					
					//Iterator that will iterate through the hashmap containing the items in the system
					Iterator<String> iter = items.keySet().iterator();
					
					//adding all the items in the inventory to the arraylist
					while (iter.hasNext()){
						
						String key = (String)iter.next();
						Item i = items.get(key);
						itemList.add(i);
						
					}
					//making the inventory serializable
					try {
						
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("items.dat"));
						out.writeObject(itemList);
						out.close();
					}
					
					catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Inside the Inventory object; output stream failed due to: " + e);
					}
					
					return true;
				}
				else
					return false;
			}
			
			/**
			 * The updateItem method that allows to update items that exist in
			 * the inventory.
			 * @precondition items.containsKey(key) == true
			 * @param namec
			 * @param qty
			 * @param prc
			 * @param iprc
			 * @return
			 */
			public Boolean updateItem(String namec, int qty, double prc, double iprc){
				
				if(items.containsKey(namec)){
					//check to see if the item qty greater or less then we can decide what to do
					Item temp = items.remove(namec);
						int currentQ = Integer.parseInt(temp.getQuantity());
						double currentC = Double.parseDouble(temp.getinvoicePrice()); 
						temp.setName(namec);
						temp.setQuantity(qty);
						temp.setSellPrice(prc);
						temp.setInvoicePrice(iprc);
						
						//place updated item back into the inventory
						items.put(namec, temp);
						
						//update the cost of the item
						double tcost = (currentC * currentQ);
						biz.subtractCost(tcost);
						cost = (Double.parseDouble(temp.getinvoicePrice()) * qty);
						biz.setCost(cost);
						
					
					
					
					//Arraylist that holds the items in the inventory
					ArrayList<Item> itemList = new ArrayList<>();
					
					//Iterator that will iterate through the hashmap containing the items in the system
					Iterator<String> iter = items.keySet().iterator();
					
					//adding all the items in the inventory to the arraylist
					while (iter.hasNext()){
						
						String key = (String)iter.next();
						Item i = items.get(key);
						itemList.add(i);
						
					}
					//making the inventory serializable
					try {
						
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("items.dat"));
						out.writeObject(itemList);
						out.close();
					}
					
					catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Inside the Inventory object; output stream failed due to: " + e);
					}
					
					return true;
				}
				else return false;
			}
			
			/**
			 * this update will take the sale of an item into consideration
			 * as to provide correct profits made from the sale of an item
			 * @precondition the item must exist in the inventory
			 * @param namec
			 * @param qty
			 * @param prc
			 * @param iprc
			 * @return
			 */
			public Boolean updateSellItem(String namec, int qty, double prc, double iprc){
				
				if(items.containsKey(namec)){
					Item temp = items.remove(namec);
					int currentQty = Integer.parseInt(temp.getQuantity());
					temp.setName(namec);
					temp.setQuantity(qty);
					temp.setSellPrice(prc);
					temp.setInvoicePrice(iprc);
					
					//place updated item back into the inventory
					items.put(namec, temp);
					
					//Arraylist that holds the items in the inventory
					ArrayList<Item> itemList = new ArrayList<>();
					
					//Iterator that will iterate through the hashmap containing the items in the system
					Iterator<String> iter = items.keySet().iterator();
					
					//adding all the items in the inventory to the arraylist
					while (iter.hasNext()){
						
						String key = (String)iter.next();
						Item i = items.get(key);
						itemList.add(i);
						
					}
					//making the inventory serializable
					try {
						
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("items.dat"));
						out.writeObject(itemList);
						out.close();
					}
					
					catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("Inside the Inventory object; output stream failed due to: " + e);
					}
					
					return true;
				}
				else return false;
			}
			
			/**
			 * Methods that will calculate the profits for the cart
			 * @param book
			 */
			public double getProfit(){
				
				return biz.getProfit(); 
			}
			
			/**
			 * a getter that will get the revenue
			 * @return
			 */
			public double getRevenue(){
				
				return biz.getRevenue();
			}
			
			/**
			 * a getter that will return the cost 
			 * @return
			 */
			public double getCost(){
				
				return biz.getCost();
			}
			
			
			/**
			 * This method will set the revenue total made
			 * by the application
			 * @param rev
			 */
			public void setRevenue(double rev){
				
				biz.setRevenue(rev);
			}
			
}
