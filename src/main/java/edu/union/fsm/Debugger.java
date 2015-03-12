package edu.union.fsm;


/**
 * Debugger Singleton Object.  Allows for unified toggleing of debug output
 * across the system by changing one variable inside of the debugger.
 */
public class Debugger {

    private static Debugger instance = null;

    private boolean debugEnabled;

    /**
     * Private Constructor.  Can only be used in getInstance().
     * Defaults to: off
     */
    private Debugger() {
        debugEnabled = false;
    }

    /**
     * returns the singleton debugger object. creates and returns it if
     * it has yet to be made.
     * @return the singleton debugger.
     */
    public static synchronized Debugger getInstance() {
        if (instance == null) {
            instance = new Debugger();
        }
        return instance;
    }

    /**
     * prints useful information about exceptions to the console when debuging is enabled.
     * @param ex the exception to debug.
     */
    public void stackDebug(Exception ex){
        if(debugEnabled) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    /**
     * Takes in a boolean and sets the debugger to on iff user input is true.  Turns
     * it off if false.
     * @param isEnabled true iff you want the debuging to be enabled.
     */
    public void setEnable(boolean enable) {
        debugEnabled = enable;
    }
}
