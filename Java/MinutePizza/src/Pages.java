import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pages extends JPanel{
	private ArrayList<String> branchlist = new ArrayList<String>();
	private CardLayout layout = new CardLayout();
	private MainPage mainpage = new MainPage();
	private BranchLogin branchlogin = new BranchLogin();
	//private JPanel cards =
	public Pages(){
		super.setLayout(layout);
		layout.addLayoutComponent(mainpage,"mainpage");
		add(mainpage);
		layout.addLayoutComponent(branchlogin,"branchlogin");
		add(branchlogin);
		//layout.addLayoutComponent("branchlogin", branchlogin);
		//layout.addLayoutComponent("mainpage", mainpage);

		
	}

	public CardLayout getLayout(){
		return layout;
	}
}