package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Inventory;

/**
 * @author Joey
 * The SellerUI class is a class that is used as a
 * template for the different screens that the seller can
 * use to add, delete, or update. This is use of the Strategy
 * Pattern.
 */
public class SellerUI {
	
public JFrame utilityScreen = new JFrame();

	
	//inventory object to passed in
	public Inventory inventory = new Inventory();
	
	//array of textfields
	public JTextField[] fields = new JTextField[5];
	
	//array of JLabels
	public JLabel[] labels = new JLabel[fields.length];
	
	//variables to hold the values of an item
	public String name, quantity, price, invoicePrice, description;
	
	/**
	 * The SellerUI constructor builds in GUI for different
	 * seller screens
	 * @param invt
	 */
	public SellerUI(Inventory invt){
		
		inventory = invt.clone();
		
		//for loop creating the proper JLabels and JTextFields
				for(int i = 0; i < labels.length; ++i){
					if(i == 0){
						labels[i] = new JLabel("Name");
						fields[i] = new JTextField();
					}
					else if (i == 1){
						labels[i] = new JLabel("Quantity");
						fields[i] = new JTextField();
					}
					else if(i == 2){
						labels[i] = new JLabel("Selling Price");
						fields[i] = new JTextField();
					}
					else if(i == 3){
						labels[i] = new JLabel("Invoice Price");
						fields[i] = new JTextField();
					}
					else{
						labels[i] = new JLabel("Description");
						fields[i] = new JTextField();
					}
				}
	}
	
	/**
	 * Create frame method that creates a frame for the a seller screen
	 */
	public void createFrame(){
		
		//setting up the frame
		utilityScreen.setLayout(new FlowLayout());
		utilityScreen.setSize(400,200);
		utilityScreen.setVisible(true);
		utilityScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
