package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Buyer;
import model.Inventory;
import model.Seller;
import model.UPDatabase;

/**
 * This class creates the UI to a create a user that
 * will be able to access the system. The User can either
 * be a seller or buyer. This class acts as the view/controller
 * in the MVC relationship with the UPDatabase class and User
 * class. This class uses the Strategy also by using the 
 * Layout managers to build the UI.
 * @author Joey
 *
 */
public class CreateUserGUI {
    
    private JFrame createUser = new JFrame("Create a User");
	@SuppressWarnings("unused")
	private JPanel framePanel;
    private JPanel cuPanel;
    private UPDatabase database = new UPDatabase();
    
    private Inventory invt = new Inventory();
   
    
    /**
     * This is the constructor to create the UI to create
     * a user in the system
     * @param db2
     * @param invent
     */
    CreateUserGUI(UPDatabase db2, Inventory invent){
    	
    	
    	database = db2.clone();
    	invt = invent.clone();
    	
        framePanel = new JPanel(new BoxLayout(cuPanel, BoxLayout.Y_AXIS));
        cuPanel = new JPanel();
        cuPanel.setLayout(new BoxLayout(cuPanel, BoxLayout.Y_AXIS));
        
        //JLabels for the create a user
        JLabel userLabel = new JLabel("Enter username");
        JLabel passLabel = new JLabel("Enter password");
        JLabel userType = new JLabel("User Type");
        
        //radio buttons
        final JRadioButton buyerButton = new JRadioButton("Buyer");
        final JRadioButton sellerButton = new JRadioButton("Seller");
        
        //Button group for the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(sellerButton);
        group.add(buyerButton);
        
        //adding actionListeners to the radio buttons
        
        
        //text field objects that will store the user name and password
        final JTextField user = new JTextField();
        user.setMaximumSize(new Dimension(300,30));
        final JTextField password = new JTextField();
        password.setMaximumSize(new Dimension(300,30));
        
        //JButton with actionListener coded anonymously
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener (){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("The submit button is being clicked");
                String name = user.getText();
                String pass = password.getText();
                
               //checking to user name doesn't already exist
                if(database.findUser(name)){
                	JPanel message = new JPanel();
                	JOptionPane.showMessageDialog(message, "User name already in use...try again");
                	System.out.println("Username already in use...choose another name");
                	user.setText(" ");
                	password.setText(" ");
                }
              
     
                else if(buyerButton.isSelected()){
                	System.out.println("buyer selected");
                	Buyer buyerObject = new Buyer(name, pass);
                	System.out.println("Buyer name: " + buyerObject.getUsername() + "\nBuyer password: " + buyerObject.getPassword());
                	SignInScreen si = new SignInScreen(database, invt);
                	//closing the frame
                	createUser.setVisible(false);
                	
                	try {
						database.addUser(buyerObject);
					} catch (FileNotFoundException e1) {
						System.out.println("The error in CreatUser screen caused by: " + e1);
					}
                	
                	user.setText(" ");
                	password.setText(" ");
                }
                
                else if(sellerButton.isSelected()){
                	System.out.println("seller selected");
                	Seller sellerObject = new Seller(name, pass);
                	SignInScreen si = new SignInScreen(database, invt);
                	//closing the frame
                	createUser.setVisible(false);
                	
                	try {
						database.addUser(sellerObject);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                	System.out.println("Seller name: " + sellerObject.getUsername() + "\nSeller password: " + sellerObject.getPassword());
                	user.setText(" ");
                	password.setText(" ");
                }
            }

        
        });
        
        final JButton back = new JButton("Back");
        back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == back){
					SignInScreen si = new SignInScreen(database, invt);
                	//closing the frame
                	createUser.setVisible(false);
				}
				
			}
        	
        });
        
        //adding necessary components to the panel
        cuPanel.add(userLabel);
        cuPanel.add(user);
        cuPanel.add(passLabel);
        cuPanel.add(password);
        cuPanel.add(userType);
        cuPanel.add(sellerButton);
        cuPanel.add(buyerButton);
        cuPanel.add(submit);
        cuPanel.add(back);
        
        
        //setting up the frame
        createUser.setSize(400,400);
        createUser.add(cuPanel);
        createUser.setVisible(true);
        createUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //setting the default button to the submit button so that enter will click on the button
        createUser.getRootPane().setDefaultButton(submit);
        
    }
    
    
}
