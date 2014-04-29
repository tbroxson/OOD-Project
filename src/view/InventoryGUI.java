package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Inventory;
import model.Item;
import model.UPDatabase;

/**
 * @author Joey
 * The InventoryGUI class will display a table of all the inventory items
 * to the seller. The seller will be able to add to the iventory, update items
 * on the list, and will be able to delete items
 *
 */
public class InventoryGUI {
	
	//the frame for the inventory
	private JFrame inventoryFrame = new JFrame("Inventory");
	
	//the inventory object that will contain the items in the inventory
	private Inventory inventory = new Inventory();
	
	//JLabel array that is based on the size of inventory items...THIS WONT WORK
	@SuppressWarnings("unused")
	private JLabel[] itemLabel  = new JLabel[inventory.getInventorySize()];
	
	//array to hold the column names
	private String[] columnNames ={"Item", "Quantity", "Selling Price", "Invoice Price"};
	
	//JButtons for actions
	private JButton[] buttons = new JButton[3];
	
	private JButton profit = new JButton("Profit");
	
	private JButton back = new JButton("Back");
	
	private UPDatabase db;
	
	//JTable object to organize the items in the inventory
	JTable table;
	
	InventoryGUI(){
		//empty constructor
	}
	
	/**
	 * constructor for the inventory gui that builds and
	 * displays the current inventory to the seller
	 * @param inventoryc
	 */
	InventoryGUI(Inventory inventoryc, UPDatabase database){
		
		db = database.clone();
	
		inventory = inventoryc.clone();
		
		//2D array that will comprise the data for the table
		Object[][] data = new Object[inventory.getInventorySize()][4];
		
		//getting the Item objects from the Inventory
		Item[] itemList = new Item[inventory.getInventorySize()];
		
		//for loop to populate the items in the inventory 
		for(int i = 0; i < itemList.length; ++i){
			itemList[i] = inventory.getItem()[i];
		}
		
		//populate the 2x2 array that will hold the item data to populate the table[r][c]
		for(int i = 0; i < inventory.getInventorySize(); ++i){
			for(int k = 0; k < 4; ++k){
				
				if(k == 0){
    				data[i][k] = itemList[i].getName();
    			}
    			else if(k ==1){
    				data[i][k] = itemList[i].getQuantity();
    			}
    			else if(k == 2){
    				data[i][k] = itemList[i].getsellingPrice();
    			}
    			else{
    				data[i][k] = itemList[i].getinvoicePrice();
    			}
				
			}
		}
		
		//create the table
		table = new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500,200));
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		
		JScrollPane tablePane = new JScrollPane(table);
		inventoryFrame.setLayout(new FlowLayout());
		inventoryFrame.add(tablePane);
		//inventoryFrame.add(addItem);
		inventoryFrame.setSize(600,400);
		inventoryFrame.setVisible(true);
		inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for(int i = 0; i < buttons.length; ++i){
			
			ActionListener al = new ActionListener(){

				@SuppressWarnings("unused")
				@Override
				public void actionPerformed(ActionEvent event) {
					if(event.getSource() == buttons[0]){
						System.out.println("Add Item button is working");
						AddScreen addScreen =  new AddScreen(inventory, db);
						inventoryFrame.setVisible(false);
					}
					else if(event.getSource() == buttons[1]){
						System.out.println("Delete Item button is working");
						//call the delete screen
						DeleteScreen dScreen = new DeleteScreen(inventory, db);
						inventoryFrame.setVisible(false);
					}
					else{
						System.out.println("Update Item button is working");
						//call the update
						UpdateScreen us = new UpdateScreen(inventory, db);
						inventoryFrame.setVisible(false);
					}
					
				}
				
			};
			
			if(i == 0){
				buttons[i] = new JButton("Add Item");
				buttons[i].addActionListener(al);
			}
			else if(i == 1){
				buttons[i] = new JButton("Delete Item");
				buttons[i].addActionListener(al);
			}
			else{
				buttons[i] = new JButton("Update Item");
				buttons[i].addActionListener(al);
			}
			inventoryFrame.add(buttons[i]);
		}
		
		profit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(arg0.getSource() == profit){
					System.out.println("profit button is working");
					Profit p = new Profit(inventory);
				}
				
			}
			
		});
		
		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg1) {
				if(arg1.getSource() == back){
					inventoryFrame.setVisible(false);
					SignInScreen si = new SignInScreen(db, inventory);
				}
				
			}
			
		});
		inventoryFrame.add(profit);
		inventoryFrame.add(back);
		
	}

}
