/**
* Main Application for viewing finite state machines.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

public class FSMApp {

    public static void main(String[] args) {

      View theView = new View();

      Model theModel = new Model();

      Controller theController = new Controller(theView,theModel);

        theView.setVisible(true);

    }
}
