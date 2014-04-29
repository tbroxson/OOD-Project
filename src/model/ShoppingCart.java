package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import view.SignInScreen;

/**
 * @author Joey
 * Main class, the driver
 */
public class ShoppingCart {
	

	@SuppressWarnings({ "unused", "unchecked" })
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		
		//making a database object to hold the users
    	UPDatabase database1 = new UPDatabase();
		Inventory invt = new Inventory();
    	
    	//reading from the serialized stream for the Inventory
    	ObjectInputStream iin = new ObjectInputStream(new FileInputStream("items.dat"));
    	
    	ArrayList<Item> items = new ArrayList<>();
    	
    	try {
			items.addAll((ArrayList<Item>)iin.readObject());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception here");
			e.printStackTrace();
		}
    	
    	iin.close();
    	
    	//foreach statement that adds the items to the inventory
    	for(Item itms : items){
    		invt.addItem(itms);
    	}
    	
    	
    	//reading from the serialized stream for the UPDatabase
     	ObjectInputStream in = new ObjectInputStream(new FileInputStream("users.dat"));
    	
    	ArrayList<User> users = new ArrayList<>();
    	
    	try {
			users.addAll((ArrayList<User>)in.readObject());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	in.close();
    	
    	//foreach statement that adds the records into the database
    	for(User ud: users){
    		database1.addUser(ud);
    	}
    	
    	
    	SignInScreen screen = new SignInScreen(database1, invt);
    		
	}
}
