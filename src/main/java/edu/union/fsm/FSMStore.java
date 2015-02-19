package edu.union.fsm;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

public class FSMStore {

    private ArrayList<State> States;
    private ArrayList<Transition> Transitions;
    private int nextID;
    private Vector listeners;

    public FSMStore(){
        nextID = 0;
        States = new ArrayList<State>();
        Transitions = new ArrayList<Transition>();
        listeners = new Vector();
    }

    public void addListener(ModelListener l)
    {
        listeners.add(l);
    }

    public void removeListener(ModelListener l)
    {
        listeners.remove(l);
    }

    private void notifyListeners()
    {
        ModelListener l;
        Iterator iter = listeners.iterator();

        while(iter.hasNext()) {
            l = (ModelListener) iter.next();
            l.update();
        }

    }

    public int addState(String name) {
        return this.addState(name,3);
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
            //SUCESSFULLY ADDED TRANSITION NOTIFY LISTENERS
            notifyListeners();
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

    public int getTransitionWithIDs(int fromID, int toID) {
      for(Transition currentTrans: Transitions) {
        if (currentTrans.getFromID() == fromID && currentTrans.getToID() == toID){
          return currentTrans.getID();
        }
      }
      return -1;
    }

    public boolean containsTransitionWithIDs(int fromID, int toID) {
      for(Transition currentTrans: Transitions) {
        if (currentTrans.getFromID() == fromID && currentTrans.getToID() == toID){
          return true;
        }
      }
      return false;
  }

  public boolean removeTransitionWithIDs(int fromID, int toID) {
        if (containsTransitionWithIDs(fromID,toID)){
            int idToRemove = getTransitionWithIDs(fromID,toID);
            removeTransition(idToRemove);
            return true;
        }
        return false;
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
        //SUCESSFULLY REMOVED TRANSITION NOTIFY LISTENERS
        notifyListeners();
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
