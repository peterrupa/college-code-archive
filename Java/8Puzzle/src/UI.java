import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class UI {
    JFrame frame;
    State currentState;
    final int BOARD_SIZE = 3;
    GameButton[][] buttons = new GameButton[BOARD_SIZE][BOARD_SIZE];
    
    public UI() {
        initializeUI();
    }
    
    private void initializeUI() {
        frame = new JFrame("8-Puzzle");
        
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(600,600));
        
        JPanel gamePanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                GameButton button = new GameButton(i, j);
                
                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        Coordinates c = new Coordinates(button.getRow(), button.getCol());
                        
                        currentState.toggle(c);
                        
                        render(currentState);
                        
                        if(currentState.checkGoalState()) {
                            win();
                        }
                    }
                });
                
                buttons[i][j] = button;
                
                gamePanel.add(button);
            }
        }
        
        JButton solveButton = new JButton("Solve");
        
        solveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Solver solver = new Solver();
        
                ArrayList<Coordinates> solution = solver.aStar(currentState);
                
                ArrayList<Integer> answers = new ArrayList<Integer>();
                State copy = new State(currentState.getBoard());
                
                for(Coordinates c: solution) {
                    answers.add(copy.getBoard()[c.getX()][c.getY()]);
                    
                    copy.toggle(c);
                }
                
                SolverUI solverUI = new SolverUI(answers);
            }
        });
        
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(solveButton, BorderLayout.SOUTH);
        
        // initialize state
        this.currentState = new State();
        
        // render state
        render(this.currentState);
        
        frame.pack();
        frame.setVisible(true);
    }
     
    private void render(State state) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                buttons[i][j].setText(currentState.getBoard()[i][j] + "");
            }
        }
    }
    
    private void win() {
        JLabel winMessage = new JLabel("You win!", SwingConstants.CENTER);

        frame.getContentPane().removeAll();
        
        frame.setLayout(new FlowLayout());

        frame.add(winMessage);
        
        JButton reset = new JButton("Play Again");
        
        reset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                initializeUI();
            }
        });
        
        frame.add(reset);
        
        frame.pack();
    }
}