/**
* InformationStore.  Stores fsmStore and displayStore.
*
* @author Frank, Rudy, & Nate
* @version 1
*/


package edu.union.fsm;

import java.io.*;


public class InformationStore implements Serializable  {

    public FSMStore fsmStore;
    public DisplayStore displayStore;

    /**
    * default constructor.
    */
    public InformationStore(){
        fsmStore = new FSMStore();
        displayStore = new DisplayStore(8,8);

        }

public void load(InformationStore modelToLoad, InformationStoreListener l) {
    this.fsmStore = modelToLoad.fsmStore;
    this.displayStore = modelToLoad.displayStore;
    this.addListener(l);
}

public void addListener(InformationStoreListener l) {
    fsmStore.addListener(l);
    displayStore.addListener(l);
    fsmStore.notifyListeners();
    displayStore.notifyListeners();
}

public void cleanForWriting() {
    displayStore.clearListeners();
    fsmStore.clearListeners();
}

public void addState(String name, int firstX,int firstY) throws StoreException {
    if (!displayStore.containsState(firstX,firstY)){
        int id = fsmStore.addState(name);
        displayStore.addState(firstX,firstY,id);
    } else {
        throw new StoreException("Can't Add State, state already exists in position.");
    }
}

public void addTransition(String name, int firstX, int firstY, int secondX, int secondY) throws StoreException {
    if(displayStore.containsState(firstX,firstY) &&
       displayStore.containsState(secondX,secondY)) {
           int fromID = displayStore.getState(firstX,firstY);
           int toID = displayStore.getState(secondX,secondY);
           fsmStore.addTransition(name,fromID,toID);
    } else {
        throw new StoreException("Error: Not a valid transition.");
    }
}

public void removeState(int firstX,int firstY) throws StoreException{
    if(displayStore.containsState(firstX,firstY)){
        int id = displayStore.removeState(firstX,firstY);
        fsmStore.removeState(id);
    }
}

public void removeTransition(int firstX, int firstY, int secondX, int secondY) throws StoreException{
    if(displayStore.containsState(firstX,firstY) &&
       displayStore.containsState(secondX,secondY)) {
           int fromID = displayStore.getState(firstX,firstY);
           int toID = displayStore.getState(secondX,secondY);
              if (fsmStore.containsTransitionWithIDs(fromID,toID)){
                  fsmStore.removeTransitionWithIDs(fromID,toID);
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
       } else{
           throw new StoreException("Error: Not a valid move.");
       }
}

public void toggleStateType(int firstX, int firstY) throws StoreException {
    if(displayStore.containsState(firstX,firstY)){
        int id = displayStore.getState(firstX,firstY);
        fsmStore.toggleStateType(id);
    }
}

}
