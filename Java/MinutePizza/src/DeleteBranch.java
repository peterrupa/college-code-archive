import javax.swing.*;
import java.awt.*;
import java.util.*;

public class DeleteBranch extends JPanel{
	private static JPanel panel = new JPanel();
	private static JLabel label = new JLabel("Branch: ");
	private static JTextField field = new JTextField();
	private static JButton delete = new JButton("Delete");
	private static JButton back = new JButton("Back");
	public DeleteBranch(){
		super(new BorderLayout());
		panel.setPreferredSize(new Dimension(100,100));
		panel.setLayout(new GridLayout(2,2,30,30));
		panel.add(label);
		panel.add(field);
		panel.add(delete);
		panel.add(back);
		add(panel, BorderLayout.SOUTH);
	}

	public JButton getDelete(){
		return delete;
	}

	public JButton getBack(){
		return back;
	}

	public JTextField getField(){
		return field;
	}
}