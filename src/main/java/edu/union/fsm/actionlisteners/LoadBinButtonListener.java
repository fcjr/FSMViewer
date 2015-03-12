package edu.union.fsm.actionlisteners;

import edu.union.fsm.*;
import edu.union.fsm.saving.*;
import edu.union.fsm.storage.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Loads the FSM when called.
 */
public class LoadBinButtonListener implements ActionListener{

    private InformationStore informationStore;
    private SwingDisplay swingDisplay;
    SimulateButtonListener simulator;
    /**
     * Constuctor. Takes all required information needed to function.
     */
    public LoadBinButtonListener(InformationStore informationStore, SwingDisplay swingDisplay, SimulateButtonListener simulator){
        this.informationStore = informationStore;
        this.swingDisplay = swingDisplay;
        this.simulator = simulator;
    }

    /**
    * Invoked when an action occurs.
    */
    public void actionPerformed(ActionEvent e) {

        try {

            LoadBin loader = new LoadBin(swingDisplay);
            Object toLoad = loader.loadFile();
            InformationStore loadInformationStore = (InformationStore) toLoad;
            informationStore.load(loadInformationStore);
            simulator.clearSimulation();

        } catch(NullPointerException ex) {
            //DO NOTHING
        } catch(Exception ex){

            System.out.println(ex);
            ex.printStackTrace();

            swingDisplay.displayErrorMessage("Error Selecting Tool");
        }
    }
}
