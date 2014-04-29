package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Inventory;
import model.Item;
import model.UPDatabase;

/**
 * @author Joey
 * This class works with the SellerUI. If the seller chooses
 * to add an item from the inventory screen then this class
 * contains the controllers that will notify the model to add the 
 * item to the invenory. This class also uses the Strategy Pattern
 * through its use of Layout Managers and the Observer Pattern
 * with ActionListeners and buttons being used to change the
 * state of the objects.
 */
public class AddScreen extends SellerUI {

	private UPDatabase db;
	
	/**
	 * The constructor for the addscreen interface, that creates the
	 * entire screen and all its components
	 * @param invt
	 */
	public AddScreen(Inventory invt, UPDatabase database) {
		
		//invoking super constructor
		super(invt);
		db = database.clone();
		
		
		
		//container for the JLabels and the JTextFields
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		//adding the labels and textfields to the JPanel
		for(int i = 0; i < labels.length; ++i){
			
	        addPanel.add(labels[i]);
			addPanel.add(fields[i]);
		}
		
		//JButton to submit the form
		final JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == submitButton){
					if(fields[0].getText() != " "){
						name = fields[0].getText();
						fields[0].setText(" ");
					}
					else{
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "You cannot enter a blank item name...try again");
	                	System.out.println("You cannot enter a blank item name...try again");
					}
					
					if(fields[1].getText() != null){
						quantity = fields[1].getText();
						fields[1].setText(" ");
					}
					else{
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "You cannot enter a blank quantity...try again");
	                	System.out.println("You cannot enter a blank quantity...try again");
					}
					
					if(fields[2].getText() != null){
						price = fields[2].getText();
						fields[2].setText(" ");
					}
					else{
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "You cannot enter a blank price...try again");
	                	System.out.println("You cannot enter a blank price...try again");
					}
					if(fields[3].getText() != null){
						invoicePrice = fields[3].getText();
						fields[3].setText(" ");
					}
					else{
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "You cannot enter a blank invoice price...try again");
	                	System.out.println("You cannot enter a blank invoice price...try again");
					}
					if(fields[4].getText() != null){
						description = fields[4].getText();
						fields[4].setText(" ");
					}
					else{
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "You cannot enter a blank invoice price...try again");
	                	System.out.println("You cannot enter a blank invoice price...try again");
					}
					
					//this is where the new item is finally added
					inventory.add(name, Integer.parseInt(quantity), Double.parseDouble(price), Double.parseDouble(invoicePrice));
					//to add the description
					Item[] temp = inventory.getItem();
					for(int i = 0; i <temp.length; ++i){
						if(temp[i].getName().equals(name)){
							temp[i].addDescription(description);
						}
					}
					
					utilityScreen.setVisible(false);
					InventoryGUI invtWindow = new InventoryGUI(inventory, db);
					
				}
				
			}
			
		});
		
		final JButton goBack = new JButton("Back");
		goBack.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == goBack){
					utilityScreen.setVisible(false);
					InventoryGUI iWindow = new InventoryGUI(inventory, db);
				}
				
			}
			
		});
		
		buttonPanel.add(submitButton);
		buttonPanel.add(goBack);
		super.createFrame();
		
		utilityScreen.setSize(500,400);
		utilityScreen.add(addPanel);
		utilityScreen.add(buttonPanel);
	}
}
