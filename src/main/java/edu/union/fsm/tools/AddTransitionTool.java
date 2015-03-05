package edu.union.fsm.tool;

import edu.union.fsm.*;

public class AddTransitionTool implements Tool{


    ToolInfoHolder info;
    Model theModel;
    View theView;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public AddTransitionTool(ToolInfoHolder info){
        this.info = info;
        this.theModel = info.getModel();
        this.theView = info.getView();
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
    }

    public void execute(){

        firstX = info.getFirstX();
        firstY = info.getFirstY();
        secondX = info.getSecondX();
        secondY = info.getSecondY();

          if(theModel.displayStore.containsState(firstX,firstY) &&
             theModel.displayStore.containsState(secondX,secondY)) {
                 String name = theView.getName();
                 int fromID = theModel.displayStore.getState(firstX,firstY);
                 int toID = theModel.displayStore.getState(secondX,secondY);
                 theModel.fsmStore.addTransition(name,fromID,toID);
          } else {
              theView.displayErrorMessage("Error: Not a valid transition.");
          }
      }
  }