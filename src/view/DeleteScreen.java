package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Inventory;
import model.UPDatabase;

/**
 * This class builds the DeleteScreen for the user
 * that allows the user to delete an item from the
 * inventory. This class inherits from the SellerUI.
 * This class uses the Observer pattern and the 
 * Strategy Pattern.
 * 
 * @author Joey
 *
 */
public class DeleteScreen extends SellerUI {
	
	private UPDatabase db;

	/**
	 * The constructor that builds the delete screen.
	 * @param invt
	 */
	public DeleteScreen(Inventory invt, UPDatabase database) {
		
		super(invt);
		db = database.clone();
		
		//container for the JLabels and the JTextFields
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));
		
		//adding the labels and textfields to the JPanel
		
		fields[0].setMaximumSize(new Dimension(300, 40));
		deletePanel.add(labels[0]);
		deletePanel.add(fields[0]);
		
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
					
					//this is where the item is deleted
					if(inventory.deleteItemU(name)){
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "Delete Successful");
	                	System.out.println("Delete Successful");
	                	utilityScreen.setVisible(false);
						InventoryGUI invtWindow = new InventoryGUI(inventory, db);
					}
					else{
						JPanel message = new JPanel();
	                	JOptionPane.showMessageDialog(message, "Delete Unsuccesful...record doesnt exist");
	                	System.out.println("Delete unsuccesful");
					}
					
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
		framePanel.add(deletePanel);
		framePanel.add(buttonPanel);
		utilityScreen.add(framePanel);
	}

}
