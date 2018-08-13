import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

class State {
    final int BOARD_SIZE = 3;
    final int MIN_STEPS_TO_RANDOMIZE = 5;
    final int MAX_STEPS_TO_RANDOMIZE = 100;
    private int [][] board;
    private int g;
    private ArrayList<Coordinates> prevActions;
    
    private Random random = new Random(); 
    
    /*
        Constructor for generating board.
    */
    public State() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        this.g = 0;
        this.prevActions = new ArrayList<Coordinates>();
        
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = 3 * i + j + 1;
            }
        }
        
        this.board[2][2] = 0;
        
        int steps = random.nextInt(MAX_STEPS_TO_RANDOMIZE + 1) + MIN_STEPS_TO_RANDOMIZE;
        
        for(int i = 0; i < steps; i++) {
            ArrayList<Coordinates> actions = this.getActions();
            
            int index = random.nextInt(actions.size());
            
            this.toggle(actions.get(index));
        } 
    }
    
    /*
        Constructor for creating a predefined board, with other values blank.
    */
    public State(int[][] board) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        
        // copy paste values
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        
        this.g = 0;
        this.prevActions = new ArrayList<Coordinates>();
    }
    
    /*
        Constructor for creating a predefined board and other values.
    */
    public State(int[][] board, int g, ArrayList<Coordinates> prevActions, Coordinates c) {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        
        // copy paste values
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        
        
        this.g = g;
        this.prevActions = new ArrayList<Coordinates>(prevActions);
        
        // apply action to prevActions
        this.prevActions.add(c);
    }
    
    public int getG() {
        return this.g;
    }
    
    public int getF() {
        return g + this.getManhattanDistance();
    }
    
    /*
        Returns possible coordinates that can be toggled.
    */
    public ArrayList<Coordinates> getActions() {
        // find 0 value
        int i = 0;
        int j = 0;
        
        for(i = 0; i < BOARD_SIZE; i++) {
            boolean flag = false;
            
            for(j = 0; j < BOARD_SIZE; j++) {
                if(this.board[i][j] == 0) {
                    flag = true;
                    break;
                }
            }
            
            if(flag) {
                break;
            }
        }
        
        ArrayList<Coordinates> possibleActions = new ArrayList<Coordinates>();
        
        // check top
        if(i + 1 < BOARD_SIZE) {
            possibleActions.add(new Coordinates(i + 1, j));
        }
        
        // check bottom
        if(i - 1 >= 0) {
            possibleActions.add(new Coordinates(i - 1, j));
        }
        
        // check right
        if(j + 1 < BOARD_SIZE) {
            possibleActions.add(new Coordinates(i, j + 1));
        }
        
        // check left
        if(j - 1 >= 0) {
            possibleActions.add(new Coordinates(i, j - 1));
        }
        
        return possibleActions;
    }
    
    public ArrayList<Coordinates> getPrevActions() {
        return new ArrayList<Coordinates>(this.prevActions);
    }
    
    /*
        Returns the resulting state after performing the given action.
    */
    public State applyAction(Coordinates action) {
        int[][] appliedBoard = new int[BOARD_SIZE][BOARD_SIZE];
        
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                appliedBoard[i][j] = this.board[i][j];
            }
        }
        
        // transform the board state
        int x = action.getX();
        int y = action.getY();
        
        boolean hasSwapped = false;
        
        // check right
        if(x + 1 < BOARD_SIZE && !hasSwapped) {
            if(appliedBoard[x + 1][y] == 0) {
                swapCoordinates(appliedBoard, new Coordinates(x, y), new Coordinates(x + 1, y));
                hasSwapped = true;
            }
        }
        
        // check left
        if(x - 1 >= 0 && !hasSwapped) {
            if(appliedBoard[x - 1][y] == 0) {
                swapCoordinates(appliedBoard, new Coordinates(x, y), new Coordinates(x - 1, y));
                hasSwapped = true;
            }
        }
        
        // check bottom
        if(y + 1 < BOARD_SIZE && !hasSwapped) {
            if(appliedBoard[x][y + 1] == 0) {
                swapCoordinates(appliedBoard, new Coordinates(x, y), new Coordinates(x, y + 1));
                hasSwapped = true;
            }
        }
        
        // check top
        if(y - 1 >= 0 && !hasSwapped) {
            if(appliedBoard[x][y - 1] == 0) {
                swapCoordinates(appliedBoard, new Coordinates(x, y), new Coordinates(x, y - 1));
                hasSwapped = true;
            }
        }
        
        return new State(appliedBoard, this.g + 1, this.prevActions, action);
    }
    
    public void toggle(Coordinates action) {
        this.board = this.applyAction(action).getBoard();
    }
    
    public int[][] getBoard() {
        return this.board;
    }
    
    public boolean equals(State s) {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(this.board[i][j] != s.getBoard()[i][j]) return false;
            }
        }
        
        return true;
    }
    
    public boolean checkGoalState() {
        return this.getManhattanDistance() == 0;
    }
    
    /*
        Computes for the manhattan distance of the current board.
    */
    public int getManhattanDistance() {
        int[][] desiredBoard = new int[BOARD_SIZE][BOARD_SIZE];
        
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                desiredBoard[i][j] = 3 * i + j + 1;
            }
        }
        
        int total = 0;
        
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                if(this.board[i][j] == 1) {
                    total += Math.abs(i - 0) + Math.abs(j - 0);
                }
                
                if(this.board[i][j] == 2) {
                    total += Math.abs(i - 0) + Math.abs(j - 1);
                }
                
                if(this.board[i][j] == 3) {
                    total += Math.abs(i - 0) + Math.abs(j - 2);
                }
                
                if(this.board[i][j] == 4) {
                    total += Math.abs(i - 1) + Math.abs(j - 0);
                }
                
                if(this.board[i][j] == 5) {
                    total += Math.abs(i - 1) + Math.abs(j - 1);
                }
                
                if(this.board[i][j] == 6) {
                    total += Math.abs(i - 1) + Math.abs(j - 2);
                }
                
                if(this.board[i][j] == 7) {
                    total += Math.abs(i - 2) + Math.abs(j - 0);
                }
                
                if(this.board[i][j] == 8) {
                    total += Math.abs(i - 2) + Math.abs(j - 1);
                }
            }
        }
        
        return total;
    }
    
    /*
        Swaps 2 coordinates in the given board.
    */
    private void swapCoordinates(int[][] board, Coordinates c1, Coordinates c2) {
        int temp = board[c1.getX()][c1.getY()];
        
        board[c1.getX()][c1.getY()] = board[c2.getX()][c2.getY()]; 
        board[c2.getX()][c2.getY()] = temp;
    }
    
    // for debugging purposes
    public void print() {
        System.out.println("");
        
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println("");
        }
        
        System.out.println("g = " + this.g);
        System.out.println("h = " + this.getManhattanDistance());
        
        System.out.println("");
    }
}