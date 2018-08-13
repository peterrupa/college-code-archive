import javax.swing.*;
import java.awt.*;
import java.util.*;


public class BranchPage extends JPanel{

	private static JPanel panel = new JPanel();
	private static BorderLayout layout = new BorderLayout();
	private JButton databutton = new JButton("Data");
	private JButton flavorsbutton = new JButton("Flavors");
	private DataPanel datapanel = new DataPanel();
	private PizzaPanel pizzapanel = new PizzaPanel();
	private JButton submit = new JButton("Submit");
	public BranchPage(){
		super(layout);
		//add(logo, BorderLayout.NORTH);
		add(datapanel, BorderLayout.WEST);
		add(pizzapanel, BorderLayout.CENTER);
		add(submit, BorderLayout.SOUTH);
	}

	public JButton getSubmit(){
		return submit;
	}

	public DataPanel getDataPanel(){
		return datapanel;
	}

	public PizzaPanel getPizzaPanel(){
		return pizzapanel;
	}

}