package edu.union.fsm;

import java.awt.event.*;
import edu.union.fsm.tools.*;

/**
 * Sets the current tool in the controller to this setters tool.
 */
public class ToolSetter implements ActionListener {

    private Tool tool;
    private Controller controller;

    /**
     * Constuctor.  Takes in the tool to set and a pointer to the controller.
     * @param   toolToSet the tool that this setter will set
     * @param   controller the controller.
     */
    public ToolSetter(Tool toolToSet, Controller controller) {
        this.tool = toolToSet;
        this.controller = controller;
    }

    /**
     * Invoked when an action occurs. sets the controllers current tool.
     * @param e The action which occured.
     */
    public void actionPerformed(ActionEvent e) {
        this.controller.setCurrentTool(this.tool);
    }
}
