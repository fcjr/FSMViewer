package edu.union.fsm.tools;

import edu.union.fsm.*;

public class ToggleTypeTool extends Tool{

    public ToggleTypeTool(ToolInfoHolder info){
        super(info);
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
