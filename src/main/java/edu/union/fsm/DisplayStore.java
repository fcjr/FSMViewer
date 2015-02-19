package edu.union.fsm;

import java.util.Vector;
import java.util.*;

public class DisplayStore {

    int row;
    int column;

    DisplayNode[][] displayGraph;

    Vector listeners;

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

    public void addListener(ModelListener l)
    {
        listeners.add(l);
    }

    public void removeListener(ModelListener l)
    {
        listeners.remove(l);
    }

    private void notifyListeners()
    {
        ModelListener l;
        Iterator iter = listeners.iterator();

        while(iter.hasNext()) {
            l = (ModelListener) iter.next();
            l.update();
        }

    }

    public int getRows() {
        return row;
    }

    public int getColumns() {
        return column;
    }

    public int addState(int x, int y, int ID){

        int toReturn = displayGraph[x][y].setNode(ID);

        notifyListeners();

        return toReturn;


    }

     public int removeState(int x, int y){

         int toReturn = displayGraph[x][y].removeNode();

         notifyListeners();

	     return toReturn;


     }

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

    public int getState(int x, int y){

	       return displayGraph[x][y].getID();
    }

    public boolean containsState(int x,int y){
	       return displayGraph[x][y].containsState();
    }




}
