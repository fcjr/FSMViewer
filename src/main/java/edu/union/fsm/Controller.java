/**
* Controller Method, manages listeners for the SwingDisplay file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import edu.union.fsm.tool.*;
import edu.union.fsm.saving.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Controller implements MouseListener {

  private SwingDisplay swingDisplay;
  private InformationStore informationStore;
  private Tool currentTool;
  private ToolInfoHolder toolInfoHolder;

  public Controller(SwingDisplay swingDisplay, InformationStore informationStore) {
    this.swingDisplay = swingDisplay;
    this.informationStore = informationStore;
    informationStore.fsmStore.addListener(swingDisplay);
    informationStore.displayStore.addListener(swingDisplay);


    toolInfoHolder = new ToolInfoHolder(informationStore, swingDisplay);
    currentTool = new DefaultTool();


    //Adds all of the control listeners to their respected Jpane
    this.swingDisplay.addAddStateButtonListener(new AddStateListener());
    this.swingDisplay.addDeleteStateButtonListener(new DeleteStateListener());
    this.swingDisplay.addMoveStateButtonListener(new MoveStateListener());
    this.swingDisplay.addToggleTypeButtonListener(new ToggleTypeButtonListener());
    this.swingDisplay.addAddTransitionButtonListener(new AddTransitionButtonListener());
    this.swingDisplay.addDeleteTransitionButtonListener(new DeleteTransitionButtonListener());
    this.swingDisplay.addSaveBinButtonListener(new SaveBinButtonListener());
    this.swingDisplay.addLoadBinButtonListener(new LoadBinButtonListener());
    this.swingDisplay.addSavePNGButtonListener(new SavePNGButtonListener());
    this.swingDisplay.addMouseListener(this);
  }

  //MOUSE EVENTS
  public void mouseExited(MouseEvent e) {}
  public void mouseClicked(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}

  public void mousePressed(MouseEvent e) {
      int firstX = swingDisplay.coordToCellSpot(e.getX());
      toolInfoHolder.setFirstX(firstX);

      int firstY = swingDisplay.coordToCellSpot(e.getY());
      toolInfoHolder.setFirstY(firstY);
  }
  public void mouseReleased(MouseEvent e) {
      int secondX = swingDisplay.coordToCellSpot(e.getX());
      toolInfoHolder.setSecondX(secondX);

      int secondY = swingDisplay.coordToCellSpot(e.getY());
      toolInfoHolder.setSecondY(secondY);

      try {
          currentTool.execute();
      } catch (ToolException ex) {
          String toPrint = ex.getMessage();
          swingDisplay.displayErrorMessage(toPrint);
      }
  }


  //BUTTON LISTENERS all these do are toggle the current tool.
  private class AddStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

         currentTool = new AddStateTool(toolInfoHolder);
      }

      catch(Exception ex){

        System.out.println(ex);

        swingDisplay.displayErrorMessage("Error Selecting Tool");

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

        swingDisplay.displayErrorMessage("Error Selecting Tool");

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

        swingDisplay.displayErrorMessage("Error Selecting Tool");

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

        swingDisplay.displayErrorMessage("Error Selecting Tool");

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

        swingDisplay.displayErrorMessage("Error Selecting Tool");

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

        swingDisplay.displayErrorMessage("Error Selecting Tool");
      }
    }
  }

  private class SaveBinButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

          SaveBin saver = new SaveBin(informationStore, swingDisplay);
          saver.saveFile();
          informationStore.addListener(swingDisplay);
      }

      catch(Exception ex){

         ex.printStackTrace();
        System.out.println(ex);

        swingDisplay.displayErrorMessage("Error Selecting Tool");
      }
    }
  }

  private class LoadBinButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try {

          LoadBin loader = new LoadBin(swingDisplay);
          Object toLoad = loader.loadFile();
          InformationStore loadInformationStore = (InformationStore) toLoad;
          informationStore.load(loadInformationStore, swingDisplay);

      } catch(NullPointerException ex) {
        //DO NOTHING
      } catch(Exception ex){

        System.out.println(ex);
        ex.printStackTrace();

        swingDisplay.displayErrorMessage("Error Selecting Tool");
      }
    }
  }

  private class SavePNGButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try {
          SavePNG saver = new SavePNG(swingDisplay.getMainDisplayComponent(), swingDisplay);
          saver.saveFile();
      } catch(Exception ex){

        System.out.println(ex);
        ex.printStackTrace();

        swingDisplay.displayErrorMessage("Error Selecting Tool");
      }
    }
  }


}
