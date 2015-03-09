package edu.union.fsm.actionlisteners;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Integer;

public class SimulateButtonListener implements ActionListener{

    private final boolean START = true;
    private final boolean NEXT = false;

    private boolean mode;
    private LinkedList<String> queue;
    private LinkedList<Integer> highlighted;
    private InformationStore informationStore;
    private SwingDisplay swingDisplay;

    /**
    * Tool which adds a state to the InformationStore.
    * @param  info ToolInfoHolder wrapper for needed information
    */
    public SimulateButtonListener(InformationStore informationStore, SwingDisplay swingDisplay){
        this.informationStore = informationStore;
        this.swingDisplay = swingDisplay;
        mode = START;
        queue = null;
        highlighted = null;
    }

    /**
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e) {
        try{

            if(mode == START) {

                queue = generateQueue();
                highlighted = new LinkedList<Integer>();
                int toAdd = informationStore.getStart();
                informationStore.highlight(toAdd);
                highlighted.add(toAdd);
                swingDisplay.updateSimulationButton("Next");
                mode = NEXT;
            } else { //MODE == NEXT
                if(!queue.isEmpty()){ //STILL MORE TRANSITIONS TO TRAVERSE.
                    informationStore.clearHighlights();
                    String nextTrans = queue.removeFirst();
                    LinkedList<Integer> toHighlight = new LinkedList<Integer>();

                    //CALCULATE THE NEXT STATES TO HIGHLIGHT (IF THERE ARE ANY)
                    while(!highlighted.isEmpty()){
                        int current = highlighted.remove().intValue();
                        LinkedList<Integer> toAdd = null;
                        try {
                            toAdd = informationStore.getNextStates(nextTrans, current);
                        } catch (StoreException ex) {}
                        if (toAdd != null) {
                            toHighlight.addAll(toAdd);
                        }
                    }
                    //INVALID TRAVERSAL: FAILED. RESTART AND INFORM USER.
                    if(toHighlight.isEmpty()) {
                        swingDisplay.clearSimulation();
                        mode = START;
                        swingDisplay.updateSimulationButton("Start");
                        swingDisplay.displayErrorMessage("Not A Valid Traversal.  Try again.");
                    }
                    //STILL A VALID TRAVERSAL. HIGHLIGHT STATES TO HIGHLIGHT AND GET READY FOR THE NEXT TRANSITION.
                    while(!toHighlight.isEmpty()){
                        int currentID = toHighlight.remove().intValue();
                        informationStore.highlight(currentID);
                        highlighted.add(currentID);
                    }
                } else{
                    //END OF TRAVERSAL: SUCCESS. RESET THE SIMULATION.
                    swingDisplay.clearSimulation();
                    swingDisplay.updateSimulationButton("Start");
                    mode = START;
                    informationStore.clearHighlights();

                    //CHECK IF ACCEPTED TRAVERSAL AND INFORM USER
                    LinkedList<String> accepts = new LinkedList<String>();
                    for(Integer id: highlighted) {
                        State current = informationStore.getState(id.intValue());
                        if(current.isAccept()){
                            accepts.add(current.getName());
                        }
                    }
                    if(accepts.isEmpty()){ //NO ACCEPT STATES HIGHLIGHTED.
                        swingDisplay.displayErrorMessage("Valid traversal, but ended on no accept states.");
                    } else{
                        String toPrint = "Valid Traversal.  Traversal was accepted at the following state(s): \n";
                        LinkedList<String> acceptNames = new LinkedList<String>();
                        for(Integer id: highlighted) {
                            State current = informationStore.getState(id.intValue());
                            if(current.isAccept()){
                                acceptNames.add(current.getName());
                            }
                        }
                        String acceptLabel = acceptNames.toString();
                        acceptLabel = acceptLabel.replaceAll("[\\[\\]]","");
                        toPrint += acceptLabel;
                        swingDisplay.displayErrorMessage(toPrint);
                    }
                }
            }

        } catch (Exception ex) {
            swingDisplay.displayErrorMessage(ex.getMessage());
        }
    }

    private LinkedList<String> generateQueue() throws Exception{
        try {
            String toParse = swingDisplay.getSimulation();
            StringTokenizer st = new StringTokenizer(toParse, ",");
            LinkedList<String> toReturn = new LinkedList<String>();
            while(st.hasMoreTokens()){
                toReturn.addLast(st.nextToken());
            }
            return toReturn;

        } catch (Exception ex) {
            throw new Exception("Invalid Simulation Input. Please check your input.");
        }
    }
}
