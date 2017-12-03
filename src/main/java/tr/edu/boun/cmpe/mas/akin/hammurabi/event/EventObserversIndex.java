package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A (package access only) helper class that envelopes a map, to simplify
 * indexing of event observers by EventTrace.
 * 
 * @author Akin Gunay
 */
class EventObserversIndex {

    private final Map<Event, Set<EventObserver>> eventObserversMap;
    
    public EventObserversIndex() {
        eventObserversMap = new HashMap<>();
    }
    
    public void addEventObserverToEvent(EventObserver eventObserver, Event event) {
        if (eventObserversMap.containsKey(event)) {
            eventObserversMap.get(event).add(eventObserver);
        } else {
            Set<EventObserver> eventObserverSet = new HashSet<>();
            eventObserverSet.add(eventObserver);
            eventObserversMap.put(event, eventObserverSet);
        }
    }
    
    public void removeEventObserverFromEvent(EventObserver eventObserver, Event event) {
        if (eventObserversMap.containsKey(event)) {
            eventObserversMap.get(event).remove(eventObserver);
            if (eventObserversMap.get(event).isEmpty()) {
                eventObserversMap.put(event, null);
                eventObserversMap.remove(event);
            }
        }
    }
    
    public void notifyObserversOfEventLog(EventLog eventLog) {
        Set<EventObserver> copyEventObservers = new HashSet<>(eventObserversMap.get(eventLog.getEvent()));
        for (EventObserver eventObserver : copyEventObservers) {
            eventObserver.update(eventLog);
        }
    }
}
