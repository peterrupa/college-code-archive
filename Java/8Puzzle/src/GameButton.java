import javax.swing.*;
import java.awt.*; 

class GameButton extends JButton {
    int row;
    int col;
    
    public GameButton(int row, int col) {
        super();
        
        this.row = row;
        this.col = col;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
}