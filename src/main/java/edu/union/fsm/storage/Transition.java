package edu.union.fsm.storage;

import java.util.ArrayList;
import java.io.*;

/**
 * Transition Class stores all relevent information about a transition.
 */
public class Transition implements Serializable {

    private int id;
    private ArrayList<String> conditions;
    private int fromID;
    private int toID;

    /**
     * constructor.
     * @param   id id of transition
     * @param   name name of transition
     * @param   fromID from state's id
     * @param   toID to state's id
     */
    protected Transition(int id, String name, int fromID, int toID) {
        this.id = id;
        this.conditions = new ArrayList<String>();
        this.conditions.add(name);
        this.fromID = fromID;
        this.toID = toID;
    }

    /**
     * returns the transitions id
     * @return id of transition
     */
    public int getID() {
        return id;
    }

    /**
     * returns the id of the from state
     * @return from state id
     */
    public int getFromID() {
        return fromID;
    }

    /**
     * returns the id of the to state
     * @return to state id
     */
    public int getToID() {
        return toID;
    }
    protected void addCondition(String name) {
        if(!this.conditions.contains(name)) {
            this.conditions.add(name);
        }
    }

    /**
     * removes the given condition if it exists.
     * @param name name of condition to remove
     */
     protected void removeCondition(String name) {
        if(this.conditions.contains(name)) {
            this.conditions.remove(name);
        }
    }

    /**
     * returns the number of conditions in the transition
     * @return num of conditions
     */
    public int numConditions() {
        return conditions.size();
    }

    /**
     * returns a string for use in labeling transtions
     * @return a string which contains all of the condition names
     */
    public String getLabel() {
        String toReturn = conditions.toString();
        toReturn = toReturn.replaceAll("[\\[\\]]","");
        return toReturn;
    }

}
