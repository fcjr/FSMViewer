package edu.union.fsm.actionlisteners;

import edu.union.fsm.*;
import edu.union.fsm.saving.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Saves the FSM when called.
 */
public class SaveBinButtonListener implements ActionListener{

    private InformationStore informationStore;
    private SwingDisplay swingDisplay;

    /**
     * Constuctor. Takes all required information needed to function.
     */
    public SaveBinButtonListener(InformationStore informationStore, SwingDisplay swingDisplay){
        this.informationStore = informationStore;
        this.swingDisplay = swingDisplay;
    }

    /**
     * Invoked when an action occurs.
     */
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
