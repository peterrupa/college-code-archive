import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.io.*;

class UI {
    private static JFrame frame;
    private Random random = new Random();
    private BoardState currentState;
    private BoardState goalState;
    private GameButton[][] buttons = new GameButton[5][5];
    private JButton solveButton;
    private int movesCount;
    private JLabel movesLabel = new JLabel("Moves: 0", SwingConstants.RIGHT);
    private String instructions;
    private UIAnswer answerUI;

    public UI(){
        initializeUI();
    }

    private void initializeUI(){
        this.movesCount = 0;
        this.instructions = "Click on a light to toggle the light and its adjacent lights. Win the game by turning off all the lights. Try to do it in the fewest moves possible!";
        boolean[][] initialState = new boolean[5][5];
        
        // read initial state
        initialState = readFromFile("lightsout.in");
        
        this.currentState = new BoardState(initialState);
        
        boolean[][] goalState = new boolean[5][5];
        
        for(int i = 0; i < 5; i++) {
            Arrays.fill(goalState[i], false);
        }
        
        this.goalState = new BoardState(goalState);
        
        frame = new JFrame("Lights Out");

        // set layout
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(600,600));

        // center layout
        JPanel game = new JPanel();
        game.setLayout(new GridLayout(5, 5));

        // add buttons
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                final GameButton button = new GameButton(currentState.getState()[i][j], i, j);

                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        buttonClick(button.getRow(), button.getCol());
                        
                        // increment moves count
                        incrementMoves();
                        
                        if(checkGame(currentState)) {
                            win();
                        }
                    }
                });

                buttons[i][j] = button;

                game.add(button);
            }
        }

        // add main components
        // add padding

        JLabel instructionsLabel = new JLabel("<html><p>" + this.instructions + "</p></html>");
        instructionsLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        instructionsLabel.setBackground(new Color(50, 50, 50));
        instructionsLabel.setForeground(new Color(220, 220, 220));
        instructionsLabel.setOpaque(true);
        frame.add(instructionsLabel, BorderLayout.NORTH);


        frame.add(game, BorderLayout.CENTER);

        movesLabel.setBorder(new EmptyBorder(10, 10, 10, 20));
        movesLabel.setBackground(new Color(50, 50, 50));
        movesLabel.setForeground(new Color(220, 220, 220));
        movesLabel.setOpaque(true);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(new Color(50, 50, 50));
        
        bottomPanel.add(movesLabel, BorderLayout.EAST);
        
        solveButton = new JButton("Solve");
        
        solveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                solve();
            }
        });
        
        bottomPanel.add(solveButton, BorderLayout.WEST);
        
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        // render
        render(currentState);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void buttonClick(int i, int j){
        currentState.toggle(i, j);
        
        render(currentState);
    }
    
    private void render(BoardState state) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                buttons[i][j].setState(state.getState()[i][j]);
            }
        }
    }
    
    private ArrayList<Coordinates> BFS(BoardState initialState) {
        ArrayList<ActionState> frontiers = new ArrayList<ActionState>();
        ArrayList<Coordinates> result = new ArrayList<Coordinates>();
        ArrayList<BoardState> stateHistory = new ArrayList<BoardState>();
        int backCount = 0;
        
        frontiers.add(new ActionState(initialState));
        
        while(frontiers.size() > 0) {
            ActionState current = frontiers.remove(0);
            
            stateHistory.add(current.getState());
            
            // check if current state reached goal state
            if(checkGame(current.getState())) {
                result = current.getSteps();
                break;
            }
            
            // adds possible actions to frontiers
            for(ActionState step: current.getActions()) {
                // check if step was explored
                if(!containsState(stateHistory, step.getState())) {
                    stateHistory.add(step.getState());
                    
                    frontiers.add(step);
                }
            }
        }
        
        return result;
    }
    
    private void solve() {
        solveButton.setText("Solving");
        
        ArrayList<Coordinates> solution = BFS(this.currentState);
        
        solveButton.setText("Solve");
        
        boolean[][] answers = new boolean[5][5];
        
        for(int i = 0; i < 5; i++) {
            Arrays.fill(answers[i], false);
        }
        
        for(Coordinates step: solution) {
            answers[step.getX()][step.getY()] = true;
        }
        
        answerUI = new UIAnswer(answers);
    }

    private void setMoves(int x) {
        this.movesCount++;

        movesLabel.setText("Moves: " + this.movesCount);
    }

    private void incrementMoves() {
        this.setMoves(this.movesCount + 1);
    }

    private void win() {
        JLabel winMessage = new JLabel("You win! Moves: " + this.movesCount, SwingConstants.CENTER);

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

    /*
        checkGame() serves as the GoalTest(state);
    */
    private boolean checkGame(BoardState state) {
        return state.equal(this.goalState);
    }
    
    private void randomizeBoard() {
        for(int i = 0; i < 50; i++) {
            int x = random.nextInt(5);
            int y = random.nextInt(5);
            
            buttonClick(x, y);
        }
    }
    
    private boolean[][] readFromFile(String filename) {
        boolean[][] state = new boolean[5][5];
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)));
            String line = null;
            
            for(int i = 0; i < 5; i++) {
                line = in.readLine();
                for(int j = 0; j < 5; j++) {
                    state[i][j] = line.charAt(j * 2) == '1'? true: false;
                }
            }    
            
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return state;
    }
    
    private boolean containsState(ArrayList<BoardState> arrayAS, BoardState state) {
        for(BoardState aState: arrayAS) {
            if(state.equal(aState)) {
                return true;
            }
        }
        
        return false;
    }
}
