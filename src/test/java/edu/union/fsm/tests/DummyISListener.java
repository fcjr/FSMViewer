package edu.union.fsm.tests;

import edu.union.fsm.storage.*;

public class DummyISListener implements InformationStoreListener {

    int numUpdates;

    public DummyISListener(){
        numUpdates = 0;
    }

    public void update(){
        numUpdates++;
    }

    public int getUpdates(){
        return numUpdates;
    }

    public void clearUpdates(){
        numUpdates = 0;
    }

}
