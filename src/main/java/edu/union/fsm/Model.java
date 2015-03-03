/**
* Model.  Stores fsmStore and displayStore.
*
* @author Frank, Rudy, & Nate
* @version 1
*/


package edu.union.fsm;

import java.io.*;


public class Model implements Serializable  {

public FSMStore fsmStore;
public DisplayStore displayStore;

/**
 * default constructor.
 */
public Model(){
    fsmStore = new FSMStore();
    displayStore = new DisplayStore(8,8);

    }

public void load(Model modelToLoad) {
    this.fsmStore = modelToLoad.fsmStore;
    this.displayStore = modelToLoad.displayStore;
    fsmStore.notifyListeners();
    displayStore.notifyListeners();
}

public void addListener(ModelListener l) {
    fsmStore.addListener(l);
    displayStore.addListener(l);
    fsmStore.notifyListeners();
    displayStore.notifyListeners();
}


}
