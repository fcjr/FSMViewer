package edu.union.fsm;

import java.util.Vector;
import java.util.*;
import java.io.*;

/**
 * Class for storing the position information.
 */
public class DisplayStore implements Serializable {

    int row;
    int column;

    DisplayNode[][] displayGraph;

    private transient Vector listeners;

    /**
     * constructor.
     * @param   x number of rows
     * @param   y number of columns
     */
    public DisplayStore(int x, int y){
	row = x;
	column = y;
	displayGraph = new DisplayNode[row][column];
	for(int i = 0; i < row; i++){
	    for(int j=0; j < column; j++){
		displayGraph[i][j] = new DisplayNode();
	    }
	}

    listeners = new Vector();

    }

    /**
     * returns the row of a given id
     * @param  id id to check
     * @return    the row number
     */
    public int getRow(int id) {
        for(int r = 0; r < row; r++){
    	    for(int c=0; c < column; c++){
                DisplayNode current = displayGraph[r][c];
                if(current.getID() == id) {
                    return r;
                }
            }
        }
        return -1;
    }
    /**
    * returns the column of a given id
    * @param  id id to check
    * @return    the column number
    */
    public int getColumn(int id) {
        for(int r = 0; r < row; r++){
            for(int c=0; c < column; c++){
                DisplayNode current = displayGraph[r][c];
                if(current.getID() == id) {
                    return c;
                }
            }
        }
        return -1;
    }

    /**
     * adds a ModelListener to the store.
     * @param l listener to add
     */
    public void addListener(ModelListener l)
    {
        if (listeners == null) {
            listeners = new Vector();
        }
        listeners.add(l);
    }

    /**
    * removes a ModelListener to the store.
    * @param l listener to remove
    */
    public void removeListener(ModelListener l)
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
     * calls .update() on all ModelListeners
     */
    public void notifyListeners()
    {
        ModelListener l;
        Iterator iter = listeners.iterator();

        while(iter.hasNext()) {
            l = (ModelListener) iter.next();
            l.update();
        }

    }

    /**
     * return number of rows
     * @return # of rows
     */
    public int getRows() {
        return row;
    }

    /**
     * returns number of columns
     * @return # of columns
     */
    public int getColumns() {
        return column;
    }

    /**
     * adds a state to the displayStore
     * @param   x row of display
     * @param   y column of display
     * @param   ID id of state to add
     * @return  the id of the state
     */
    public int addState(int x, int y, int ID){

        int toReturn = displayGraph[x][y].setNode(ID);

        notifyListeners();

        return toReturn;


    }

    /**
    * removes a state to the displayStore
    * @param   x row of display
    * @param   y column of display
    * @return  the id of the state
    */
     public int removeState(int x, int y){

         int toReturn = displayGraph[x][y].removeNode();

         notifyListeners();

	     return toReturn;


     }

    /**
     * moves state from one pos to another
     * @param   x1 xpos of state
     * @param   y1 ypos of state
     * @param   x2 xpos to move
     * @param   y2 ypos to move
     * @return  true iff move is sucessful
     */
    public boolean moveState(int x1,int y1,int x2,int y2){

	if(displayGraph[x2][y2].containsState()){
	    //System.out.println(displayGraph[x2][y2].containsState());
	    return false;
	}
	else{
	    //System.out.println(displayGraph[x2][y2].getID());
	    displayGraph[x2][y2].setNode(displayGraph[x1][y1].getID());

	    displayGraph[x1][y1].removeNode();

        notifyListeners();

	    return true;
	}

    }

    /**
     * returns the id of the state in a given spot
     * @param   x column
     * @param   y row
     * @return  id of state
     */
    public int getState(int x, int y){

	       return displayGraph[x][y].getID();
    }

    /**
     * returns true iff the state exists.
     * @param   x column
     * @param   y row
     * @return  true iff the state exists.
     */
    public boolean containsState(int x,int y){
	       return displayGraph[x][y].containsState();
    }




}
