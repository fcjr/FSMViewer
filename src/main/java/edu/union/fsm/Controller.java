/**
* Controller Method, manages listeners for the SwingDisplay file.
*
* @author Frank, Rudy, & Nate
* @version 1
*/

package edu.union.fsm;

import edu.union.fsm.tools.*;
import edu.union.fsm.saving.*;
import edu.union.fsm.actionlisteners.*;
import edu.union.fsm.storage.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class Controller implements MouseListener {

    private final boolean DEBUG = false;

    private SwingDisplay swingDisplay;
    private InformationStore informationStore;
    private Tool currentTool;
    private ToolInfoHolder toolInfoHolder;

    public Controller(SwingDisplay swingDisplay, InformationStore informationStore) {
        this.swingDisplay = swingDisplay;
        this.informationStore = informationStore;
        informationStore.addListener(swingDisplay);

        //Setup tool info and default tool.
        toolInfoHolder = new ToolInfoHolder(informationStore, swingDisplay);
        currentTool = new DefaultTool(toolInfoHolder);


        //Adds all of the control listeners to their respected Jpane
        populateDisplayListeners();

        //Make this a mouse listener for the display
        this.swingDisplay.addMouseListener(this);
    }

    //MOUSE EVENTS
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}


    public void mousePressed(MouseEvent e) {
        int firstX = swingDisplay.coordToCellSpot(e.getX());
        toolInfoHolder.setFirstX(firstX);

        int firstY = swingDisplay.coordToCellSpot(e.getY());
        toolInfoHolder.setFirstY(firstY);
    }
    public void mouseReleased(MouseEvent e) {
        int secondX = swingDisplay.coordToCellSpot(e.getX());
        toolInfoHolder.setSecondX(secondX);

        int secondY = swingDisplay.coordToCellSpot(e.getY());
        toolInfoHolder.setSecondY(secondY);

        executeCurrentTool();
    }

    //Other Functions

    /**
     * Sets all of the Button Liseners in the SwingDisplay to their poper ToolSetters.
     */
    private void populateDisplayListeners() {

        //Add State Button
        Tool tool = new AddStateTool(toolInfoHolder);
        ToolSetter setter = new ToolSetter(tool, this);
        this.swingDisplay.addAddStateButtonListener(setter);

        //Delete State Button
        tool = new DeleteStateTool(toolInfoHolder);
        setter = new ToolSetter(tool, this);
        this.swingDisplay.addDeleteStateButtonListener(setter);

        //Move State Button
        tool = new MoveStateTool(toolInfoHolder);
        setter = new ToolSetter(tool, this);
        this.swingDisplay.addMoveStateButtonListener(setter);

        //Toggle Type Button
        tool = new ToggleTypeTool(toolInfoHolder);
        setter = new ToolSetter(tool, this);
        this.swingDisplay.addToggleTypeButtonListener(setter);

        //Add Transition Button
        tool = new AddTransitionTool(toolInfoHolder);
        setter = new ToolSetter(tool, this);
        this.swingDisplay.addAddTransitionButtonListener(setter);

        //Delete Transition Button
        tool = new DeleteTransitionTool(toolInfoHolder);
        setter = new ToolSetter(tool, this);
        this.swingDisplay.addDeleteTransitionButtonListener(setter);

        //NON TOOL LISTENERS
        this.swingDisplay.addSaveBinButtonListener(new SaveBinButtonListener(informationStore, swingDisplay));
        this.swingDisplay.addLoadBinButtonListener(new LoadBinButtonListener(informationStore, swingDisplay));
        this.swingDisplay.addSavePNGButtonListener(new SavePNGButtonListener(swingDisplay));
    }

    /**
    * Executes the current Tool.
    */
    private void executeCurrentTool() {
        try {
            currentTool.execute();
        } catch (ToolException ex) {
            if(DEBUG) {
                System.out.println(ex);
                ex.printStackTrace();
            }
            String toPrint = ex.getMessage();
            swingDisplay.displayErrorMessage(toPrint);
        }
    }

    /**
    * Setts the currentTool of the Controller.
    * @param toolToSet the tool to set.
    */
    protected void setCurrentTool(Tool toolToSet) {
        try {
            currentTool = toolToSet;
        } catch (Exception ex) {
            if(DEBUG) {
                System.out.println(ex);
                ex.printStackTrace();
            }
            swingDisplay.displayErrorMessage("Error Selecting Tool. Please Try again.");
        }
    }

}
