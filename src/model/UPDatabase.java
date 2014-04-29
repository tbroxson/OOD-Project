package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Joey
 * The UPDatabase is the class that stores the users that will be 
 * registered to use the system. This class implements serialization 
 * (Decorator Pattern) and it behaves as the model that it used
 *  to check for users in the system to then decide what they will
 *  be able to perform. This class also implements the Cloneable
 *  interface.
 */
public class UPDatabase implements Cloneable, Serializable {
	

private HashMap<String, User> database = new HashMap<>();
	
	
	/**
	 * method that adds user to the database
	 * @precondition file must be contained for serialization
	 * to work if not, throws
	 * @param user
	 * @throws FileNotFoundException
	 */
	public void addUser(User user) throws FileNotFoundException{
		
		
		if(database.containsKey(user.getUsername())){
			System.out.println("User already exists in the database");
		}
		
		else{
			database.put(user.getUsername(), user);
			
			//ArrayList that will hold UPDatabase entries for serialization
			ArrayList<User> users = new ArrayList<>();
			
			Iterator<?> iter = database.keySet().iterator();
			
			//while loop that iterates through the hash table and will add the Users into the array list
			while (iter.hasNext()){
				
				String key = (String)iter.next();
				User entry = database.get(key);
				users.add(entry);
				
			}
			
			
			//constructing stream for serialization
			try {
				
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("users.dat"));
				out.writeObject(users);
				out.close();
			}
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Inside the UPDatabase object; output stream failed due to: " + e);
			}
			
		}
	}
	
	
	/**
	 * method that checks to see if the user is in the database
	 * @param username
	 * @return
	 */
	public boolean findUser(String username){
		
		if(database.containsKey(username))
			return true;
		else
			return false;
	}
	
	
	/**
	 * The Delete user method will delete a user from the database
	 * @precondition the user to be deleted must exist in the database
	 * @param username
	 * @return
	 */
	public boolean deleteUser(String username){
		
		if(database.containsKey(username)){
			database.remove(username);
			return true;
		}
		else
			return false;
	}
	
	public UPDatabase clone(){
		try{
			UPDatabase cloned = (UPDatabase)super.clone();
			return cloned;
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
	
	/**
	 * method that returns the user type
	 * @param username
	 * @return
	 */
	public String getType(String username){
		
		User user = database.get(username);
		return user.getUserType();
	}
}
