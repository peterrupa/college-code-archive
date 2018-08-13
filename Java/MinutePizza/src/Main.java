import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main{
	private static ArrayList<String> branches = new ArrayList<String>();
	private static CardLayout layout = new CardLayout();
	private static MainPage mainpage = new MainPage();
	private static BranchLogin branchlogin = new BranchLogin();
	private static BranchPage branchpage = new BranchPage();
	private static AdminPage adminpage = new AdminPage();
	private static AddBranch addbranch = new AddBranch();
	private static AddFlavor addflavor = new AddFlavor();
	private static DeleteBranch deletebranch = new DeleteBranch();
	private static DeleteFlavor deleteflavor = new DeleteFlavor();

	private static CentralManagement MinutePizza = new CentralManagement();
	public static void main(String[] args){
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(800,600));
		frame.setLayout(layout);
		Container container = frame.getContentPane();
		layout.addLayoutComponent(mainpage,"mainpage");
		container.add(mainpage);
		layout.addLayoutComponent(branchlogin,"branchlogin");
		container.add(branchlogin);
		layout.addLayoutComponent(branchpage,"branchpage");
		container.add(branchpage);
		layout.addLayoutComponent(adminpage,"adminpage");
		container.add(adminpage);
		layout.addLayoutComponent(addbranch,"addbranch");
		container.add(addbranch);
		layout.addLayoutComponent(deletebranch,"deletebranch");
		container.add(deletebranch);
		layout.addLayoutComponent(addflavor,"addflavor");
		container.add(addflavor);
		layout.addLayoutComponent(deletebranch,"deleteflavor");
		container.add(deleteflavor);
		frame.pack();
		frame.setVisible(true);

		mainpage.getEnter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainpage.getAdmin().isSelected() == true){
					layout.show(container, "adminpage");
				}
				else if(mainpage.getBranch().isSelected() == true){
					layout.show(container, "branchlogin");
					
				}
			}
		});

		branchlogin.getEnter().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(branches.contains(branchlogin.getBranchField().getAccessibleContext()) == true){
					//branches
				}
				else{
					layout.show(container, "branchpage");
				}
			}
		});

		branchpage.getSubmit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layout.show(container, "mainpage");

			}
		});

		adminpage.getFlavorsPanel().getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "addflavor");
			}
		});

		adminpage.getBranchPanel().getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "addbranch");
			}
		});

		adminpage.getFlavorsPanel().getDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "deletebranch");
			}
		});

		adminpage.getBranchPanel().getDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "deleteflavor");
			}
		});


		adminpage.getExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				MinutePizza.saveData();
				System.exit(0);
			}
		});

		deleteflavor.getBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
			}
		});

		deletebranch.getBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
			}
		});

		deleteflavor.getDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
				MinutePizza.deleteFlavor(deleteflavor.getField().getText());
			}
		});

		deletebranch.getDelete().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
				MinutePizza.deleteBranch(deletebranch.getField().getText());
			}
		});

		addflavor.getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
				MinutePizza.addFlavor(addflavor.getField().getText(),0);
			}
		});

		addflavor.getBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
			}
		});

		addbranch.getAdd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
				MinutePizza.addBranches(addbranch.getField().getText());
			}
		});

		addbranch.getBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				layout.show(container, "adminpage");
			}
		});
	}


}