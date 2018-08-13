import java.awt.*;
import javax.swing.*;

class UIAnswer {
    public UIAnswer(boolean[][] state) {
        JFrame frame = new JFrame("Solution");
        
        // set layout
        frame.setLayout(new GridLayout(5, 5));
        frame.setPreferredSize(new Dimension(600,600));
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                GameButton button = new GameButton(state[i][j], i, j);
                
                frame.add(button);
            }
        }
        
        frame.pack();
        frame.setLocation(600, 0);
        frame.setVisible(true);
    }
}