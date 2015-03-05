package edu.union.fsm.tool;

import edu.union.fsm.*;

public class DeleteStateTool implements Tool{


    ToolInfoHolder info;
    Model theModel;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;

    public DeleteStateTool(ToolInfoHolder info){
        this.info = info;
        this.theModel = info.getModel();
        this.swingDisplay = info.getSwingDisplay();
        firstX = 0;
        firstY = 0;
    }

    public void execute(){

        firstX = info.getFirstX();
        firstY = info.getFirstY();

        if(theModel.displayStore.containsState(firstX,firstY)){
            int id = theModel.displayStore.removeState(firstX,firstY);
            theModel.fsmStore.removeState(id);
        }
    }
}
