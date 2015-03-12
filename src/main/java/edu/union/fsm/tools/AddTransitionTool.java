package edu.union.fsm.tools;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;

public class AddTransitionTool extends Tool{


    public AddTransitionTool(){
        super();
        }

    public void execute() throws ToolException{

        InformationStore informationStore = info.getInformationStore();
        SwingDisplay swingDisplay = info.getSwingDisplay();
        int firstX = info.getFirstX();
        int firstY = info.getFirstY();
        int secondX = info.getSecondX();
        int secondY = info.getSecondY();
        String name = swingDisplay.getName();
        if(name.contains(",")){
            throw new ToolException("Names cannot contain commas.  Please try again.");
        }

        try{
            informationStore.addTransition(name, firstX, firstY, secondX, secondY);
        } catch(StoreException ex) {
            throw new ToolException(ex);
        }
      }
  }
