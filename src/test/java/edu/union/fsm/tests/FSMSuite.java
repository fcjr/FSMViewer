package edu.union.fsm.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import edu.union.fsm.tests.*;


@RunWith(Suite.class)
@Suite.SuiteClasses
({
    //FSMStoreTests.class,
    //DisplayStoreTests.class,
    InformationStoreTests.class,
    InformationStoreMockTests.class,
    ToolMockTests.class
})
public class FSMSuite
{ // no implementation needed; above annotations do the work.
}
