package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Inventory;
import model.Item;
import model.UPDatabase;

/**
 * @author Joey
 * The YourCart class will display to the user the which items have been
 * chosen from the shopping cart. From this screen the user will be able 
 * to check out and update the quantity that they wish to purchase. This
 * implements KeyListener
 *
 */
public class YourCart implements KeyListener, FocusListener, Cloneable {
	
	private JFrame cart = new JFrame("Your Cart");
	
	//this will construct the labels depending on what the buyer chose to buy
	//the size of the labels is chosen by what is passed in the customer
	private JLabel[] labels, qtyLabel;
	
	//text fields to hold the quantity entered by the user
	private JTextField[] qEntry;
	
	private Inventory inventory = new Inventory();
	
	//arraylist to get the quantity entered the by the user
	private ArrayList<Integer> qty = new ArrayList<>(); 
	
	//arrayList to format the character into a string
	private ArrayList<Character> number = new ArrayList<>();
	
	private Item[] itemChoice;
	
	private UPDatabase db;
	
	/**
	 * This is the construcor for the checkout cart
	 * @param invt
	 * @param items
	 * @param choice
	 * @param database
	 */
	YourCart(Inventory invt, ArrayList<String> items, ArrayList<Item> choice, UPDatabase database){
		
		db = database.clone();
		inventory = invt.clone();
		
		//adding standard value
		/*for(int i = 0; i < choice.size(); ++i){
			qty.add(1);
		}*/
		
		//the items that have been chosen
		itemChoice = new Item[choice.size()];
		
		//for loop that populate the choices in item format
		for(int i = 0; i <choice.size(); ++i){
			itemChoice[i] = choice.get(i);
			System.out.println(itemChoice[i].getName());
		}
		
		//Jpanel to hold the items that are in the cart
		JPanel cartPanel = new JPanel();
		//cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
		cartPanel.setLayout(new FlowLayout());
		cartPanel.setAlignmentX(FlowLayout.LEFT);
		
		//JPanel to control the frame layout
		JPanel framePanel = new JPanel();
		framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));
		
		//initializing the labels and the textfields
		labels = new JLabel[items.size()];
		qtyLabel = new JLabel[labels.length];
		qEntry = new JTextField[labels.length];
		
		
		for(int i = 0; i < labels.length; ++i){
			
			labels[i] = new JLabel(items.get(i));
			qtyLabel[i] = new JLabel("Qty: ");
			qEntry[i] = new JTextField(3);
			qEntry[i].addKeyListener(this);
			qEntry[i].addFocusListener(this);
			labels[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
			//qFields[i].setMaximumSize(new Dimension(200, 25));
			cartPanel.add(labels[i]);
			cartPanel.add(qtyLabel[i]);
			cartPanel.add(qEntry[i]);	
		}
		
		final JButton checkOut = new JButton("Checkout");
		checkOut.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent checkOutEvent) {
				if(checkOutEvent.getSource() == checkOut){
					
					int check = 0;
					
					//checks to make sure qty is not greater than what is available
					for(int i = 0; i < qty.size(); ++i){
						
						System.out.println(qty.get(i));
						
						if(Integer.parseInt(itemChoice[i].getQuantity()) >= qty.get(i)){
							System.out.println("the amont enter is good ");
							check++;
						}
						else{
							System.out.println("Not enough available");
							JPanel message = new JPanel();
							qty.clear();
							check = 0;
							JOptionPane.showMessageDialog(message, "There are not enough available items for " + itemChoice[i].getName());
						}
						qEntry[i].setText(" ");
					}
					
					
					if(check <= qty.size()){
						//call the checkout screen
						CheckOut out = new CheckOut(inventory, itemChoice, qty, db);
						cart.setVisible(false);
					}
						
				}
			}
		});
		
		final JButton back = new JButton("Back");
		back.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == back){
					//will pass back the array of items chosens
					BuyerInventory bi = new BuyerInventory(inventory, db, itemChoice);
					cart.setVisible(false);
				}
				
			}
			
		});
		
		framePanel.add(cartPanel);
		framePanel.add(checkOut);
		framePanel.add(back);
		
		
		cart.setSize(600, 500);
		cart.add(framePanel);
		cart.setVisible(true);
		cart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}



	@Override
	public void keyReleased(KeyEvent textField) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent textField) {
		// TODO Auto-generated method stub
		char c = textField.getKeyChar();
		
		
	
        if(c <= '9' && c >= '0'){
        	number.add(c);
        }
            
        else
            textField.consume();
		
	}
	
	@Override
    public void keyPressed(KeyEvent textField){
       
        int key = textField.getKeyCode();
        if(key == KeyEvent.VK_ENTER ){
        	
        	int k = 0;
        	String t ="";
        	
        	for(int i = 0; i <number.size(); ++i){
        		t = t + number.get(i);
        	}
        	
        	if(t != ""){
        		number.clear();
            	k = Integer.parseInt(t);
            	qty.add(k);
        		
        	}
        	
        	
        	for(int i = 0; i < qty.size(); ++i){
        		System.out.println("The quantity entered for " + itemChoice[i].getName() + " is: " + qty.get(i));
        	}
        	
        	qty.clear();
        }
        
      //this else if will allow for you to erase things from the arraylist size that stores the number to resize the icon
        else if(key == KeyEvent.VK_BACK_SPACE){
            if(!number.isEmpty())
                number.remove(number.size()-1);
            else
                System.out.println("Nothing in the text field");
        }
        
	}



	@Override
	public void focusGained(FocusEvent textField) {
		System.out.println("focused has been gained on: " + textField.getSource().toString());
		
	}



	@Override
	public void focusLost(FocusEvent textField) {
		System.out.println("focus had been lost on: " + textField.getSource().toString());
		
		int k = 0;
		String t = "";
		
		//placing the characters into a string
		for(int i = 0; i < number.size(); ++i){
			t = t + number.get(i);
		}
		
		if(t != ""){
			k = Integer.parseInt(t);
			System.out.println("The quantity is: " + k);
			qty.add(k);			
			number.clear();
		}
		
	}
	
	
	
}
