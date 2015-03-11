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

	    // add state in empty cell
	    informationStore.addState("Hello", 1, 1);	
	    assertTrue("There should exist a state at location(1,1).", informationStore.containsState(1,1));
	    
	    State tempState = informationStore.getState(1,1);
	    String tempName = tempState.getName();
	    assertEquals("Name should be Hello", "Hello", tempName);


	    // adding state in cell with already existing state in it
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

	    // remove existing state
	    informationStore.addState("Hello", 1, 1);
	    assertTrue("There should exist a state at location(1,1).", informationStore.containsState(1,1));

	    informationStore.removeState(1, 1);
	    assertFalse("There should no longer be a state at position (1,1)", informationStore.containsState(1,1));


	    // remove non-existing state
	    informationStore.removeState(4, 4);
	    assertFalse("Removing nonexistant state should not break it.", informationStore.containsState(4,4));
	    
	} catch (Exception ex) {}
	
    }

    @Test
    public void testRemoveTransition() {

    }

    @Test
    public void testMoveState() {
	try{

	    // moving existant state
	    informationStore.addState("Hello", 1, 1);
	    assertTrue("There should exist a state at location(1,1).", informationStore.containsState(1,1));

	    informationStore.moveState(1, 1, 7, 7);
	    assertFalse("There should no longer exist a state at location(1,1).", informationStore.containsState(1,1));
	    assertTrue("There should exist a state at location(7,7).", informationStore.containsState(7,7));

	    State tempState = informationStore.getState(7,7);
	    String tempName = tempState.getName();
	    assertEquals("Name should be Hello", "Hello", tempName);


	    // moving state more than once
	    informationStore.moveState(7, 7, 5, 2);
	    assertFalse("There should no longer exist a state at location(7,7).", informationStore.containsState(7,7));
	    assertTrue("There should exist a state at location(5,2).", informationStore.containsState(5,2));

	    State tempState2 = informationStore.getState(5,2);
	    String tempName2 = tempState.getName();
	    assertEquals("Name should be Hello", "Hello", tempName2);


	    // moving state to non-empty cell
	    informationStore.addState("World", 7, 5);
	    informationStore.moveState(5, 2, 7, 5);
	    
	    State tempState3 = informationStore.getState(7,5);
	    String tempName3 = tempState.getName();
	    assertEquals("Name should not change", "World", tempName3);
	    assertTrue("There should still exist a state at location(5,2).", informationStore.containsState(5,2));
	    assertTrue("There should still exist a state at location(7,5).", informationStore.containsState(7,5));

	    
	    // moving non-existant state
	    informationStore.moveState(3, 4, 6, 6);
	    assertFalse("There should be no state at location(6,6). This should not break.", informationStore.containsState(6,6));
		    
	} catch (Exception ex) {}
	
    }

    
//  @Test
//  public void testGetRow() {
//	
//  }

//  @Test
//  public void testGetColumn() {
//	
//  }

    
}
