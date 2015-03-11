package edu.union.fsm.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import edu.union.fsm.storage.*;


@RunWith(JUnit4.class)
public class InformationStoreMockTests {

    private InformationStore informationStore;
    private DummyISListener dummyListener;

    @Before
    public void setUp() {
        informationStore = new InformationStore();
        dummyListener = new DummyISListener();
        informationStore.addListener(dummyListener);
    }

    @After
    public void tearDown() {
        informationStore = null;
        dummyListener = null;
    }

    @Test
    public void listenerFunctions() {
        informationStore.notifyListeners();
        assertEquals("adding a listener causes it to be notified when notified",1,dummyListener.getUpdates());
        informationStore.removeListener(dummyListener);
        informationStore.notifyListeners();
        assertEquals("removeing a listener causes it to no longer be notified",1,dummyListener.getUpdates());
        informationStore.addListener(dummyListener);
        informationStore.clearListeners();
        informationStore.notifyListeners();
        assertEquals("clearing listeners has the same affect",1,dummyListener.getUpdates());
    }

    @Test
    public void addState(){
        try{
            informationStore.addState("name",1,1);
        } catch (Exception ex){}
        assertEquals("adding a state in an empty location notifies listeners",1,dummyListener.getUpdates());
        Boolean exception = false;
        try{
            informationStore.addState("next",1,1);
        } catch (StoreException ex){
            exception = true;
        }
        assertTrue("adding a state in a non-empty location throws an exception",exception);
        assertEquals("adding a state in a non-empty location does not notify listeners",1,dummyListener.getUpdates());
    }

    @Test
    public void addTransition(){
        try{
            informationStore.addState("one",1,1);
            informationStore.addState("two",2,2);
            informationStore.addTransition("trans",1,1,2,2);
            assertEquals("adding a valid transition notifies listeners",3,dummyListener.getUpdates());
            informationStore.addTransition("trans2",1,1,2,2);
            assertEquals("adding a valid transition where one already exists also notifies listeners",4,dummyListener.getUpdates());
        } catch (Exception ex){}
        Boolean exception = false;
        try{
            informationStore.addTransition("trans3",1,4,2,2);
        } catch (StoreException ex){
            exception = true;
        }
        assertTrue("adding an invalid transition thows an exception ",exception);
        assertEquals("adding an invalid transition thows an exception does not notify listeners",4,dummyListener.getUpdates());
    }


    @Test
    public void removeState(){
    }

    @Test
    public void removeTransition(){

    }

    @Test
    public void moveState() {

    }
    @Test
    public void toggleStateType() {

    }

    @Test
    public void highlight(){

    }

    @Test
    public void unhighlight() {

    }

    @Test
    public void clearHighlights(){
        try {
            informationStore.clearHighlights();
        } catch (Exception e) {}
        assertEquals("calling clearHighlights notifies the listeners",1,dummyListener.getUpdates());
    }

}
