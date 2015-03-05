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


}
