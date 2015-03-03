package edu.union.fsm;

import java.io.*;

/**
 * Display Node for storing information in the DisplayStore.
 */
public class DisplayNode implements Serializable {



    public boolean hasState;
    public int myID;

    public DisplayNode(){
	hasState = false;
	myID = -1;
    }

    /**
     * returns true iff there is a state in the node
     * @return treu iff there is a state in the node
     */
    public boolean containsState(){
	return hasState;
    }

    /**
     * returns id of the state
     * @return id of the state, -1 if no state
     */
    public int getID(){
	return myID;
    }

    /**
     * sets the id of the state stored
     * @param  ID id of the state to store
     * @return    the id.
     */
    public int setNode(int ID){
	hasState = true;
	myID = ID;
	return myID;
    }

    /**
     * removes state from the node
     * @return id of the state
     */
    public int removeNode(){
	hasState = false;
	int temp = myID;
	myID = -1;
	return temp;
    }

}
