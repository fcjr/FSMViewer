/**
* InformationStore Inferface.  Stores fsmStore and displayStore.
*/


package edu.union.fsm.storage;

import java.util.*;
import java.io.*;
import java.util.LinkedList;


public interface InformationStore {

    //LISTENER FUNCTIONS
    /**
    * adds a InformationStoreListener to the store.
    * @param l listener to add
    */
    public void addListener(InformationStoreListener l);

    /**
    * removes a InformationStore to the store.
    * @param l listener to remove
    */
    public void removeListener(InformationStoreListener l);

    /**
    * removes all Listeners
    * @param l the listener to remove
    */
    public void clearListeners();

    /**
    * calls .update() on all InformationStoreListeners
    */
    public void notifyListeners();

    /**
     * Loads information from a different information store into this one.
     * @param  toLoad the informationStore to load
     */
    public void load(InformationStore toLoad);

    /**
     * Adds the given state if there is not one which exists there already.
     * @param  name name of the state
     * @param  firstX the x coord of the state to add
     * @param  firstY the y coord of the state to add
     */
    public void addState(String name, int firstX,int firstY) throws StoreException;

    /**
     * Adds the given transition if there is not one which exists there already.
     * @param  name name of the state
     * @param  firstX the 1st x coord of the transition to add
     * @param  firstY the 1st y coord of the transition to add
     * @param  secondX the 2nd x coord of the transition to add
     * @param  secondY the 2nd y coord of the transition to add
     */
    public void addTransition(String name, int firstX, int firstY, int secondX, int secondY) throws StoreException;

    /**
     * removes the given state
     * @param  firstX x coord of the state to remove
     * @param  firstY y coord of the state to remove
     */
    public void removeState(int firstX,int firstY) throws StoreException;

    /**
     * Removes the given transition
     * @param  firstX the 1st x coord of the transition to remove
     * @param  firstY the 1st y coord of the transition to remove
     * @param  secondX the 2nd x coord of the transition to remove
     * @param  secondY the 2nd y coord of the transition to remove
     */
    public void removeTransition(int firstX, int firstY, int secondX, int secondY) throws StoreException;

    /**
     * Moves a state from one location to another.
     * @param  firstX original x coord
     * @param  firstY original y coord
     * @param  secondX new x coord
     * @param  secondY new y coord
     */
    public void moveState(int firstX, int firstY, int secondX, int secondY) throws StoreException;

    /**
     * Toggles the type of the given state
     * @param  firstX the x coord of the state to toggle
     * @param  firstY the y coord of the state to toggle
     */
    public void toggleStateType(int firstX, int firstY) throws StoreException;

    /**
     * returns the id of the start state
     */
    public int getStart() throws StoreException;

    /**
     * highlights the requested state.
     * @param id the id of the state to highlight
     */
    public void highlight(int id) throws StoreException;

    /**
     * unhighlights the requested state.
     * @param id the id of the state to highlight
     */
    public void unhighlight(int id) throws StoreException;

    /**
     * unhighlights all states.
     * @param id the id of the state to highlight
     */
    public void clearHighlights() throws StoreException;

    /**
     * returns an iterable of the ids of next states as Integer Objects.
     * @param name name of the transition condition to follow
     * @param id id of the starting node
     * @return an iterable of the ids of next states as Integer Objects.
     */
    public LinkedList<Integer> getNextStates(String name, int id) throws StoreException;
    /**
     * return number of rows
     * @return # of rows
     */
    public int getRows();

    /**
     * returns the row of a given id
     * @param  id id to check
     * @return    the row number
     */
    public int getRow(int id);

    /**
     * returns number of columns
     * @return # of columns
     */
    public int getColumns();

    /**
     * returns the column of a given id
     * @param  id id to check
     * @return    the column number
     */
    public int getColumn(int id);

    /**
     * returns true iff the state exists.
     * @param   x column
     * @param   y row
     * @return  true iff the state exists.
     */
    public boolean containsState(int row, int column);

    /**
     * returns the state at the given row and column, returns -1 if doesn't contain the state.
     * @param   x column
     * @param   y row
     * @return  the state object
     */
    public State getState(int row, int column);

    /**
     * returns the state of the given id
     * @param id the id in question
     * @return  the state object
     */
    public State getState(int id);

    /**
     * returns an arraylist of the transition objects
     * @return an arraylist of the transition objects
     */
    public ArrayList<Transition> getTransitions();

}
