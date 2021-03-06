package edu.union.fsm.tools;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;

public class DeleteTransitionTool extends Tool{

    public DeleteTransitionTool(){
        super();
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
