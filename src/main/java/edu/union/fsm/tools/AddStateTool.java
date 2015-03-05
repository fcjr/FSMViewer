package edu.union.fsm.tool;

import edu.union.fsm.*;

public class AddStateTool implements Tool{


    ToolInfoHolder info;
    Model theModel;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;

    public AddStateTool(ToolInfoHolder info){
        this.info = info;
        this.theModel = info.getModel();
        this.swingDisplay = info.getSwingDisplay();
        firstX = 0;
        firstY = 0;
    }

    public void execute(){
        firstX = info.getFirstX();
        firstY = info.getFirstY();
        String name = swingDisplay.getName();
        //TODO SELECT TYPE
        if (!theModel.displayStore.containsState(firstX,firstY)){
            int id = theModel.fsmStore.addState(name);
            theModel.displayStore.addState(firstX,firstY,id);
        } else {
            swingDisplay.displayErrorMessage("Can't Add State, state already exists in position.");
        }
    }
}
