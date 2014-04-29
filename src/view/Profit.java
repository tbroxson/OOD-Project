package view;

import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Inventory;

/**
 * The Profit class acts as the view for the what is 
 * modeled by the Business class. This class will display
 * current profits, revenue, and costs for the cart.
 * @author Joey
 *
 */
public class Profit {
	
	private JFrame frame = new JFrame("Business Book");
	
	/**
	 * Constructor for the Profit class that builds the frame
	 * with the correct information.
	 * @param invt
	 */
	public Profit(Inventory invt){
		
		System.out.println("Profit is: " + invt.getProfit());
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		System.out.println("Revenue is: " + invt.getRevenue());
		
		//labels that will display the business values
		JLabel prfit = new JLabel("Profit: " + df.format(invt.getProfit()));
		JLabel cst = new JLabel("Current Cost: " + df.format(invt.getCost()));
		JLabel rev = new JLabel("Revenue: " + df.format(invt.getRevenue()));
		
		//panel to hold the Labels
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(prfit);
		panel.add(rev);
		panel.add(cst);
		
		frame.setSize(300,300);
		frame.add(panel);
		frame.setVisible(true);
		
	}
	

}
