package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Inventory;
import model.Item;
import model.UPDatabase;

/**
 * @author Joey
 * This class will provide the user view of the checkout screen.
 * It will prompt the user to enter their credit card number
 * and bulling address. After the customer enters this information,
 * the correct profits will be added together and the quantity of the
 * chosen item will be deducted.
 */
public class CheckOut {
	
	//frame for the checkout
	JFrame checkoutFrame = new JFrame("Checkout");
	private Inventory inventory = new Inventory();
	
	//variable to hold the quantities that were passed in by the previous screen
	private ArrayList<Integer> newQty = new ArrayList<>();
	
	//variables that will hold financial info
	private double cost, revenue;
	
	private Item[] list;
	
	//array to hold the column names
	private String[] columnNames ={"Item", "Quantity", "Price"};
	
	//the textfields that will hold payment information
	private JTextField[] infoFields = new JTextField[3];
	
	//JLabels to describe the info to be entered
	private JLabel[] labels = new JLabel[3];
	
	//table to display what the user is buying
	private JTable table;
	
	private double total = 0.0;
	
	private UPDatabase db;
	
	/**
	 * Constructor for the checkout window
	 * @param invt
	 * @param items
	 * @param qty
	 * @param datab
	 * @param cart
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CheckOut(Inventory invt, final Item[] items, ArrayList<Integer> qty, UPDatabase datab){
		
		
		db = datab.clone();
		list = new Item[items.length];
		
		//getting the values of the quantities that were passed in by the previous screen
		for(int i = 0; i < qty.size(); ++i){
			newQty.add(i, qty.get(i));
		}
		
		inventory = invt.clone();
		
		//setting up the JTable
		//2D array that will comprise the data for the table; adding plus one to the rows to include a total
		Object[][] data = new Object[items.length + 1][3];
		
		
		//populate the 2x2 array that will hold the item data to populate the table[r][c]
		for(int i = 0; i < items.length; ++i){
			
			for(int k = 0; k < 3; ++k){
				
				if(k == 0){
    				data[i][k] = items[i].getName();
    			}
    			else if(k ==1){
    				data[i][k] = newQty.get(i);
    			}
    			else{ 
    				data[i][k] = (Double.parseDouble(items[i].getsellingPrice()) * newQty.get(i));
    			}
			}
			
			total = total + (Double.parseDouble(items[i].getsellingPrice()) * newQty.get(i));
		}
		
		//getting the profit made by the selling
		DecimalFormat df = new DecimalFormat("#.##");
		data[items.length][0] = "Total";
		data[items.length][2] = df.format(total);
		
		//create the table
		table = new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,100));
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		
		//JPanel to hold the labels and texts
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//Jlabels to be used
		JLabel name = new JLabel("Enter Full Name on Card");
		panel.add(name);
		JTextField nameField = new JTextField();
		panel.add(nameField);
		
		JLabel address = new JLabel("Enter Full Billing Address");
		panel.add(address);
		JTextField addressField = new JTextField();
		panel.add(addressField);
		
		//comboBox to display the credit card types
		JComboBox type = new JComboBox();
		type.addItem("MasterCard");
		type.addItem("American Express");
		type.addItem("Visa");
		type.addItem("Discover");
		JLabel cardSelect = new JLabel("Select Credit Card Company");
		panel.add(cardSelect);
		panel.add(type);
		
		JLabel card = new JLabel("Enter Credit Card Number");
		panel.add(card);
		JTextField cardInfo = new JTextField();
		panel.add(cardInfo);
		
		//through this actionlistener the values will be updated in the inventory
		final JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == submit){
					Boolean b = update(items, newQty); 
					JPanel message = new JPanel();
					JOptionPane.showMessageDialog(message, "Thank you transaction completed");	
					checkoutFrame.setVisible(false);
					//for testing purposes only
					inventory.setRevenue(total);
					SignInScreen si = new SignInScreen(db, inventory);
					
					
				}
				
			}
			
		});
		
		
		panel.add(submit);
		JScrollPane tablePane = new JScrollPane(table);
		checkoutFrame.setLayout(new FlowLayout());
		checkoutFrame.add(tablePane);
		checkoutFrame.add(panel);
		//inventoryFrame.add(addItem);
		checkoutFrame.setSize(600,400);
		checkoutFrame.setVisible(true);
		checkoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	/**
	 * Method that will update the items in the inventory after being bought
	 * @param itemList
	 * @param qyt
	 * @return
	 */
	public Boolean update(Item[] itemList, ArrayList<Integer> qyt){
		
		for(int i = 0; i < itemList.length; ++i){
			
			if(Integer.parseInt(itemList[i].getQuantity()) == qyt.get(i)){
				Boolean b = inventory.deleteItem(itemList[i].getName());
				if(b){
					System.out.println("Deletion successful");
				}
			}
			
			else if(inventory.updateSellItem(itemList[i].getName(), (Integer.parseInt(itemList[i].getQuantity()) - qyt.get(i)), Double.parseDouble(itemList[i].getsellingPrice()), Double.parseDouble(itemList[i].getinvoicePrice()))){
				System.out.println("Update Succcessful");
				
			}
		}
		return null;
	}
	
}

