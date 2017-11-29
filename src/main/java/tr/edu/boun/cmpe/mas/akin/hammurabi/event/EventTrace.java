package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Akin Gunay
 */
public class EventTrace implements EventSubject {

    private final List<EventOccurrence> eventOccurrences;
    private final Set<Event> events;
    private final long lastMoment;
    private final EventObserversMap eventObserversMap;
    
    public EventTrace(long lastMoment) {
        eventOccurrences = new ArrayList<>();   // TODO populate from imput
        events = new HashSet<>();   // TODO populate from imput
        this.lastMoment = lastMoment;
        eventObserversMap = new EventObserversMap();
    }
    
    public void execute() {
        long currentMoment = 0;
        int nextEventOccurrenceIndex = 0;
        while (currentMoment <= lastMoment) {
            notifyEventObservers(new EventOccurrence(Event.TICK, currentMoment));
            nextEventOccurrenceIndex = executeEventsAtCurrentMoment(currentMoment, nextEventOccurrenceIndex);
            currentMoment++;
        }
    }
    
    private int executeEventsAtCurrentMoment(long currentMoment, int nextEventOccurenceIndex) {
        while (eventOccurrences.size() <= nextEventOccurenceIndex && 
                eventOccurrences.get(nextEventOccurenceIndex).getMoment() == currentMoment) {
            notifyEventObservers(eventOccurrences.get(nextEventOccurenceIndex));
            nextEventOccurenceIndex++;
        }
        return nextEventOccurenceIndex;
    }
    
    @Override
    public void registerEventObserver(EventObserver eventObserver, Event event) {
        // validate event is in events
        // validate
        eventObserversMap.addEventObserverToEvent(eventObserver, event);
    }

    @Override
    public void removeEventObserver(EventObserver eventObserver, Event event) {
        // validate event is in events
        // validate
        eventObserversMap.removeEventObserverFromEvent(eventObserver, event);
    }

    @Override
    public void notifyEventObservers(EventOccurrence eventOccurrence) {
        // validate
        eventObserversMap.notifyObserversOfEventOccurrence(eventOccurrence);
        
    }

}
