package edu.union.fsm.tools;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;

public class ToggleTypeTool extends Tool{

    public ToggleTypeTool(){
        super();
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
