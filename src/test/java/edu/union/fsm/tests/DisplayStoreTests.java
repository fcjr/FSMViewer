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

import edu.union.fsm.*;

@RunWith(JUnit4.class)
public class DisplayStoreTests {

    private DisplayStore displayStore;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        displayStore = null;
    }

    @Test
    public void testConstructor() {
    }
}
