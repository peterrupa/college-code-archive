import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FlavorsPanel extends JPanel{
	private JPanel buttons = new JPanel();
	private JPanel flavornames = new JPanel();
	private JButton addflavor = new JButton("Add");
	private JButton deleteflavor = new JButton("Delete");
	private JLabel flavors = new JLabel("Flavors");
	private int i;

	public FlavorsPanel(){
		super(new BorderLayout());
		flavornames.setLayout(new GridLayout(5,1));
		buttons.add(addflavor);
		buttons.add(deleteflavor);
		for(i=0; i<5; i++){
			flavornames.add(new JLabel("Pizzuh" + i));
		}
		add(flavornames, BorderLayout.CENTER);
		add(buttons, BorderLayout.SOUTH);
		add(flavors, BorderLayout.NORTH);
	}

	public void updateLabels(String whatToDo){
		if(whatToDo == "add"){
			//add to list
		}
		else{
			//delete from list
		}
	}

	public JButton getAdd(){
		return addflavor;
	}

	public JButton getDelete(){
		return deleteflavor;
	}
}