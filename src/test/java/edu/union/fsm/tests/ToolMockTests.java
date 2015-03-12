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
import edu.union.fsm.*;
import edu.union.fsm.tools.*;


@RunWith(JUnit4.class)
public class ToolMockTests {

    private DummyInformationStore informationStore;
    private ToolInfoHolder info;
    @Before
    public void setUp() {
        informationStore = new DummyInformationStore();
        ToolInfoHolder info = ToolInfoHolder.getInstance();
        info.init(informationStore, new SwingDisplay(informationStore));
    }

    @After
    public void tearDown() {
        informationStore = null;
        info = null;

    }

    @Test
    public void addStateTool() {
        try{
            Tool tool = new AddStateTool();
            tool.execute();
        } catch (Exception ex) {}
        assertTrue("addStateTool calls addState",informationStore.addStateCalled());
    }

    @Test
    public void addTransitionTool() {
        try{
            Tool tool = new AddTransitionTool();
            tool.execute();
        } catch (Exception ex) {}
        assertTrue("addTransitionTool calls addTransition",informationStore.addTransitionCalled());
    }

    @Test
    public void deleteStateTool() {
        try{
            Tool tool = new DeleteStateTool();
            tool.execute();
        } catch (Exception ex) {}
        assertTrue("DeleteStateTool calls removeState",informationStore.removeStateCalled());
    }

    @Test
    public void deleteTransitionTool() {
        try{
            Tool tool = new DeleteTransitionTool();
            tool.execute();
        } catch (Exception ex) {}
        assertTrue("deleteTransitionTool calls removeTransition once",informationStore.removeTransitionCalled());
    }

    @Test
    public void moveStateTool() {
        try{
            Tool tool = new MoveStateTool();
            tool.execute();
        } catch (Exception ex) {}
        assertTrue("MoveStateTool calls moveState once",informationStore.moveStateCalled());
    }

    @Test
    public void toggleTypeTool() {
        try{
            Tool tool = new ToggleTypeTool();
            tool.execute();
        } catch (Exception ex) {}
        assertTrue("ToggleTypeTool calls toggleStateType once",informationStore.toggleStateTypeCalled());
    }

}
