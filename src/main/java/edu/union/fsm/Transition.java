package edu.union.fsm;

import java.util.ArrayList;

public class Transition {

    private int id;
    private ArrayList<String> conditions;
    private int fromID;
    private int toID;

    public Transition(int id, String name, int fromID, int toID) {
        this.id = id;
        this.conditions = new ArrayList<String>();
        this.conditions.add(name);
        this.fromID = fromID;
        this.toID = toID;
    }

    public int getID() {
        return id;
    }

    public int getFromID() {
        return fromID;
    }
    public int getToID() {
        return toID;
    }
    public void addCondition(String name) {
        if(!this.conditions.contains(name)) {
            this.conditions.add(name);
        }
    }
    public void removeCondition(String name) { 
        if(this.conditions.contains(name)) {
            this.conditions.remove(name);
        }
    }
    public int numConditions() {
        return conditions.size();
    }

    //TEST THIS
    public String getLabel() {
        String toReturn = conditions.toString();
        toReturn = toReturn.replaceAll("[\\[\\]]","");
        return toReturn;
    }

}
