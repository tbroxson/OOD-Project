package view;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Item;

/**
 * this class will provide the window to show the description per item
 * @author Joey
 *
 */
public class ItemDescription {
	
	//frame to contain the description
	private JFrame frame = new JFrame("Description");
	
	//JTextArea to have the description of the item
	private JTextArea dArea;
	
	/**
	 * Constructor for the ItemDescription class 
	 * @param itmName
	 */
	public ItemDescription(Item itmName){
		
		
		//panel to hold all the components for this screen
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//JLabels
		JLabel desc = new JLabel("Description");
		JLabel price = new JLabel("Price: " + itmName.getsellingPrice());
		JLabel qty = new JLabel("Available: " + itmName.getQuantity());
		
		//textarea to hold the description
		dArea = new JTextArea(itmName.getDescription());
		dArea.setLineWrap(true);
		
		panel.add(desc);
		panel.add(dArea);
		panel.add(price);
		panel.add(qty);
		
		frame.setSize(400,300);
		frame.add(panel);
		frame.setVisible(true);
	}

}
