import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class SolverUI {
    JFrame frame;
    ArrayList<Integer> answers;
    int currentIndex;
    JLabel text;
    JLabel bottomText;
    
    public SolverUI(ArrayList<Integer> answers) {
        this.answers = new ArrayList<Integer>(answers);
        currentIndex = 0;
        
        frame = new JFrame("Solution");
        frame.setLayout(new BorderLayout());
        
        text = new JLabel("Click " + answers.get(currentIndex), SwingConstants.CENTER);
        bottomText = new JLabel((currentIndex + 1) + "/" + answers.size(), SwingConstants.CENTER);
        
        frame.add(text, BorderLayout.CENTER);
        
        JPanel bottom = new JPanel(new BorderLayout());
        
        JButton prev = new JButton("<");
        
        prev.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(currentIndex > 0) {
                    currentIndex--;
                    
                    render();
                }
            }
        });
        
        JButton next = new JButton(">");
        
        next.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(currentIndex < answers.size() - 1) {
                    currentIndex++;
                    
                    render();
                }
            }
        });
        
        bottom.add(prev, BorderLayout.WEST);
        bottom.add(next, BorderLayout.EAST);
        bottom.add(bottomText, BorderLayout.CENTER);
        
        frame.add(bottom, BorderLayout.SOUTH);
        
        frame.setPreferredSize(new Dimension(600,600));
        
        frame.pack();
        frame.setLocation(600, 0);
        frame.setVisible(true);
    }
    
    public void render() {
        text.setText("Click " + answers.get(currentIndex));
        bottomText.setText((currentIndex + 1) + "/" + answers.size());
    }
}