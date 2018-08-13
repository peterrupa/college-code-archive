import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BranchLogin extends JPanel{
	JPanel panel = new JPanel();
	JLabel branch = new JLabel("Branch");
	JTextField branchfield = new JTextField(10);
	JButton enter = new JButton("Enter");
	public BranchLogin(){
		super(new BorderLayout());
		panel.setLayout(new GridLayout(1,2));
		panel.add(branch);
		panel.add(branchfield);
		add(panel, BorderLayout.NORTH);
		add(enter, BorderLayout.SOUTH);
	}

	public JButton getEnter(){
		return enter;
	}

	public JTextField getBranchField(){
		return branchfield;
	}
}