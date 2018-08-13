import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JPanel{
	private JPanel panel = new JPanel();
	private JRadioButton admin = new JRadioButton("Admin");
	private JRadioButton branch = new JRadioButton("Branch");
	private JButton enter = new JButton("Enter");
	private ButtonGroup group = new ButtonGroup();

	public MainPage(){
		super(new BorderLayout());
		panel.setLayout(new GridLayout(1,2));
		group.add(admin);
		group.add(branch);
		panel.add(admin);
		panel.add(branch);
		add(panel, BorderLayout.CENTER);
		add(enter, BorderLayout.SOUTH);

	}

	public JRadioButton getAdmin(){
		return admin;
	}

	public JRadioButton getBranch(){
		return branch;
	}

	public JButton getEnter(){
		return enter;
	}

}