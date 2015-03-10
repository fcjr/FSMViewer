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

import java.util.*;
import edu.union.fsm.storage.*;


@RunWith(JUnit4.class)
public class InformationStoreTests {

    private InformationStore informationStore;

    @Before
    public void setUp() {
        informationStore = new InformationStore();
    }

    @After
    public void tearDown() {
        informationStore = null;
    }

    @Test
    public void testConstructor() {
	assertEquals("an empty information has 8 rows", 8, informationStore.getRows());
	assertEquals("an empty information has 8 columns", 8, informationStore.getColumns());
      }

    @Test
    public void testAddState() {

	try{
	    assertFalse("There should be no state to begin with.", informationStore.containsState(1,1));
	
	    informationStore.addState("Hello", 1, 1);	
	    assertTrue("There should exist a state at location(1,1).", informationStore.containsState(1,1));
	
	    State tempState = informationStore.getState(1,1);
	    String tempName = tempState.getName();
	    assertEquals("Name should be Hello", "Hello", tempName);

	    informationStore.addState("Bye", 1, 1);
	    State toCheck = informationStore.getState(1,1);
	    String name = toCheck.getName();
	    assertEquals("Name of state should remain the same when trying to add a state where existing state is", "Hello", name);
	} catch (Exception ex) {}
	
    }

    //    @Test
    //public void testAddTransition() {

    //	try{
    //	    ArrayList<Transition> toCheck = informationStore.getTransitions();
    //	    assertTrue("There should be no transition to begin with.", toCheck.isEmpty());
    //
    //	    informationStore.addState("Marco", 1, 1);
    //	    informationStore.addState("Reus", 2, 2);
    //	    informationStore.addTransition("BVB", 1, 1, 2, 2);
    //
    //	    State from = informationStore.getState(1,1);
    //	    State to = informationStore.getState(2,2);
    //	    int fromID = from.getID();
    //	    int toID = to.getID();
    //	    boolean hasTransition = toCheck.containsTransitionWithIDs(fromID,toID);
    //
    //	    assertTrue("There should be the one transition we are looking for.", hasTransition);
    //	} catch (Exception ex) {}
    //}

    @Test
    public void testRemoveState() {
	try{
	    assertFalse("There should be no state to begin with.", informationStore.containsState(1,1));
	
	    informationStore.addState("Hello", 1, 1);
	    informationStore.addState("Bye", 2, 2);
	    assertTrue("There should exist a state at location(1,1).", informationStore.containsState(1,1));
	    assertTrue("There should exist a state at location(2,2).", informationStore.containsState(2,2));

	   informationStore.removeState(1, 1);
	    assertFalse("There should no longer be a state at position (1,1)", informationStore.containsState(1,1));
	} catch (Exception ex) {}
	
    }

    @Test
    public void testRemoveTransition() {

    }

    @Test
    public void testMoveState() {

    }

    @Test
    public void testGetStart() {

    }
    
    @Test
    public void testGetRow() {

    }

    @Test
    public void testGetColumn() {

    }

    
}
