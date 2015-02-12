/**
 * Wrapper class for all asociated information needed to store a state.
 *
 * @author Frank, Rudy, Nate
 */

package edu.union.fsm;


public class State {

    private int id;
    private String name;
    private boolean start;
    private boolean except;

    public State(int id, String name) {
        this.id = id;
        this.name = name;
        this.setType(3);
    }


    public State(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.setType(type);
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rename(String name) {
        this.name = name;
    }

    public void setType(int type) { 
        if(type == 0) {
            this.start = true;
            this.except = false;
        } else if(type == 1) {
            this.start = false;
            this.except = true;
        } else {
            this.start = false;
            this.except = false;
        }
    }


}
