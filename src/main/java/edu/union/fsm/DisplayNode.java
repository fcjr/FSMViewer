import stuff...;


public class DisplayNode {
    


    private boolean hasState;
    private int myID;
    
    public DisplayNode(){
	myType = false;
	myID = -1;
    }
    
    public int containsState(){
	return hasState;
    }

    public int getID(){
	return myID();
    }
    
    public int setNode(int ID){
	hasState = true;
	myID = ID;
	return myID;
    }

    public int removeNode(){
	hasState = false;
	int temp = myID;
	myID = -1;
	return temp;
    }

}
