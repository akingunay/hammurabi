package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class EventTest {
    
    public EventTest() {
    }

    @Test (expected=NullPointerException.class)
    public void testNullInitialization() {
        new Event(null);
    }

    @Test (expected=IllegalArgumentException.class)
    public void testEmptyLabelInitialization() {
        new Event("");
    }
    
    @Test
    public void testEquals() {
        assertEquals(new Event("event a"), new Event("event a"));
    }
    
    @Test
    public void testNotEquals() {
        assertNotEquals(new Event("event a"), new Event("event b"));
    }

    @Test
    public void testEqualsHashCodeCompatability() {
        assertEquals((new Event("event a")).hashCode(), (new Event("event a")).hashCode());
        assertEquals((new Event("xyz")).hashCode(), (new Event("xyz")).hashCode()); 
        assertEquals((new Event("first event")).hashCode(), (new Event("first event")).hashCode()); 
    }
}
