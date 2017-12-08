package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceValidator;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.InvalidEventTraceException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.ArgumentValidator;

/**
 *
 * @author Akin Gunay
 */
public class DefaultEventTraceValidator implements EventTraceValidator {

    private final Set<Event> reservedEvents;
    private final long allowedFirstMoment;
    private final long allowedLastMoment;

    public DefaultEventTraceValidator(Set<Event> reservedEvents, long allowedFirstMoment, long allowedLastMoment) {
        ArgumentValidator.validateObjectIsNotNull(reservedEvents, "argument \"reservedEvents\" cannot be null");
        this.reservedEvents = reservedEvents;
        this.allowedFirstMoment = allowedFirstMoment;
        this.allowedLastMoment = allowedLastMoment;
    }

    @Override
    public void validate(List<EventLog> eventLogs, List<Event> events) throws InvalidEventTraceException {
        ArgumentValidator.validateObjectIsNotNull(eventLogs, "argument \"eventLogs\" cannot be null");
        ArgumentValidator.validateObjectIsNotNull(events, "argument \"events\" cannot be null");
        validateEventOrdering(eventLogs);
        validateAllowedInitialMoment(eventLogs);
        validateAllowedLastMoment(eventLogs);
        validateEventUniqueness(events);
        validateReservedEvents(events);
    }
    
    private void validateEventOrdering(List<EventLog> eventLogs) throws InvalidEventTraceException {
        long currentMoment = Long.MIN_VALUE;
        for (EventLog eventLog : eventLogs) {
            if (eventLog.getMoment() < currentMoment) {
                throw new InvalidEventTraceException("Events must happen in order.");
            }
            currentMoment = eventLog.getMoment();
        }
    }
    
    private void validateAllowedInitialMoment(List<EventLog> eventLogs) throws InvalidEventTraceException {
        if (eventLogs.get(0).getMoment() < allowedFirstMoment)  {
            throw new InvalidEventTraceException("Events may not happen before the first allowed moment (" + allowedFirstMoment + ").");
        }
    }
    
    private void validateAllowedLastMoment(List<EventLog> eventLogs) throws InvalidEventTraceException {
        if (allowedLastMoment <= eventLogs.get(eventLogs.size() - 1).getMoment()) {
            throw new InvalidEventTraceException("Events may not happen after the last allowed moment (" + allowedLastMoment + ").");
        }
    }
    
    private void validateEventUniqueness(List<Event> events) throws InvalidEventTraceException {
        Set<Event> eventSet = new HashSet<>(events);
        if (eventSet.size() != events.size()) {
            throw new InvalidEventTraceException("Duplicate event happenings are not allowed in an event trace.");
        }
    }
         
    private void validateReservedEvents(List<Event> events) throws InvalidEventTraceException {
        Set<Event> eventsInTrace = new HashSet<>(events);
        eventsInTrace.retainAll(reservedEvents);
        if (!eventsInTrace.isEmpty()) {
            throw new InvalidEventTraceException("Keywords are not allowed in an event trace.");
        }
    }
    
}
