/**
* InformationStore.  Stores fsmStore and displayStore.
*/


package edu.union.fsm.storage;

import java.util.*;
import java.io.*;
import java.util.LinkedList;


public class InformationStoreImpl implements Serializable, InformationStore {

    private static final int DIMENSION = 8;

    private FSMStore fsmStore;
    private DisplayStore displayStore;
    private transient Vector listeners;

    /**
    * default constructor.
    */
    public InformationStoreImpl(){
        fsmStore = new FSMStore();
        displayStore = new DisplayStore(DIMENSION,DIMENSION);
        listeners = new Vector();
    }

    //LISTENER FUNCTIONS
    /**
    * adds a InformationStoreListener to the store.
    * @param l listener to add
    */
    public void addListener(InformationStoreListener l)
    {
        listeners.add(l);
    }

    /**
    * removes a InformationStoreListener to the store.
    * @param l listener to remove
    */
    public void removeListener(InformationStoreListener l)
    {
        listeners.remove(l);
    }

    /**
    * removes all Listeners
    * @param l the listener to remove
    */
    public void clearListeners()
    {
        listeners = new Vector();
    }

    /**
    * calls .update() on all InformationStoreListeners
    */
    public void notifyListeners()
    {
        InformationStoreListener l;
        Iterator iter = listeners.iterator();

        while(iter.hasNext()) {
            l = (InformationStoreListener) iter.next();
            l.update();
        }

    }

    /**
     * Loads information from a different information store into this one.
     * @param  toLoad the informationStore to load
     */
    public void load(InformationStore toLoad) {
            InformationStoreImpl toLoad2 = (InformationStoreImpl) toLoad;
            this.fsmStore = toLoad2.fsmStore;
            this.displayStore = toLoad2.displayStore;
            notifyListeners();
    }

    /**
     * Adds the given state if there is not one which exists there already.
     * @param  name name of the state
     * @param  firstX the x coord of the state to add
     * @param  firstY the y coord of the state to add
     */
    public void addState(String name, int firstX,int firstY) throws StoreException {
        if (!displayStore.containsState(firstX,firstY)){
            int id = fsmStore.addState(name);
            displayStore.addState(firstX,firstY,id);
        } else {
            throw new StoreException("Can't Add State, state already exists in position.");
        }
        notifyListeners();
    }

    /**
     * Adds the given transition if there is not one which exists there already.
     * @param  name name of the state
     * @param  firstX the 1st x coord of the transition to add
     * @param  firstY the 1st y coord of the transition to add
     * @param  secondX the 2nd x coord of the transition to add
     * @param  secondY the 2nd y coord of the transition to add
     */
    public void addTransition(String name, int firstX, int firstY, int secondX, int secondY) throws StoreException {
        if(displayStore.containsState(firstX,firstY) &&
        displayStore.containsState(secondX,secondY)) {
            int fromID = displayStore.getState(firstX,firstY);
            int toID = displayStore.getState(secondX,secondY);
            fsmStore.addTransition(name,fromID,toID);
            notifyListeners();
        } else {
            throw new StoreException("Error: Not a valid transition.");
        }
    }

    /**
     * removes the given state
     * @param  firstX x coord of the state to remove
     * @param  firstY y coord of the state to remove
     */
    public void removeState(int firstX,int firstY) throws StoreException{
        if(displayStore.containsState(firstX,firstY)){
            int id = displayStore.removeState(firstX,firstY);
            fsmStore.removeState(id);
            notifyListeners();
        }
    }

    /**
     * Removes the given transition
     * @param  firstX the 1st x coord of the transition to remove
     * @param  firstY the 1st y coord of the transition to remove
     * @param  secondX the 2nd x coord of the transition to remove
     * @param  secondY the 2nd y coord of the transition to remove
     */
    public void removeTransition(int firstX, int firstY, int secondX, int secondY) throws StoreException{
        if(displayStore.containsState(firstX,firstY) &&
        displayStore.containsState(secondX,secondY)) {
            int fromID = displayStore.getState(firstX,firstY);
            int toID = displayStore.getState(secondX,secondY);
            if (fsmStore.containsTransitionWithIDs(fromID,toID)){
                fsmStore.removeTransitionWithIDs(fromID,toID);
                notifyListeners();
            } else{
                throw new StoreException("Error: No transtion exists.");
            }
        } else {
            throw new StoreException("Error: Invalid States.");
        }
    }

