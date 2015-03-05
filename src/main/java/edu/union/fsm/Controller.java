/**
* Controller Method, manages listeners for the view file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import edu.union.fsm.tool.*;;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Controller implements MouseListener {

  private View theView;
  private Model theModel;
  private Tool currentTool;
  private ToolInfoHolder toolInfoHolder;

  public Controller(View theView, Model theModel) {
    this.theView = theView;
    this.theModel = theModel;
    theModel.fsmStore.addListener(theView);
    theModel.displayStore.addListener(theView);


    toolInfoHolder = new ToolInfoHolder(theModel, theView);
    currentTool = new DefaultTool();


    //Adds all of the control listeners to their respected Jpane
    this.theView.addAddStateButtonListener(new AddStateListener());
    this.theView.addDeleteStateButtonListener(new DeleteStateListener());
    this.theView.addMoveStateButtonListener(new MoveStateListener());
    this.theView.addToggleTypeButtonListener(new ToggleTypeButtonListener());
    this.theView.addAddTransitionButtonListener(new AddTransitionButtonListener());
    this.theView.addDeleteTransitionButtonListener(new DeleteTransitionButtonListener());
    this.theView.addSaveBinButtonListener(new SaveBinButtonListener());
    this.theView.addLoadBinButtonListener(new LoadBinButtonListener());
    this.theView.addSavePNGButtonListener(new SavePNGButtonListener());
    this.theView.addMouseListener(this);
  }

  //MOUSE EVENTS
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
      int firstX = theView.coordToCellSpot(e.getX());
      toolInfoHolder.setFirstX(firstX);

      int firstY = theView.coordToCellSpot(e.getY());
      toolInfoHolder.setFirstY(firstY);
  }
  public void mouseReleased(MouseEvent e) {
      int secondX = theView.coordToCellSpot(e.getX());
      toolInfoHolder.setSecondX(secondX);

      int secondY = theView.coordToCellSpot(e.getY());
      toolInfoHolder.setSecondY(secondY);

      currentTool.execute();
  }


  //BUTTON LISTENERS all these do are toggle the current tool.
  private class AddStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

         currentTool = new AddStateTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");

      }

    }

  }

  private class DeleteStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new DeleteStateTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");

      }

    }

  }

  private class MoveStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new MoveStateTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");

      }

    }

  }

  private class ToggleTypeButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new ToggleTypeTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");

      }

    }

  }

  private class AddTransitionButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        currentTool = new AddTransitionTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");

      }

    }

  }

  private class DeleteTransitionButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

          currentTool = new DeleteTransitionTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");
      }
    }
  }

  private class SaveBinButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

          SaveBin saver = new SaveBin(theModel, theView);
          saver.saveFile();
          theModel.addListener(theView);
      }

      catch(Exception ex){

         ex.printStackTrace();
        System.out.println(ex);

        theView.displayErrorMessage("Error Selecting Tool");
      }
    }
  }

  private class LoadBinButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try {
          LoadBin loader = new LoadBin(theModel, theView);
          loader.loadFile();
          theModel.addListener(theView);
      } catch(Exception ex){

        System.out.println(ex);
        ex.printStackTrace();

        theView.displayErrorMessage("Error Selecting Tool");
      }
    }
  }

  private class SavePNGButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try {
          SavePNG saver = new SavePNG(theView.getMainDisplayComponent(), theView);
          saver.saveFile();
      } catch(Exception ex){

        System.out.println(ex);
        ex.printStackTrace();

        theView.displayErrorMessage("Error Selecting Tool");
      }
    }
  }


}
