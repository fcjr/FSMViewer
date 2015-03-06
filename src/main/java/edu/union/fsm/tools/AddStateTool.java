package edu.union.fsm.tool;

import edu.union.fsm.*;

public class AddStateTool implements Tool{


    ToolInfoHolder info;
    InformationStore informationStore;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    String name;

    /**
     * Tool which adds a state to the InformationStore.
     * @param  info ToolInfoHolder wrapper for needed information
     */
    public AddStateTool(ToolInfoHolder info){
        this.info = info;
        this.informationStore = info.getInformationStore();
        this.swingDisplay = info.getSwingDisplay();
        this.name = "";
        firstX = 0;
        firstY = 0;
    }

    /**
     * adds the state to the Information store.
     */
    public void execute() throws ToolException{

        firstX = info.getFirstX();
        firstY = info.getFirstY();
        name = swingDisplay.getName();

        try{
            informationStore.addState(name,firstX,firstY);
        } catch(StoreException ex) {
            throw new ToolException(ex);
        }

    }
}
