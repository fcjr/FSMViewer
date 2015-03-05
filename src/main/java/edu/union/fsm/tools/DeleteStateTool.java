package edu.union.fsm.tool;

import edu.union.fsm.*;

public class DeleteStateTool implements Tool{


    ToolInfoHolder info;
    InformationStore informationStore;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;

    public DeleteStateTool(ToolInfoHolder info){
        this.info = info;
        this.informationStore = info.getInformationStore();
        this.swingDisplay = info.getSwingDisplay();
        firstX = 0;
        firstY = 0;
    }

    public void execute(){

        firstX = info.getFirstX();
        firstY = info.getFirstY();

        if(informationStore.displayStore.containsState(firstX,firstY)){
            int id = informationStore.displayStore.removeState(firstX,firstY);
            informationStore.fsmStore.removeState(id);
        }
    }
}
