package edu.union.fsm.tool;

import edu.union.fsm.*;

public class AddStateTool implements Tool{


    ToolInfoHolder info;
    InformationStore informationStore;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;

    public AddStateTool(ToolInfoHolder info){
        this.info = info;
        this.informationStore = info.getInformationStore();
        this.swingDisplay = info.getSwingDisplay();
        firstX = 0;
        firstY = 0;
    }

    public void execute(){
        firstX = info.getFirstX();
        firstY = info.getFirstY();
        String name = swingDisplay.getName();
        //TODO SELECT TYPE
        if (!informationStore.displayStore.containsState(firstX,firstY)){
            int id = informationStore.fsmStore.addState(name);
            informationStore.displayStore.addState(firstX,firstY,id);
        } else {
            swingDisplay.displayErrorMessage("Can't Add State, state already exists in position.");
        }
    }
}
