package edu.union.fsm.tool;

import edu.union.fsm.*;

public class DeleteStateTool implements Tool{


    ToolInfoHolder info;

    public DeleteStateTool(ToolInfoHolder info){
        this.info = info;
    }

    public void execute() throws ToolException {

        int firstX = info.getFirstX();
        int firstY = info.getFirstY();
        InformationStore informationStore = info.getInformationStore();
        SwingDisplay swingDisplay = info.getSwingDisplay();

        try{
            informationStore.removeState(firstX,firstY);
        } catch (StoreException ex) {
            throw new ToolException(ex);
        }
    }
}
