package edu.union.fsm.tool;

import edu.union.fsm.*;

public class ToggleTypeTool implements Tool{

    ToolInfoHolder info;

    public ToggleTypeTool(ToolInfoHolder info){
        this.info = info;
    }

    public void execute() throws ToolException{

        InformationStore informationStore = info.getInformationStore();
        int firstX = info.getFirstX();
        int firstY = info.getFirstY();

        try {
            informationStore.toggleStateType(firstX,firstY);
        } catch(StoreException ex) {
            throw new ToolException(ex);
        }
    }
}
