package edu.union.fsm.tool;

import edu.union.fsm.*;

public class DeleteTransitionTool implements Tool{

    ToolInfoHolder info;

    public DeleteTransitionTool(ToolInfoHolder info){
        this.info = info;
    }

    public void execute() throws ToolException{

        InformationStore informationStore = info.getInformationStore();
        int firstX = info.getFirstX();
        int firstY = info.getFirstY();
        int secondX = info.getSecondX();
        int secondY = info.getSecondY();

        try{
            informationStore.removeTransition(firstX, firstY, secondX, secondY);
        } catch(StoreException ex) {
            throw new ToolException(ex);
        }

    }
}
