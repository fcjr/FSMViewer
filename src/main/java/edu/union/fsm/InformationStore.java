/**
* InformationStore.  Stores fsmStore and displayStore.
*
* @author Frank, Rudy, & Nate
* @version 1
*/


package edu.union.fsm;

import java.util.*;
import java.io.*;


public class InformationStore implements Serializable  {

    private static final int DIMENSION = 8;

    public FSMStore fsmStore;
    public DisplayStore displayStore;
    private transient Vector listeners;

    /**
    * default constructor.
    */
    public InformationStore(){
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
        if (listeners == null) {
            listeners = new Vector();
        }
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


    public void load(InformationStore modelToLoad, InformationStoreListener l) {
        this.fsmStore = modelToLoad.fsmStore;
        this.displayStore = modelToLoad.displayStore;
        this.addListener(l);
        notifyListeners();
    }

    public void cleanForWriting() {
        this.clearListeners();
    }

    public void addState(String name, int firstX,int firstY) throws StoreException {
        if (!displayStore.containsState(firstX,firstY)){
            int id = fsmStore.addState(name);
            displayStore.addState(firstX,firstY,id);
        } else {
            throw new StoreException("Can't Add State, state already exists in position.");
        }
        notifyListeners();
    }

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

    public void removeState(int firstX,int firstY) throws StoreException{
        if(displayStore.containsState(firstX,firstY)){
            int id = displayStore.removeState(firstX,firstY);
            fsmStore.removeState(id);
            notifyListeners();
        }
    }

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

    public void moveState(int firstX, int firstY, int secondX, int secondY) throws StoreException {
        if(displayStore.containsState(firstX,firstY) &&
        !displayStore.containsState(secondX,secondY)) {
            displayStore.moveState(firstX,firstY,secondX,secondY);
            notifyListeners();
        } else{
            throw new StoreException("Error: Not a valid move.");
        }
    }

    public void toggleStateType(int firstX, int firstY) throws StoreException {
        if(displayStore.containsState(firstX,firstY)){
            int id = displayStore.getState(firstX,firstY);
            fsmStore.toggleStateType(id);
            notifyListeners();
        }
    }

}
