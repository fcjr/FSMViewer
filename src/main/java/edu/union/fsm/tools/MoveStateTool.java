package edu.union.fsm.tools;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;

public class MoveStateTool extends Tool{


    public MoveStateTool(ToolInfoHolder info){
        super(info);
    }

    public void execute() throws ToolException{

        InformationStore informationStore = info.getInformationStore();
        int firstX = info.getFirstX();
        int firstY = info.getFirstY();
        int secondX = info.getSecondX();
        int secondY = info.getSecondY();

        try {
            informationStore.moveState(firstX,firstY,secondX,secondY);
        } catch (StoreException ex){
            throw new ToolException(ex);
        }
    }
}
