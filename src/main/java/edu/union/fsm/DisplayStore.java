package edu.union.fsm;

public class DisplayStore {
    
    int row;
    int column;
 
    DisplayNode[][] displayGraph;

    public DisplayStore(int x, int y){
	row = x;
	column = y;
	displayGraph = new DisplayNode[row][column];
	for(int i = 0; i < row; i++){
	    for(int j=0; j < column; j++){
		displayGraph[i][j] = new DisplayNode();
	    }
	}
 

    }

    public int addState(int x, int y, int ID){

	return displayGraph[x][y].setNode(ID);

	    
    }

     public int removeState(int x, int y){

	 return displayGraph[x][y].removeNode();
	
     }

    public boolean moveState(int x1,int y1,int x2,int y2){

	if(displayGraph[x2][y2].containsState()){
	    System.out.println(displayGraph[x2][y2].containsState());
	    return false;
	}
	else{
	    System.out.println(displayGraph[x2][y2].getID());
	    displayGraph[x2][y2].setNode(displayGraph[x1][y1].getID());

	    displayGraph[x1][y1].removeNode();



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
