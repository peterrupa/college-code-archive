/*
    A state-action pair, describing what actions are needed to reach the state.
*/

import java.util.ArrayList;

class ActionState {
    private ArrayList<Coordinates> actions;
    private BoardState state;
    
    public ActionState(ArrayList<Coordinates> actions, BoardState state) {
        this.actions = actions;
        this.state = state;
    }
    
    public ActionState(BoardState state) {
        actions = new ArrayList<Coordinates>();
        this.state = state;
    }
    
    public ArrayList<Coordinates> getSteps() {
        return this.actions;
    }
    
    public BoardState getState() {
        return this.state;
    }
    
    
    /*
        Maps all possible actions for the current state.
    */
    public ArrayList<ActionState> getActions() {
        ArrayList<ActionState> steps = new ArrayList<ActionState>(); 
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Coordinates step = new Coordinates(i, j);
                
                if(actions.contains(step)) {
                    continue;
                }
                
                BoardState nextState = new BoardState(this.state.getState());
                nextState.toggle(i, j);
                
                ArrayList<Coordinates> nextSteps = new ArrayList<Coordinates>(actions);
                nextSteps.add(step);
                
                steps.add(new ActionState(nextSteps, nextState));
            }
        }
        
        return steps;
    }
}