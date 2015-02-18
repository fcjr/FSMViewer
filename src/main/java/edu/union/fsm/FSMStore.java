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

    public int addState(String name) {
        return this.addState(name, 0);
    }

    public int addState(String name,int type){
        int toReturn = this.nextID;
        State toAdd = new State(nextID,name,type);
        States.add(toAdd);
        nextID++;
        return toReturn;
    }


    //TODO DOESNT ADD NAMES TO EXISTING TRANSITIONS
    public int addTransition(String name, int fromID, int toID){
        if (this.containsState(fromID) && this.containsState(toID)){
          Transition toAdd = getTransitionPrivate(fromID, toID);
          if(toAdd != null){
            toAdd.addCondition(name);
            return toAdd.getID();
          } else {
            int toReturn = this.nextID;
            toAdd = new Transition(nextID,name,fromID,toID);
            Transitions.add(toAdd);
            nextID++;
            return toReturn;
          }
        } else {
        return -1;
      }
    }

    private Transition getTransitionPrivate(int fromID, int toID) {
      for(Transition currentTrans: Transitions) {
        if (currentTrans.getFromID() == fromID && currentTrans.getToID() == toID){
          return currentTrans;
        }
      }
      return null;
    }

    public void removeState(int id) {
        if(this.containsState(id)){
            for(int i=0;i<States.size();i++){
                State currentState = States.get(i);
                if(currentState.getID() == id) {
                    States.remove(i);
                }
            }
            ArrayList<Integer> transToRemove = new ArrayList<Integer>();
            for(Transition currentTrans: Transitions) {
              if(currentTrans.getToID() == id || currentTrans.getFromID() == id) {
                transToRemove.add(currentTrans.getID());
              }
            }
            for (Integer idToRemove: transToRemove) {
              this.removeTransition(idToRemove.intValue());
            }
        }
    }

    public void removeTransition(int id) {
        if(this.containsTransition(id)){
            for(int i=0;i<Transitions.size();i++){
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

    public Transition getTransition(int ID) {
        for(int i=0;i<Transitions.size();i++) {
           Transition currentTransition = Transitions.get(i);
           if (currentTransition.getID() == ID) {
                return currentTransition;
           }
        }
        return null;
    }

    public int numStates() { return States.size();}

    public int numTransitions() {return Transitions.size();}
}