    /**
     * Moves a state from one location to another.
     * @param  firstX original x coord
     * @param  firstY original y coord
     * @param  secondX new x coord
     * @param  secondY new y coord
     */
    public void moveState(int firstX, int firstY, int secondX, int secondY) throws StoreException {
        if(displayStore.containsState(firstX,firstY) &&
        !displayStore.containsState(secondX,secondY)) {
            displayStore.moveState(firstX,firstY,secondX,secondY);
            notifyListeners();
        } else{
            throw new StoreException("Error: Not a valid move.");
        }
    }

    /**
     * Toggles the type of the given state
     * @param  firstX the x coord of the state to toggle
     * @param  firstY the y coord of the state to toggle
     */
    public void toggleStateType(int firstX, int firstY) throws StoreException {
        if(displayStore.containsState(firstX,firstY)){
            int id = displayStore.getState(firstX,firstY);
            fsmStore.toggleStateType(id);

            State toggled = fsmStore.getState(id);
            if(toggled.isStart()) {
                ArrayList<State> states = fsmStore.getStates();
                for(State current: states) {
                    if(current.isStart() && toggled != current) {
                        if(current.isAccept()){
                            current.setType(State.ACCEPT);
                        } else{
                            current.setType(State.NEITHER);
                        }
                    }
                }
            }
            notifyListeners();
        }
    }

    /**
     * returns the id of the start state
     */
    public int getStart() throws StoreException {
        ArrayList<State> states = fsmStore.getStates();
        for(State current: states) {
            if(current.isStart()) {
                return current.getID();
            }
        }
        throw new StoreException("The FSM does not contain a starting state.");
    }

    /**
     * highlights the requested state.
     * @param id the id of the state to highlight
     */
    public void highlight(int id) throws StoreException {
        try{
            State toHighlight = fsmStore.getState(id);
            toHighlight.highlight();
            notifyListeners();
        } catch (Exception ex){
            throw new StoreException("Unable to highlight state.");
        }
    }

    /**
     * unhighlights the requested state.
     * @param id the id of the state to highlight
     */
    public void unhighlight(int id) throws StoreException {
        try{
            State toHighlight = fsmStore.getState(id);
            toHighlight.unhighlight();
            notifyListeners();
        } catch (Exception ex){
            throw new StoreException("Unable to unhighlight state.");
        }
    }

    /**
     * unhighlights all states.
     * @param id the id of the state to highlight
     */
    public void clearHighlights() throws StoreException {
        ArrayList<State> states = fsmStore.getStates();
        for(State current: states) {
            current.unhighlight();
        }
        notifyListeners();
    }

    /**
     * returns an iterable of the ids of next states as Integer Objects.
     * @param name name of the transition condition to follow
     * @param id id of the starting node
     * @return an iterable of the ids of next states as Integer Objects.
     */
    public LinkedList<Integer> getNextStates(String name, int id) throws StoreException {
        LinkedList<Integer> toReturn = new LinkedList<Integer>();
        ArrayList<Transition> trans = this.getTransitions();
        for(Transition current: trans){
            if(current.getFromID() == id && current.containsCondition(name)) {
                Integer toAdd = new Integer(current.getToID());
                toReturn.add(toAdd);
            }
        }
        if(!toReturn.isEmpty()) {
            return toReturn;
        } else {
            throw new StoreException("No valid transitions to follow.");
        }
    }

    /**
     * return number of rows
     * @return # of rows
     */
    public int getRows() {
        return displayStore.getRows();
    }

    /**
     * returns the row of a given id
     * @param  id id to check
     * @return    the row number
     */
    public int getRow(int id) {
        return displayStore.getRow(id);
    }

    /**
     * returns number of columns
     * @return # of columns
     */
    public int getColumns() {
        return displayStore.getColumns();
    }

    /**
     * returns the column of a given id
     * @param  id id to check
     * @return    the column number
     */
    public int getColumn(int id) {
        return displayStore.getColumn(id);
    }

    /**
     * returns true iff the state exists.
     * @param   x column
     * @param   y row
     * @return  true iff the state exists.
     */
    public boolean containsState(int row, int column) {
        return displayStore.containsState(row,column);
    }

    /**
     * returns the state at the given row and column, returns -1 if doesn't contain the state.
     * @param   x column
     * @param   y row
     * @return  the state object
     */
    public State getState(int row, int column) {
        int id = displayStore.getState(row,column);
        return fsmStore.getState(id);
    }

    /**
     * returns the state of the given id
     * @param id the id in question
     * @return  the state object
     */
    public State getState(int id){
        return fsmStore.getState(id);
    }

    /**
     * returns an arraylist of the transition objects
     * @return an arraylist of the transition objects
     */
    public ArrayList<Transition> getTransitions() {
        return fsmStore.getTransitions();
    }

}
