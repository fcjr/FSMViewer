/**
 * Wrapper class for all asociated information needed to store a state.
 *
 * @author Frank, Rudy, Nate
 */

package edu.union.fsm.storage;


import java.io.*;


public class State implements Serializable {

    private int id;
    private String name;
    private boolean start;
    private boolean accept;


    //type 0 == start
    //type 1 == accept
    //other == neither

    /**
     * non-default constructor
     * @param   id id of state
     * @param   name name of state
     */
    protected State(int id, String name) {
        this.id = id;
        this.name = name;
        this.setType(2);
    }

    /**
     * non-default constructor which can specify type
     * type 0 == start
     * type 1 == accept
     * other == neither
     * @param   id id of state
     * @param   name name of state
     * @param   type type of state
     */
    protected State(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.setType(type);
    }

    /**
     * returns id of state
     * @return id of state
     */
    protected int getID() {
        return id;
    }

    /**
     * returns name of the state
     * @return name of state
     */
    public String getName() {
        return name;
    }

    /**
     * toggles the type of state between all types
     */
    protected void toggleState() {
        if(start) {
            this.setType(1);
        } else if (accept) {
            this.setType(2);
        } else {
            this.setType(0);
        }
    }

    /**
     * renames the state
     * @param name the new name
     */
    protected void rename(String name) {
        this.name = name;
    }

    /**
     * sets the type of the state
     * @param type the type int to set
     */
    protected void setType(int type) {
        if(type == 0) {
            this.start = true;
            this.accept = false;
        } else if(type == 1) {
            this.start = false;
            this.accept = true;
        } else {
            this.start = false;
            this.accept = false;
        }
    }

    /**
     * returns true iff the state is a start state
     * @return true iff the state is a start state
     */
    public boolean isStart(){
        return start;
    }

    /**
     * returns true iff the state is an accept state
     * @return true iff the state is an accept state
     */
    public boolean isAccept(){
        return accept;
    }


}
