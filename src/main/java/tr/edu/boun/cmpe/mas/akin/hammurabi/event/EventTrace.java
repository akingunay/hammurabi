package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParser;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.RawEventLog;

/**
 *
 * @author Akin Gunay
 */
public class EventTrace implements EventSubject {

    private final List<EventLog> eventLogs;
    private final Map<String, Event> eventIndex;
    private final long lastMoment;
    private final EventObserversIndex eventObserversIndex;
    
    private static final long FIRST_ALLOWED_EVENT_MOMENT = 1;
    
    public static EventTrace newEventTrace(InputStream eventTraceStream, long lastMoment) throws ParseException {
        if (lastMoment < FIRST_ALLOWED_EVENT_MOMENT) {
            throw new IllegalArgumentException("Last moment cannot be before the first allowed event moment.");
        }
        List<EventLog> eventLogs = parseEventLogs(eventTraceStream);
        Map<String, Event> eventIndex = extractEventIndexFromLogs(eventLogs);
        if (lastMoment < eventLogs.get(eventLogs.size() - 1).getMoment()) {
            throw new IllegalArgumentException("Last moment cannot be before the moment of the last event in the trace.");
        }
        return new EventTrace(eventLogs, eventIndex, lastMoment);
    }

    private static List<EventLog> parseEventLogs(InputStream eventTraceStream) throws ParseException {
        List<RawEventLog> rawEventLogs = EventTraceParser.parse(eventTraceStream);
        List<EventLog> eventLogs = new ArrayList<>(rawEventLogs.size());
        long currentMoment = FIRST_ALLOWED_EVENT_MOMENT;
        for (RawEventLog rawEventLog : rawEventLogs) {
            if (rawEventLog.moment < currentMoment) {
                throw new ParseException("Events must happen in order.");
            }
            eventLogs.add(EventLog.newEventLog(Event.newEvent(rawEventLog.eventLabel), rawEventLog.moment));
            currentMoment = rawEventLog.moment;
        }
        return eventLogs;
    }
    
    private static Map<String, Event> extractEventIndexFromLogs(List<EventLog> eventLogs) throws ParseException  {
        Map<String, Event> eventIndex = new HashMap<>();
        for (EventLog eventLog : eventLogs) {
            if (eventIndex.containsKey(eventLog.getEvent().getEventLabel())) {
                throw new ParseException("An event can happen only once.");
            }
            eventIndex.put(eventLog.getEvent().getEventLabel(), eventLog.getEvent());
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
            notifyEventObservers(EventLog.newEventLog(Event.TICK, currentMoment));
            nextEventLogIndex = executeEventsAtCurrentMoment(currentMoment, nextEventLogIndex);
            currentMoment++;
        }
    }
    
    private int executeEventsAtCurrentMoment(long currentMoment, int nextEventLogIndex) {
        while (eventLogs.size() <= nextEventLogIndex && 
                eventLogs.get(nextEventLogIndex).getMoment() == currentMoment) {
            notifyEventObservers(eventLogs.get(nextEventLogIndex));
            nextEventLogIndex++;
        }
        return nextEventLogIndex;
    }
    
    public Event getEventInstance(String eventLabel) {
        return eventIndex.get(eventLabel);
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
        eventObserversIndex.notifyObserversOfEventLog(eventLog);
    }

}
