/*
    Contains the state of the board (true or false) with all associated methods for it.
*/

import java.util.Arrays;
import java.util.ArrayList;

class BoardState {
    private boolean[][] state = new boolean[5][5];
    
    public BoardState(boolean[][] state) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                this.state[i][j] = state[i][j];
            }
        }
    }
    
    public void setState(boolean[][] state) {
        this.state = state;
    }
    
    public boolean[][] getState() {
        return this.state;
    }
    
    /*
        Compares the state with the given state.
    */
    public boolean equal(BoardState state) {
        boolean[][] comparedState = state.getState();
        
        return Arrays.deepEquals(comparedState, this.state);
    }
    
    public void toggle(int x, int y) {
        // toggle clicked button
        state[x][y] = !state[x][y];

        // toggle north button
        if(x - 1 >= 0){
            state[x - 1][y] = !state[x - 1][y]; 
        }

        // toggle south button
        if(x + 1 < 5){
            state[x + 1][y] = !state[x + 1][y]; 
        }

        // toggle left button
        if(y - 1 >= 0) {
            state[x][y - 1] = !state[x][y - 1]; 
        }

        // toggle right button
        if(y + 1 < 5){
            state[x][y + 1] = !state[x][y + 1]; 
        }
    }
    
    public void print() {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.state[i][j]) {
                    System.out.print("1 ");
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.println("");
        }
    }
}