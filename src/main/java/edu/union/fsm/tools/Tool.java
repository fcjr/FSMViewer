package edu.union.fsm.tools;

//Abstract Tool Class

public abstract class Tool {


    ToolInfoHolder info;

    /**
     * Main Constructor.  Takes in and stores a ToolInfoHolder Object,
     * which contains all the necessary information for the tool to function.
     * @param  info The ToolInfoHolder
     */
    public Tool(ToolInfoHolder info){
        this.info = info;
    }

    /**
     * executes the functionality of the Tool.
     */
    public abstract void execute() throws ToolException;

}
