package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Inventory;
import model.Item;
import model.UPDatabase;

/**
 * @author Joey
 * This class acts as the view for the inventory model. It will display
 * the current inventory to a buyer user. The buyer will be able to choose
 * which items to purchase and then proceed to checkout.
 *
 */
public class BuyerInventory {
	
	private JFrame buyerInventory = new JFrame("Inventory");
	
	//array of JLabels
	private JLabel[] labels;
	
	//array of checkboxes
	private JCheckBox[] boxes;
	
	//array of JPanels
	private JPanel[] panels;
	
	//arrayList of Items that will be passed depending on what the user checks off
	private ArrayList<Item> choice = new ArrayList<>();
	
	//variable to hold the amount items in your cart
	private int yourItems;
	
	private UPDatabase db;

	
	/**
	 * This is the constructor that builds the inventory for the buyer to choose from
	 * @param invt
	 */
	public BuyerInventory(Inventory invt, UPDatabase database, final Item[] totalChoice){
		
		final Inventory inventory = invt.clone();
		db = database.clone();
		
		if(totalChoice != null){
			
			yourItems = totalChoice.length;
		}
		
		
		labels = new JLabel[inventory.getInventorySize()];
		boxes = new JCheckBox[labels.length];
		panels = new JPanel[labels.length];
		
		//get the items from the inventory
		final Item[] items = inventory.getItem();
		
		//panel for the list view
		JPanel iPanel = new JPanel();
		iPanel.setLayout(new BoxLayout(iPanel, BoxLayout.Y_AXIS));
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		//for loop to set up our panels 
		for(int i = 0; i < inventory.getInventorySize(); ++i){
			
			panels[i] = new JPanel();
			panels[i].setBackground(Color.WHITE);
			panels[i].setLayout(new FlowLayout());
			labels[i] = new JLabel("Product:  " + items[i].getName() + "   Available:  " + items[i].getQuantity() + 
						"   Price:  " + df.format(Double.parseDouble(items[i].getsellingPrice())));
			String s = items[i].getName();
			labels[i].addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent me) {
					System.out.println("clicking on Jlabel worked");
					for(int k = 0; k < inventory.getInventorySize(); ++k){
						
						if(me.getSource() == labels[k]){
							ItemDescription id = new ItemDescription(items[k]);
						}
						
					}
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			boxes[i] = new JCheckBox();
			panels[i].add(labels[i]);
			panels[i].add(boxes[i]);
			iPanel.add(panels[i]);
		}
		
		JLabel yourCart = new JLabel("Your Cart contains " + yourItems + " items");
		
		final JButton checkOut = new JButton("Proceed to Checkout");
		checkOut.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == checkOut){
					
					//an array of Items to be passed to the shopping cart depending on the user's choice
					ArrayList<String> chosenItems = new ArrayList<>();

					for(int i = 0; i < labels.length; ++i){
						if(boxes[i].isSelected()){
							chosenItems.add("Item: " + items[i].getName() + "   " + "Available: " + items[i].getQuantity() + "   " + "Price: "+ items[i].getsellingPrice());
							choice.add(items[i]);
							System.out.println("check box " + i + " is enabled");
						}
					}
					//call the checkout screen
					if(yourItems != 0){
						for(int i = 0; i < totalChoice.length; ++i){
							
							if(!choice.contains(totalChoice[i])){
								choice.add(totalChoice[i]);
								chosenItems.add("Item: " + items[i].getName() + "   " + "Available: " + items[i].getQuantity() + "   " + "Price: "+ items[i].getsellingPrice());
							}
						}
						YourCart newCart = new YourCart(inventory, chosenItems, choice, db);
						buyerInventory.setVisible(false);
					}
					YourCart cart = new YourCart(inventory, chosenItems, choice, db);
					buyerInventory.setVisible(false);
				}
				
			}
			
		});
		
		buyerInventory.setLayout(new FlowLayout());
		buyerInventory.setSize(500,400);
		buyerInventory.add(iPanel);
		buyerInventory.add(yourCart);
		buyerInventory.add(checkOut);
		buyerInventory.setVisible(true);
		buyerInventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
