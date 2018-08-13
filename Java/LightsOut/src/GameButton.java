import javax.swing.JButton;
import java.awt.Color;

class GameButton extends JButton {
    private boolean state;
    private int row, col;
    private final Color WHITE = new Color(220, 220, 220);
    private final Color BLACK = new Color(20, 20, 20);

    public GameButton(boolean state, int row, int col){
        super();
        this.state = state;
        this.row = row;
        this.col = col;

        this.setBackground(state? WHITE: BLACK);
    }

    public boolean getState(){
        return this.state;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

    public void setState(boolean state){
        this.setBackground(state? WHITE: BLACK);
    }
}
