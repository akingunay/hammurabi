package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Akin Gunay
 */
public class EventObserversIndexTest {
    
    EventObserversIndex eventObserversIndex;
    private DummyEventObserver firstObserver;
    private DummyEventObserver secondObserver;
    private Set<EventLog> firstObserverExpected;
    Set<EventLog> secondObserverExpected;
    
    public EventObserversIndexTest() {
    }
    
    @Before
    public void init() {
        eventObserversIndex = new EventObserversIndex();
        firstObserver = new DummyEventObserver();
        secondObserver = new DummyEventObserver();
        firstObserverExpected = new HashSet<>();
        secondObserverExpected = new HashSet<>();
    }
    
    @Test
    public void testEventObserversIndex() {
        Event event1 = new Event("e1");
        Event event2 = new Event("e2");
        
        eventObserversIndex.addEventObserverToEvent(firstObserver, event1);
        event(new EventLog(event1, 1), true, false);
        event(new EventLog(event2, 1), false, false);
        eventObserversIndex.addEventObserverToEvent(secondObserver, event2);
        event(new EventLog(event1, 2), true, false);
        event(new EventLog(event2, 1), false, true);
        eventObserversIndex.addEventObserverToEvent(firstObserver, event2);
        event(new EventLog(event1, 3), true, false);
        event(new EventLog(event2, 3), true, true);
        eventObserversIndex.addEventObserverToEvent(secondObserver, event1);
        event(new EventLog(event1, 4), true, true);
        event(new EventLog(event2, 4), true, true);
        eventObserversIndex.removeEventObserverFromEvent(firstObserver, event1);
        event(new EventLog(event1, 5), false, true);
        event(new EventLog(event2, 5), true, true);
        eventObserversIndex.removeEventObserverFromEvent(secondObserver, event1);
        event(new EventLog(event1, 6), false, false);
        event(new EventLog(event2, 6), true, true);
        eventObserversIndex.removeEventObserverFromEvent(secondObserver, event1);
        event(new EventLog(event1, 7), false, false);
        event(new EventLog(event2, 7), true, true);
        eventObserversIndex.addEventObserverToEvent(firstObserver, event1);
        event(new EventLog(event1, 8), true, false);
        event(new EventLog(event2, 8), true, true);
        
    }
    
    private void event(EventLog eventLog, boolean firstObserverObserves, boolean secondObserverObserves) {
        eventObserversIndex.notifyEventObservers(eventLog);
        if (firstObserverObserves) {
            firstObserverExpected.add(eventLog);
        }
        if (secondObserverObserves) {
            secondObserverExpected.add(eventLog);
        }
        assertTrue(firstObserver.isObservedEventsEqual(firstObserverExpected));
        assertTrue(secondObserver.isObservedEventsEqual(secondObserverExpected));
    }
}
