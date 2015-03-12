package edu.union.fsm.tools;

import edu.union.fsm.*;
import edu.union.fsm.storage.*;

public class ToolInfoHolder {

    private static ToolInfoHolder instance = null;

    InformationStore informationStore;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    private ToolInfoHolder() {
        this.informationStore = null;
        this.swingDisplay = null;
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
    }

    /**
     * returns the singleton debugger object. creates and returns it if
     * it has yet to be made.
     * @return the singleton debugger.
     */
    public static synchronized ToolInfoHolder getInstance() {
        if (instance == null) {
            instance = new ToolInfoHolder();
        }
        return instance;
    }

    //GETTERS
    public InformationStore getInformationStore() {
        return informationStore;
    }
    public SwingDisplay getSwingDisplay() {
        return swingDisplay;
    }
    public int getFirstX() {
        return firstX;
    }
    public int getFirstY(){
        return firstY;
    }
    public int getSecondX() {
        return secondX;
    }
    public int getSecondY(){
        return secondY;
    }

    //SETTERS
    public void init(InformationStore informationStore, SwingDisplay swingDisplay) {
        this.informationStore = informationStore;
        this.swingDisplay = swingDisplay;
    }
    public void setFirstX(int firstX) {
        this.firstX = firstX;
    }
    public void setFirstY(int firstY) {
        this.firstY = firstY;
    }
    public void setSecondX(int secondX) {
        this.secondX = secondX;
    }
    public void setSecondY(int secondY) {
        this.secondY = secondY;
    }

}
