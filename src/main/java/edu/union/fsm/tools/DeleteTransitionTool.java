package edu.union.fsm.tool;

import edu.union.fsm.*;

public class DeleteTransitionTool implements Tool{

    ToolInfoHolder info;
    Model theModel;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public DeleteTransitionTool(ToolInfoHolder info){
        this.info = info;
        this.theModel = info.getModel();
        this.swingDisplay = info.getSwingDisplay();
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
    }

    public void execute() {

        firstX = info.getFirstX();
        firstY = info.getFirstY();
        secondX = info.getSecondX();
        secondY = info.getSecondY();

        if(theModel.displayStore.containsState(firstX,firstY) &&
           theModel.displayStore.containsState(secondX,secondY)) {
               String name = swingDisplay.getName();
               int fromID = theModel.displayStore.getState(firstX,firstY);
               int toID = theModel.displayStore.getState(secondX,secondY);
                  if (theModel.fsmStore.containsTransitionWithIDs(fromID,toID)){
                      theModel.fsmStore.removeTransitionWithIDs(fromID,toID);
                  } else{
                      swingDisplay.displayErrorMessage("Error: No transtion exists.");
                  }
           } else {
               swingDisplay.displayErrorMessage("Error: Invalid States.");
           }
    }
}
