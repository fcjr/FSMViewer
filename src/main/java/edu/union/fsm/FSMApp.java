/**
* Main Application for SwingDisplaying finite state machines.
*/

package edu.union.fsm;
import edu.union.fsm.storage.*;

public class FSMApp {

    public static void main(String[] args) {

      InformationStore informationStore = new InformationStoreImpl();

      SwingDisplay swingDisplay = new SwingDisplay(informationStore);


      Controller theController = new Controller(swingDisplay,informationStore);

        swingDisplay.setVisible(true);

    }
}
