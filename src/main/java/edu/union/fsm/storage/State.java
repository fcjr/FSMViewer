/**
 * Wrapper class for all asociated information needed to store a state.
 *
 * @author Frank, Rudy, Nate
 */

package edu.union.fsm.storage;


import java.io.*;


public class State implements Serializable {

    private int id;
    private int type;
    private String name;


    //type 0 == start
    //type 1 == accept
    //type 2 == both
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
        if(type == 0) {
            this.setType(1);
        } else if (type == 1) {
            this.setType(2);
        } else if (type == 2) {
            this.setType(3);
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
        this.type = type;
    }

    /**
     * returns true iff the state is a start state
     * @return true iff the state is a start state
     */
    public boolean isStart(){
        if(type == 0 || type == 2) {
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
        if(type == 1 || type == 2) {
            return true;
        } else {
            return false;
        }
    }


}
