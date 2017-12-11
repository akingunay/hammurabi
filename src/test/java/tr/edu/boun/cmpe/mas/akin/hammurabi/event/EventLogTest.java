package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class EventLogTest {
    
    public EventLogTest() {
    }
    
    @Test (expected=NullPointerException.class)
    public void testNullInitialization() {
        new EventLog(null, 0);
    }
    
    @Test
    public void testEquals() {
        assertEquals(new EventLog(new Event("event a"), 0), new EventLog(new Event("event a"), 0));
    }
    
    @Test
    public void testNotEquals() {
        assertNotEquals(new EventLog(new Event("event a"), 0), new EventLog(new Event("event b"), 0));
        assertNotEquals(new EventLog(new Event("event a"), 0), new EventLog(new Event("event a"), 1));
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        assertEquals((new EventLog(new Event("event a"), 0)).hashCode(), (new EventLog(new Event("event a"), 0)).hashCode());
    }
    
    @Test
    public void testCompareTo() {
        EventLog logA = new EventLog(new Event("A"), -1);
        EventLog logB = new EventLog(new Event("B"), 0);
        EventLog logB2 = new EventLog(new Event("B2"), 0);
        EventLog logC = new EventLog(new Event("C"), 1);
        if (0 != logA.compareTo(logA)) {
            throw new AssertionError();
        }
        if (0 != logB.compareTo(logB2)) {
            throw new AssertionError();
        }
        if (0 <= logA.compareTo(logB)) {
            throw new AssertionError();
        }
        if (0 <= logA.compareTo(logC)) {
            throw new AssertionError();
        }
        if (0 <= logB.compareTo(logC)) {
            throw new AssertionError();
        }
        if (logB.compareTo(logA) < 1) {
            throw new AssertionError();
        }
        if (logC.compareTo(logB) < 1) {
            throw new AssertionError();
        }
        if (logC.compareTo(logA) < 1) {
            throw new AssertionError();
        }
    }
}
