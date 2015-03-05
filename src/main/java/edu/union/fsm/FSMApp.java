/**
* Main Application for SwingDisplaying finite state machines.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

public class FSMApp {

    public static void main(String[] args) {

      InformationStore informationStore = new InformationStore();

      SwingDisplay swingDisplay = new SwingDisplay(informationStore);


      Controller theController = new Controller(swingDisplay,informationStore);

        swingDisplay.setVisible(true);

    }
}
