package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The Business class will contain the necessary variables
 * and methods to calculate the profits, costs, and revenues.
 * This class behaves as the model for the profit screen, and
 * the inventory class acts like the controller as it changes
 * the state of the Business class. This class is also
 * serializable.
 * @author Joey
 *
 */
public class Business implements Serializable {
	
	//variables to calculate
	private double revenue, cost, profit;
	
	/**
	 * Constructor for the business item
	 * @throws FileNotFoundException 
	 */
	public Business() {
		
		try {
			initNumbers();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	/**getter method that returns revenue
	 * @return
	 */
	public double getRevenue() {
		
		return revenue;
	}

	/**
	 * setter method that will set the revenue
	 * @param revenue
	 */
	public void setRevenue(double revenue) {
		
		this.revenue = this.revenue + revenue;
		
				//double to hold revenue
				Double rev = this.revenue;
				
				//making the inventory serializable
				try {
					
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("numbers.dat"));
					out.writeObject(rev);
					out.close();
				}
				
				catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Inside the Business object; output stream failed due to: " + e);
				}
				
	}

	/**
	 * getter method that gets the profit
	 * @return
	 */
	public double getProfit() {
		
		profit = revenue - cost;
		
		return profit;
		
		
	}


	/**
	 * getter method that returns the cost
	 * @return
	 */
	public double getCost() {
		
		return cost;
	}

	/**
	 * setter method that will set the cost
	 * @param cost
	 */
	public void setCost(double cost) {
		
		this.cost = this.cost + cost;
				
	}
	
	/**
	 * method that will subtract from the cost
	 * @param cost
	 */
	public void subtractCost(double cost){
		
		this.cost = this.cost - cost;
				
	}
	
	/**
	 * This is an initializer method that will help calculate the numbers
	 * with serialization.
	 * @param revenue
	 * @param cost
	 * @param profit
	 */
	public void initNumbers() throws FileNotFoundException{
		
		try {
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("numbers.dat"));
			
			try {
				
				Double inNumbers = (Double) in.readObject();
				
				this.revenue = inNumbers;
					
				in.close();
				
			} 
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
