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

}
