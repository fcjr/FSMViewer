package edu.union.fsm.actionlisteners;

import edu.union.fsm.*;
import edu.union.fsm.saving.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Save a PNG of the FSM when called.
 */
public class SavePNGButtonListener implements ActionListener{

    private SwingDisplay swingDisplay;

    /**
     * Constuctor. Takes all required information needed to function.
     */
    public SavePNGButtonListener(SwingDisplay swingDisplay){
        this.swingDisplay = swingDisplay;
    }

    /**
    * Invoked when an action occurs.
    */
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
