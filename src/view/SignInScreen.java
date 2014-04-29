package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Inventory;
import model.UPDatabase;

/**
 * This class will build the GUI for the sign in screen.
 * This screen implements a KeyListener interface
 * @author Joey
 */
public class SignInScreen implements KeyListener {
    
	private UPDatabase database = new UPDatabase();
    private final JFrame siScreen = new JFrame("Welcome");
    private Inventory invt = new Inventory();
    
    private JTextField userName = new JTextField();
    private JTextField password = new JTextField();
    
    /**
     * Constructor for the SignInScreen class
     * @param db
     * @param inventory
     */
    public SignInScreen(UPDatabase db, Inventory inventory){
        
    	//setting up the database thats passed in
    	database = db.clone();
    	
    	//setting up the inventory that is passed in
    	invt = inventory.clone();
    	
        JPanel siPanel = new JPanel();
        siPanel.setLayout(new BoxLayout(siPanel, BoxLayout.Y_AXIS));
        
        //adding JLabels
        JLabel enterUsername = new JLabel("Username");
        enterUsername.setPreferredSize(new Dimension(100,50));
        enterUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        JLabel enterPassword = new JLabel("Password");
        enterPassword.setPreferredSize(new Dimension(100,50));
        enterPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        //setting up the dimensions of textfields
        userName.setMaximumSize(new Dimension(300, 40));
        password.setMaximumSize(new Dimension(300, 40));
        
        //adding create user jbutton
        final JButton createButton = new JButton("Create User");
        final JButton signIn = new JButton("Sign In");
         
        //add the actionlisetener and code the event anomously
        signIn.addActionListener(new ActionListener(){
			
        @SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e1) {
				if(e1.getSource() == signIn){
					
					//check for seller or buyer and determine which screen to call
					String type = (database.getType(userName.getText()));
					
					if(database.getType(userName.getText()).equals("seller")){
						
						InventoryGUI invtGUI = new InventoryGUI(invt, database);
						
						System.out.println("Submit button is working");
						
						siScreen.setVisible(false);
						
						String unpass = userName.getText() + " " + password.getText();
						
						System.out.println(unpass);
					}
					else{
						//here is where we call the BuyerUI
						siScreen.setVisible(false);
						BuyerInventory bWindow = new BuyerInventory(invt, database, null);
						
					}
				}
        }
										
        	
       });
        
        //adding a keylistener to the signIN button	
        signIn.addKeyListener(this);
        createButton.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource() == createButton){
                    System.out.println("Action Listener is working");
                    siScreen.setVisible(false);
                    CreateUserGUI screen = new CreateUserGUI(database, invt);
                }
            }
        });
        
        siPanel.add(enterUsername);
        siPanel.add(userName);
        siPanel.add(enterPassword);
        siPanel.add(password);
        siPanel.add(signIn);
        siPanel.add(createButton);
        
         
        //setting the specifications for the window
        siScreen.setLayout(new BorderLayout());
        siScreen.setSize(400, 400);
        siScreen.add(siPanel, BorderLayout.CENTER);
        siScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        siScreen.setVisible(true);
        
    }

	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER){
			
			//check for seller or buyer and determine which screen to call
			String type = (database.getType(userName.getText()));
			
			if(database.getType(userName.getText()).equals("seller")){
				
				siScreen.setVisible(false);
				
				InventoryGUI invtGUI = new InventoryGUI(invt, database);
				
				System.out.println("Submit button is working");
				
				siScreen.setVisible(false);
				
				String unpass = userName.getText() + " " + password.getText();
				
				System.out.println(unpass);
			}
			else{
				//here is where we call the BuyerUI...below is test frame to make sure logic works and it does
				BuyerInventory bWindow = new BuyerInventory(invt, database, null);
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
}
