package edu.union.fsm.tools;

import edu.union.fsm.*;

public class DeleteStateTool extends Tool{


    public DeleteStateTool(ToolInfoHolder info){
        super(info);
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
