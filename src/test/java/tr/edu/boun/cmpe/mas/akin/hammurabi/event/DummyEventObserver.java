package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Akin Gunay
 */
public class DummyEventObserver implements EventObserver{

    private final Set<EventLog> observedEventLogs;

    public DummyEventObserver() {
        this.observedEventLogs = new HashSet<>();
    }
    
    @Override
    public void update(EventLog eventLog) {
        observedEventLogs.add(eventLog);
    }
    
    public boolean isObservedEventsEqual(Set<EventLog> expectedEventLogs) {
        return observedEventLogs.equals(expectedEventLogs);
    }

}
