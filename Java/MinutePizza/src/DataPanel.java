import javax.swing.*;
import java.awt.*;
import java.util.*;

public class DataPanel extends JPanel{
	private static JLabel income = new JLabel("Income");
	private static JLabel customers = new JLabel("Customers");
	private static JTextField incomefield = new JTextField();
	private static JTextField customersfield = new JTextField();
	private static JButton next = new JButton("Next");

	public DataPanel(){
		super(new GridLayout(2,3,20,20));
		setPreferredSize(new Dimension(380,450));
		setVisible(true);
		add(income);
		add(incomefield);
		add(customers);
		add(customersfield);
	}

	public JTextField getIncomeField(){
		return incomefield;
	}

	public JTextField getCustomersField(){
		return customersfield;
	}

	public JButton getNext(){
		return next;
	}
}