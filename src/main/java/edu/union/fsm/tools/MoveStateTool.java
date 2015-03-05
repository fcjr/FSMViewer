package edu.union.fsm.tool;

import edu.union.fsm.*;

public class MoveStateTool implements Tool{



    ToolInfoHolder info;
    Model theModel;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public MoveStateTool(ToolInfoHolder info){
        this.info = info;
        this.theModel = info.getModel();
        this.swingDisplay = info.getSwingDisplay();
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
    }

    public void execute(){

        firstX = info.getFirstX();
        firstY = info.getFirstY();
        secondX = info.getSecondX();
        secondY = info.getSecondY();


        if(theModel.displayStore.containsState(firstX,firstY) &&
           !theModel.displayStore.containsState(secondX,secondY)) {
               theModel.displayStore.moveState(firstX,firstY,secondX,secondY);
           } else{
               swingDisplay.displayErrorMessage("Error: Not a valid move.");
           }
    }
}
