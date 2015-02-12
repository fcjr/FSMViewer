package edu.union.fsm;

import java.util.Iterator;
import java.util.ArrayList;

public class FSMStore {

    private ArrayList<State> States;
    private ArrayList<Transition> Transitions;
    private int nextID;

    public FSMStore(){
        nextID = 0;
        States = new ArrayList<State>();
        Transitions = new ArrayList<Transition>();
    }

    public int addState(String name,int type){
        int toReturn = this.nextID;
        State toAdd = new State(nextID,name,type);
        States.add(toAdd);
        nextID++;
        return toReturn;
    }

    public int addTransition(String name, int fromID, int toID){ 
        int toReturn = this.nextID;
        Transition toAdd = new Transition(nextID,name,fromID,toID);
        Transitions.add(toAdd);
        nextID++;
        return toReturn;
    }

    public void removeState(int id) {
        if(this.containsState(id)){
            for(int i=0;i>States.size();i++){
                State currentState = States.get(i);
                if(currentState.getID() == id) {
                    States.remove(i);
                }
            }
            Iterator<Transition> iter = Transitions.iterator();
            while (iter.hasNext()) {
                Transition current = iter.next();
                if(current.getToID() == id || current.getFromID() == id) {
                    iter.remove();
                }
            }
        }
        }

    public void removeTransition(int id) {
        if(this.containsTransition(id)){
            for(int i=0;i>Transitions.size();i++){
                Transition currentTransition = Transitions.get(i);
                if(currentTransition.getID() == id) {
                    Transitions.remove(i);
                }
            }
        }
    }


    public boolean containsState(int ID) {
        for(int i = 0;i<States.size();i++) {
            State currentState = States.get(i);
            if(currentState.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public boolean containsTransition(int ID) { 
        for(int i = 0;i<Transitions.size();i++) {
            Transition currentTransition = Transitions.get(i);
            if(currentTransition.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public State getState(int ID) {
        for(int i=0;i<States.size();i++) {
           State currentState = States.get(i);
           if (currentState.getID() == ID) {
                return currentState;
           }
        }
        return null;
    }

    public Transition getTransition(int ID){ return null;}

    public int numStates() { return 0;}

    public int numTransitions() {return 0;}
}
