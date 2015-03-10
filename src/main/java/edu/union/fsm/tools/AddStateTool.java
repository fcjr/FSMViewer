package edu.union.fsm.tools;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;

public class AddStateTool extends Tool{

    /**
     * Tool which adds a state to the InformationStore.
     * @param  info ToolInfoHolder wrapper for needed information
     */
    public AddStateTool(){
        super();
    }

    /**
     * adds the state to the Information store.
     */
    public void execute() throws ToolException{

        SwingDisplay swingDisplay = info.getSwingDisplay();
        InformationStore informationStore = info.getInformationStore();
        int firstX = info.getFirstX();
        int firstY = info.getFirstY();
        String name = swingDisplay.getName();

        try{
            informationStore.addState(name,firstX,firstY);
        } catch(StoreException ex) {
            throw new ToolException(ex);
        }

    }
}
