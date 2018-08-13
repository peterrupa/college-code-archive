import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AddFlavor extends JPanel{
	private static JPanel panel = new JPanel();
	private static JLabel label = new JLabel("Flavor: ");
	private static JTextField field = new JTextField();
	private static JButton add = new JButton("Add");
	private static JButton back = new JButton("Back");
	public AddFlavor(){
		super(new BorderLayout());
		panel.setPreferredSize(new Dimension(100,100));
		panel.setLayout(new GridLayout(2,2,30,30));
		panel.add(label);
		panel.add(field);
		panel.add(add);
		panel.add(back);
		add(panel, BorderLayout.SOUTH);
	}

	public JButton getAdd(){
		return add;
	}

	public JButton getBack(){
		return back;
	}

	public JTextField getField(){
		return field;
	}
}