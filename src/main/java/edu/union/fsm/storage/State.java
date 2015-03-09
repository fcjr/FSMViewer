/**
 * Wrapper class for all asociated information needed to store a state.
 *
 * @author Frank, Rudy, Nate
 */

package edu.union.fsm.storage;


import java.io.*;


public class State implements Serializable {

    protected static final int START = 0;
    protected static final int ACCEPT = 1;
    protected static final int ALL = 2;
    protected static final int NEITHER = 3;

    private int id;
    private int type;
    private String name;


    //type 0 == start
    //type 1 == accept
    //type 2 == all
    //other == neither

    /**
     * non-default constructor
     * @param   id id of state
     * @param   name name of state
     */
    protected State(int id, String name) {
        this.id = id;
        this.name = name;
        this.setType(NEITHER);
        this.highlighted = false;
    }

    /**
     * non-default constructor which can specify type
     * type 0 == start
     * type 1 == accept
     * type 2 == both
     * other == neither
     * @param   id id of state
     * @param   name name of state
     * @param   type type of state
     */
    protected State(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
        if(type == START) {
            this.setType(ACCEPT);
        } else if (type == ACCEPT) {
            this.setType(ALL);
        } else if (type == ALL) {
            this.setType(NEITHER);
        } else {
            this.setType(START);
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
        this.type = type;
    }

    /**
     * returns true iff the state is a start state
     * @return true iff the state is a start state
     */
    public boolean isStart(){
        if(type == START || type == ALL) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns true iff the state is an accept state
     * @return true iff the state is an accept state
     */
    public boolean isAccept(){
        if(type == ACCEPT || type == ALL) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */


}
