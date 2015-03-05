package edu.union.fsm.tool;

import edu.union.fsm.*;

public class ToolInfoHolder {

    Model model;
    View view;
    int firstX;
    int firstY;
    int secondX;
    int secondY;

    public ToolInfoHolder(Model model, View view) {
        this.model = model;
        this.view = view;
        firstX = 0;
        firstY = 0;
        secondX = 0;
        secondY = 0;
    }

    //GETTERS
    public Model getModel() {
        return model;
    }
    public View getView() {
        return view;
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
