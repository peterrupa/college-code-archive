import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PizzaPanel extends JPanel{

	private int i = 0;
	public PizzaPanel(){
		super(new GridLayout(5,5));
		for(i=0; i<5; i++){
			add(new JLabel("Pizza Name"));
			add(new JTextField());
		}
	}
}