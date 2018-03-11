package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.DefaultEventTraceValidator;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParser;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.InvalidEventTraceException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.ArgumentValidator;

/**
 * This class represents a trace of event happenings. It can execute the trace 
 * (by generating a TICK event for each moment and) by notifying observers of
 * the trace events about their happenings.
 * 
 * @author Akin Gunay
 */
public class EventTrace implements EventSubject {

    private final List<EventLog> eventLogs;
    private final Map<String, Event> eventIndex;
    private final long lastMoment;
    private final EventObserversIndex eventObserversIndex;
    
    private static final long ALLOWED_FIRST_MOMENT;
    private static final Set<Event> RESERVED_EVENTS;
    
    static {
        ALLOWED_FIRST_MOMENT = 1;
        RESERVED_EVENTS = new HashSet<>();
        RESERVED_EVENTS.add(Event.TICK);
    }
    
    public static EventTrace newEventTrace(EventTraceParser eventTraceParser, long lastMoment) throws EventTraceParseException, InvalidEventTraceException {
        ArgumentValidator.validateObjectIsNotNull(eventTraceParser, "argument \"eventTraceParser\" cannot be null");
        if (lastMoment < ALLOWED_FIRST_MOMENT) {
            throw new IllegalArgumentException("last moment (" + lastMoment + ") must be equal to or after the first allowed moment (" + ALLOWED_FIRST_MOMENT + ")");
        }
        eventTraceParser.parse(new DefaultEventTraceValidator(RESERVED_EVENTS, ALLOWED_FIRST_MOMENT, lastMoment));
        List<EventLog> eventLogs = eventTraceParser.getEventLogs();
        Map<String, Event> eventIndex = extractEventIndexFromLogs(eventTraceParser.getEvents());
        eventIndex.put(Event.TICK.getEventLabel(), Event.TICK);
        return new EventTrace(eventLogs, eventIndex, lastMoment);
    }

    private static Map<String, Event> extractEventIndexFromLogs(Set<Event> events) {
        Map<String, Event> eventIndex = new HashMap<>();
        for (Event event : events) {
            eventIndex.put(event.getEventLabel(), event);
        }
        return eventIndex;
    }
    
    private EventTrace(List<EventLog> eventLogs, Map<String, Event> eventIndex, long lastMoment) {
        this.eventLogs = eventLogs;
        this.eventIndex = eventIndex;
        this.lastMoment = lastMoment;
        this.eventObserversIndex = new EventObserversIndex();
    }
    
    public void execute() {
        long currentMoment = 0;
        int nextEventLogIndex = 0;
        while (currentMoment <= lastMoment) {
            notifyEventObservers(new EventLog(Event.TICK, currentMoment));
            nextEventLogIndex = executeEventsAtCurrentMoment(currentMoment, nextEventLogIndex);
            currentMoment++;
        }
    }
    
    private int executeEventsAtCurrentMoment(long currentMoment, int nextEventLogIndex) {
        while (nextEventLogIndex < eventLogs.size() && eventLogs.get(nextEventLogIndex).getMoment() == currentMoment) {
            notifyEventObservers(eventLogs.get(nextEventLogIndex));
            nextEventLogIndex++;
        }
        return nextEventLogIndex;
    }
    
    public Event getEventInstance(String eventLabel) {
        ArgumentValidator.validateObjectIsNotNull(eventLabel, "argument \"eventLabel\" cannot be null or empty");
        if (eventIndex.containsKey(eventLabel)) {
            return eventIndex.get(eventLabel);
        } else {
            throw new IllegalArgumentException("Event label (" + eventLabel + ") is unknown.");
        }
    }
    
    @Override
    public void registerEventObserver(EventObserver eventObserver, Event event) {
        // TODO validate input
        if (eventIndex.containsKey(event.getEventLabel())) {
            eventObserversIndex.addEventObserverToEvent(eventObserver, event);
        }
        // TODO an unchecked exception in the else case would help to detect issues earlier
    }

    @Override
    public void removeEventObserver(EventObserver eventObserver, Event event) {
        // TODO validate input
        if (eventIndex.containsKey(event.getEventLabel())) {
            eventObserversIndex.removeEventObserverFromEvent(eventObserver, event);
        }
    }

    @Override
    public void notifyEventObservers(EventLog eventLog) {
        eventObserversIndex.notifyEventObservers(eventLog);
    }

}
