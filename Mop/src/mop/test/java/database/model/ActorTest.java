package mop.test.java.database.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import mop.main.java.database.model.queryable.Actor;

public class ActorTest {

    @Test
    public void testName() {

        Actor sut = new Actor();
        sut.setName("testActor");
        assertEquals("testActor", sut.getName());
    }

    @Test
    public void testId() {

        Actor sut = new Actor();
        sut.setId(2);
        assertEquals(2, sut.getId());
    }
}
