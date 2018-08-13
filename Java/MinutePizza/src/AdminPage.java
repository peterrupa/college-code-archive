import javax.swing.*;
import java.awt.*;
import java.util.*;

public class AdminPage extends JPanel{
	private static BranchPanel branchpanel = new BranchPanel();
	private static FlavorsPanel flavorspanel = new FlavorsPanel();
	private JButton summary = new JButton("View Summary Report");
	private JButton exit = new JButton("Exit");
	private JPanel buttons = new JPanel();
	private JPanel panels = new JPanel();
	public AdminPage(){
		super(new BorderLayout());
		panels.setLayout(new GridLayout(1,2,50,50));
		panels.add(branchpanel);
		panels.add(flavorspanel);
		add(panels, BorderLayout.CENTER);
		buttons.add(summary);
		buttons.add(exit);
		add(buttons, BorderLayout.SOUTH);
	}

	public BranchPanel getBranchPanel(){
		return branchpanel;
	}

	public FlavorsPanel getFlavorsPanel(){
		return flavorspanel;
	}

	public JButton getSummary(){
		return summary;
	}

	public JButton getExit(){
		return exit;
	}
	
}