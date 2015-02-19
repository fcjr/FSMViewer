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
    private boolean accept;


    //type 0 == start
    //type 1 == accept
    //other == neither

    public State(int id, String name) {
        this.id = id;
        this.name = name;
        this.setType(2);
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

    public void toggleState() {
        if(start) {
            this.setType(1);
        } else if (accept) {
            this.setType(2);
        } else {
            this.setType(0);
        }
    }

    public void rename(String name) {
        this.name = name;
    }

    public void setType(int type) {
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
    public boolean isStart(){
        return start;
    }

    public boolean isAccept(){
        return accept;
    }


}
