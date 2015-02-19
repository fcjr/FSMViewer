/**
* Controller Method, manages listeners for the view file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Controller implements MouseListener {

  private View theView;
  private Model theModel;
  private ToolInvoker toolInvoker;
  private Tool currentTool;
  private int firstX;
  private int secondX;
  private int firstY;
  private int secondY;

  public Controller(View theView, Model theModel) {
    this.theView = theView;
    this.theModel = theModel;
    theModel.fsmStore.addListener(theView);
    theModel.displayStore.addListener(theView);


    firstX = 0;
    firstY = 0;
    secondX = 0;
    secondY = 0;
    toolInvoker = new ToolInvoker();
    currentTool = new DefaultTool();


    //Adds all of the control listeners to their respected Jpane
    this.theView.addAddStateButtonListener(new AddStateListener());
    this.theView.addDeleteStateButtonListener(new DeleteStateListener());
    this.theView.addMoveStateButtonListener(new MoveStateListener());
    this.theView.addToggleTypeButtonListener(new ToggleTypeButtonListener());
    this.theView.addAddTransitionButtonListener(new AddTransitionButtonListener());
    this.theView.addDeleteTransitionButtonListener(new DeleteTransitionButtonListener());
    this.theView.addMouseListener(this);
  }

  //MOUSE EVENTS
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
      firstX = theView.coordToCellSpot(e.getX());
      firstY = theView.coordToCellSpot(e.getY());
  }
  public void mouseReleased(MouseEvent e) {
      secondX = theView.coordToCellSpot(e.getX());
      secondY = theView.coordToCellSpot(e.getY());
      toolInvoker.runTool(currentTool);
  }

  //TOOLS
  private class DefaultTool implements Tool{
      public void execute(){
      //DO NOTHING
      }
  }

  private class AddStateTool implements Tool{
      public void execute(){
          String name = theView.getName();
          //TODO SELECT TYPE
          if (!theModel.displayStore.containsState(firstX,firstY)){
              int id = theModel.fsmStore.addState(name);
              theModel.displayStore.addState(firstX,firstY,id);
          } else {
              theView.displayErrorMessage("Can't Add State, state already exists in position.");
          }
      }
  }

  private class DeleteStateTool implements Tool{
      public void execute(){
          if(theModel.displayStore.containsState(firstX,firstY)){
              int id = theModel.displayStore.removeState(firstX,firstY);
              theModel.fsmStore.removeState(id);
          }
      }
  }

  private class MoveStateTool implements Tool{
      public void execute(){
          if(theModel.displayStore.containsState(firstX,firstY) &&
             !theModel.displayStore.containsState(secondX,secondY)) {
                 theModel.displayStore.moveState(firstX,firstY,secondX,secondY);
             } else{
                 theView.displayErrorMessage("Error: Not a valid move.");
             }
      }
  }

  private class ToggleTypeTool implements Tool{
      public void execute(){
          if(theModel.displayStore.containsState(firstX,firstY)){
              int id = theModel.displayStore.getState(firstX,firstY);
              theModel.fsmStore.toggleStateType(id);
          }
      }
  }

  private class AddTransitionTool implements Tool{
      public void execute(){

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

  private class DeleteTransitionTool implements Tool{
      public void execute(){
          if(theModel.displayStore.containsState(firstX,firstY) &&
             theModel.displayStore.containsState(secondX,secondY)) {
                 String name = theView.getName();
                 int fromID = theModel.displayStore.getState(firstX,firstY);
                 int toID = theModel.displayStore.getState(secondX,secondY);
                    if (theModel.fsmStore.containsTransitionWithIDs(fromID,toID)){
                        theModel.fsmStore.removeTransitionWithIDs(fromID,toID);
                    } else{
                        theView.displayErrorMessage("Error: No transtion exists.");
                    }
             } else {
                 theView.displayErrorMessage("Error: Invalid States.");
             }
      }
  }

  //BUTTON LISTENERS all these do are toggle the current tool.
  private class AddStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

         currentTool = new AddStateTool();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

  private class DeleteStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new DeleteStateTool();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

  private class MoveStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new MoveStateTool();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

  private class ToggleTypeButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new ToggleTypeTool();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

  private class AddTransitionButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new AddTransitionTool();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

  private class DeleteTransitionButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

          currentTool = new DeleteTransitionTool();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

}
