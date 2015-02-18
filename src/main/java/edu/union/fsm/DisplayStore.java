package edu.union.fsm;

public class DisplayStore {
    
    int row = 20;
    int column = 20;
    int pixelwidth;
    int pixelheight;
    DisplayNode[][] displayGraph;

    public DisplayStore(int x, int y){

	pixelwidth = x/20;
	pixelheight = y/20;
	displayGraph = new DisplayNode[row][column];
 

    }

    public int addState(int x, int y, int ID){
	
	return displayGraph[getRow(x)][getColumn(y)].setNode(ID);

	    
    }

     public int removeState(int x, int y,){
	
	return displayGraph[getRow(x)][getColumn(y)].removeNode();
	
     }

    public boolean moveState(x1, y1, x2, y2){

	if(displayGraph[getRow(x2)][getColumn(y2)].containsState()){
	    return false;
	}
	else{
	    displayGraph[getRow(x2)][getColumn(y2)] =  displayGraph[getRow(x1)][getColumn(y1)];
	    return true;
	}

    }

    public int getState(x, y){
	return displayGraph[getRow(x)][getColumn(y)].getID();
    }

    public booean containsState(x,y){
	return displayGraph[getRow(x)][getColumn(y)].containsState();
    }

    

    //not sure the size of the buttons or whether the bottons will affect the relative postition of the pixels, 
    //so this can might need to be changed
    private int getRow(int x){
	x = x/pixelheight; 
	return x;
    }

    private int getColumn(int y){
	y = y/pixelwidth;
	return y;
    }
}
