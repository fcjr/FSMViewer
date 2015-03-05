package edu.union.fsm.tool;

import edu.union.fsm.*;

public class ToolInfoHolder {

    InformationStore model;
    SwingDisplay swingDisplay;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public ToolInfoHolder(InformationStore model, SwingDisplay swingDisplay) {
        this.model = model;
        this.swingDisplay = swingDisplay;
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
    }

    //GETTERS
    public InformationStore getInformationStore() {
        return model;
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
