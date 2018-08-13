import javax.swing.*;
import java.awt.*;
import java.util.*;

public class BranchPanel extends JPanel{

	private JPanel buttons = new JPanel();
	private JPanel brancheslist = new JPanel();
	private JButton addbranch = new JButton("Add");
	private JButton deletebranch = new JButton("Delete");
	private JLabel branches = new JLabel("Branches");
	private int i;

	public BranchPanel(){
		super(new BorderLayout());
		brancheslist.setLayout(new GridLayout(5,1));
		buttons.add(addbranch);
		buttons.add(deletebranch);
		add(buttons, BorderLayout.SOUTH);
		add(branches, BorderLayout.NORTH);
		for(i=0; i<5; i++){
			brancheslist.add(new JLabel("Pizzuh" + i));
		}
		add(brancheslist, BorderLayout.CENTER);
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
		return addbranch;
	}

	public JButton getDelete(){
		return deletebranch;
	}
}