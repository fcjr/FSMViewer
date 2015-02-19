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
    this.theView.addAddStateListener(new AddStateListener());
    this.theView.addDeleteStateButtonListener(new DeleteStateListener());
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
          int id = theModel.fsmStore.addState(name);
          theModel.displayStore.addState(firstX,firstY,id);
      }
  }

  private class DeleteStateTool implements Tool{
      public void execute(){
          //DO NOTHING
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
        theView.clearName();
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

        currentTool = new AddStateTool();
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

        theView.displayErrorMessage("Deleted Transition");
        theView.clearName();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

}
