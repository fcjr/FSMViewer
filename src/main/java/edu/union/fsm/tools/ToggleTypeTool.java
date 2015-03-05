package edu.union.fsm.tool;

import edu.union.fsm.*;

public class ToggleTypeTool implements Tool{

    ToolInfoHolder info;
    Model theModel;
    View theView;
    int firstX;
    int firstY;

    public ToggleTypeTool(ToolInfoHolder info){
        this.info = info;
        this.theModel = info.getModel();
        this.theView = info.getView();
        firstX = 0;
        firstY = 0;
    }

    public void execute(){

        firstX = info.getFirstX();
        firstY = info.getFirstY();

        if(theModel.displayStore.containsState(firstX,firstY)){
            int id = theModel.displayStore.getState(firstX,firstY);
            theModel.fsmStore.toggleStateType(id);
        }
    }
}
