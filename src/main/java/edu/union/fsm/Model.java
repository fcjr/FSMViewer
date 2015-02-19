/**
* Model TODO
*
* @author Frank, Rudy, & Nate
* @version 1
*/


package edu.union.fsm;


public class Model {

public FSMStore fsmStore;
public DisplayStore displayStore;


public Model(){
    fsmStore = new FSMStore();
    displayStore = new DisplayStore(8,8);

    }


}
