/**
* Controller Method, manages listeners for the view file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller {

  private View theView;
  private Model theModel;

  public Controller(View theView, Model theModel) {
    this.theView = theView;
    this.theModel = theModel;


    //Adds all of the control listeners to their respected Jpane
    this.theView.addAddStateListener(new AddStateListener());
    this.theView.addDeleteStateButtonListener(new DeleteStateListener());
    this.theView.addAddTransitionButtonListener(new AddTransitionButtonListener());
    this.theView.addDeleteTransitionButtonListener(new DeleteTransitionButtonListener());
  }

  //TODO pull out these classes into separate files
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

  private class AddTransitionButtonListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        theView.displayErrorMessage("Added Transition");
        theView.clearName();
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

        theView.displayErrorMessage("Deleted State");
        theView.clearName();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

  private class AddStateListener implements ActionListener{

    public void actionPerformed(ActionEvent e) {

      try{

        theView.displayErrorMessage("Added State");
        theView.clearName();
      }

      catch(Exception ex){

        System.out.println(ex);

        theView.displayErrorMessage("AHHH");

      }

    }

  }

}
