/**
* Main Application for viewing finite state machines.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

public class FSMApp {

    public static void main(String[] args) {

      Model theModel = new Model();

      View theView = new View(theModel);


      Controller theController = new Controller(theView,theModel);

        theView.setVisible(true);

    }
}
