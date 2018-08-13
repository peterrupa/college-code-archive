import java.util.ArrayList;

class Solver {
    /*
        Given an initial state, returns the list of coordinates to be pressed using A-Star algorithm.
    */
    public ArrayList<Coordinates> aStar(State initial) {
        ArrayList<State> openList = new ArrayList<State>();
        ArrayList<State> closedList = new ArrayList<State>();
        State bestNode;
        
        // initial state
        openList.add(initial);
        
        // list loop
        while(openList.size() > 0) {
            bestNode = removeMinF(openList);
            closedList.add(bestNode);
            
            if(bestNode.checkGoalState()) {
                return bestNode.getPrevActions();
            }
            
            // map possible actions of best node
            for(Coordinates c: bestNode.getActions()) {
                State applied = bestNode.applyAction(c);
                
                // if not in either list or in list but lesser G than duplicate
                if((notIn(applied, openList) || notIn(applied, closedList))
                   || ((in(applied, openList) || in(applied, closedList)) && (applied.getG() < getDuplicate(applied, openList, closedList).getG()))
                ) {
                    openList.add(applied);
                }
            }
        }
        
        return null;
    }
    
    /*
        Check if the given list contains the state.
    */
    private boolean in(State state, ArrayList<State> list) {
        for(State s: list) {
            if(state.equals(s)) return true;
        }
        
        return false;
    }
    
    /*
        Returns the duplicated element found in either list.
        
        NOTE: it is assumed that state is always within lists.
    */
    private State getDuplicate(State state, ArrayList<State> list1, ArrayList<State> list2) {
        for(State s: list1) {
            if(state.equals(s)) return s;
        }
        
        for(State s: list2) {
            if(state.equals(s)) return s;
        }
        
        return null;
    }
    
    /*
        Check if the given list does not contain the state.
    */
    private boolean notIn(State state, ArrayList<State> list) {
        for(State s: list) {
            if(state.equals(s)) return false;
        }
        
        return true;
    }
    
    /*
        Given an ArrayList of states, return the element with the least F.
    */
    private State removeMinF(ArrayList<State> list) {
        int min = 0;
        int minIndex = 0;
        
        for(int i = 0; i < list.size(); i++) {
            if(i == 0 || list.get(i).getF() < min) {
                min = list.get(i).getF();
                minIndex = i;
            }
        }
        
        return list.remove(minIndex);
    }
}