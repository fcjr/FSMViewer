package edu.union.fsm.tool;

import edu.union.fsm.*;

public class MoveStateTool implements Tool{



    ToolInfoHolder info;
    InformationStore informationStore;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public MoveStateTool(ToolInfoHolder info){
        this.info = info;
        this.informationStore = info.getInformationStore();
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


        if(informationStore.displayStore.containsState(firstX,firstY) &&
           !informationStore.displayStore.containsState(secondX,secondY)) {
               informationStore.displayStore.moveState(firstX,firstY,secondX,secondY);
           } else{
               swingDisplay.displayErrorMessage("Error: Not a valid move.");
           }
    }
}
