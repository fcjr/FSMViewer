package edu.union.fsm;

/**
 * ToolInvoker invokes the execute command on the given tool 
 */
public class ToolInvoker{

    public void runTool(Tool tool){
        tool.execute();
    }
}
