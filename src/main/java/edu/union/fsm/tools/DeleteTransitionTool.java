package edu.union.fsm.tool;

import edu.union.fsm.*;

public class DeleteTransitionTool implements Tool{

    ToolInfoHolder info;
    InformationStore informationStore;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public DeleteTransitionTool(ToolInfoHolder info){
        this.info = info;
        this.informationStore = info.getInformationStore();
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

        if(informationStore.displayStore.containsState(firstX,firstY) &&
           informationStore.displayStore.containsState(secondX,secondY)) {
               String name = swingDisplay.getName();
               int fromID = informationStore.displayStore.getState(firstX,firstY);
               int toID = informationStore.displayStore.getState(secondX,secondY);
                  if (informationStore.fsmStore.containsTransitionWithIDs(fromID,toID)){
                      informationStore.fsmStore.removeTransitionWithIDs(fromID,toID);
                  } else{
                      swingDisplay.displayErrorMessage("Error: No transtion exists.");
                  }
           } else {
               swingDisplay.displayErrorMessage("Error: Invalid States.");
           }
    }
}
