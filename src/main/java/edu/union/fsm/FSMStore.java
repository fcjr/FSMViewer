package edu.union.fsm;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.*;

/**
 * FSMStore stores States, and Transitions and allows for modifications of them.
 * ListenerNotifer
 *
 * @author Frankie,rudy,nate
 */
public class FSMStore {

    private ArrayList<State> States;
    private ArrayList<Transition> Transitions;
    private int nextID;
    private Vector listeners;

    /**
     * default constructor
     */
    public FSMStore(){
        nextID = 0;
        States = new ArrayList<State>();
        Transitions = new ArrayList<Transition>();
        listeners = new Vector();
    }

    /**
     * adds the given listener to the listener vector
     * @param l the listener to add
     */
    public void addListener(ModelListener l)
    {
        listeners.add(l);
    }

    /**
     * removes the requested listener
     * @param l the listener to remove
     */
    public void removeListener(ModelListener l)
    {
        listeners.remove(l);
    }

    /**
     * calls update on all of the listeners
     */
    private void notifyListeners()
    {
        ModelListener l;
        Iterator iter = listeners.iterator();

        while(iter.hasNext()) {
            l = (ModelListener) iter.next();
            l.update();
        }

    }

    /**
     * Returns an array list of all of the Transition Objects
     * @return the arraylist of transitions
     */
    public ArrayList<Transition> getTransitions(){
        return Transitions;
    }

    /**
     * adds a standard state
     * @param  name name of the state
     * @return      the id of the state
     */
    public int addState(String name) {
        return this.addState(name,3);
    }

    /**
     * adds a state with the specified type.
     * @param name the name of the state.
     * @param type the type of the state (0 = start,1 = accept,other == regular)
     * @return  the id
     */
    public int addState(String name,int type){
        int toReturn = this.nextID;
        State toAdd = new State(nextID,name,type);
        States.add(toAdd);
        nextID++;
        return toReturn;
    }

    /**
     * toggles the type of the state
     * @param id id of the state.
     */
    public void toggleStateType(int id) {
        if (this.containsState(id)){
            State toToggle = this.getState(id);
            toToggle.toggleState();
            notifyListeners();
        }
    }


    /**
     * adds a transition.
     * adds to the list of the names if the transition exists/
     * @param name the name of the transition
     * @param fromID the id of the fromState
     * @param toID the id of the toState
     * @return id of the transition, -1 if failed to add.
     */
    public int addTransition(String name, int fromID, int toID){
        if (this.containsState(fromID) && this.containsState(toID)){
          Transition toAdd = getTransitionPrivate(fromID, toID);
          if(toAdd != null){
            toAdd.addCondition(name);
            notifyListeners();
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

    /**
     * returns the Transition object from the requested state ids
     * @param fromID id of the fromState
     * @param toID the id of the toState
     * @return  the transition, null if does not exist
     */
    private Transition getTransitionPrivate(int fromID, int toID) {
      for(Transition currentTrans: Transitions) {
        if (currentTrans.getFromID() == fromID && currentTrans.getToID() == toID){
          return currentTrans;
        }
      }
      return null;

    }

    /**
     * returns the requested transitions id, -1 if not existant.
     * @param   fromID the id of the from state
     * @param   toID the id of the to state
     * @return  id of the transition, -1 if doesnt exist.
     */
    public int getTransitionWithIDs(int fromID, int toID) {
      for(Transition currentTrans: Transitions) {
        if (currentTrans.getFromID() == fromID && currentTrans.getToID() == toID){
          return currentTrans.getID();
        }
      }
      return -1;
    }

    /**
     * returns true iff the transition exists.
     * @param   fromID the id of the fromState
     * @param   toID the id of the toState
     * @return  true iff the transition exists.
     */
    public boolean containsTransitionWithIDs(int fromID, int toID) {
      for(Transition currentTrans: Transitions) {
        if (currentTrans.getFromID() == fromID && currentTrans.getToID() == toID){
          return true;
        }
      }
      return false;
  }

  /**
   * removes the requested id
   * @param   fromID the id of the fromState
   * @param   toID the id of the toState
   * @return  true iff the remove is sucessful
   */
  public boolean removeTransitionWithIDs(int fromID, int toID) {
        if (containsTransitionWithIDs(fromID,toID)){
            int idToRemove = getTransitionWithIDs(fromID,toID);
            removeTransition(idToRemove);
            return true;
        }
        return false;
  }

    /**
     * removes the state at the given id
     * @param id id of the state to remove
     */
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

    /**
     * removes transition with the given id
     * @param id id of the transition to remove
     */
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

    /**
     * true iff the state exists
     * @param  ID id of the state
     * @return    true iff the state exists
     */
    public boolean containsState(int ID) {
        for(int i = 0;i<States.size();i++) {
            State currentState = States.get(i);
            if(currentState.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns true iff the transition exists
     * @param  ID id to check
     * @return    true iff the transition exists
     */
    public boolean containsTransition(int ID) {
        for(int i = 0;i<Transitions.size();i++) {
            Transition currentTransition = Transitions.get(i);
            if(currentTransition.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns the state of the given id, null if doesnt exist
     * @param  ID id of state to return
     * @return    state of given id
     */
    public State getState(int ID) {
        for(int i=0;i<States.size();i++) {
           State currentState = States.get(i);
           if (currentState.getID() == ID) {
                return currentState;
           }
        }
        return null;
    }

    /**
     * returns the transition of the given id, null if non existant
     * @param  ID id of transition to return
     * @return    transition at given id, null if non-existant
     */
    public Transition getTransition(int ID) {
        for(int i=0;i<Transitions.size();i++) {
           Transition currentTransition = Transitions.get(i);
           if (currentTransition.getID() == ID) {
                return currentTransition;
           }
        }
        return null;
    }

    /**
     * returns the number of state objects
     * @return   number of states
     */
    public int numStates() { return States.size();}

    /**
     * returns number of transition objects, note: transitions can have multiple names.
     * @return number of transitions
     */
    public int numTransitions() {return Transitions.size();}
}
