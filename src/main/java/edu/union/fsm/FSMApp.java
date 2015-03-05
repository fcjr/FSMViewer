/**
* Main Application for SwingDisplaying finite state machines.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

public class FSMApp {

    public static void main(String[] args) {

      Model theModel = new Model();

      SwingDisplay swingDisplay = new SwingDisplay(theModel);


      Controller theController = new Controller(swingDisplay,theModel);

        swingDisplay.setVisible(true);

    }
}
