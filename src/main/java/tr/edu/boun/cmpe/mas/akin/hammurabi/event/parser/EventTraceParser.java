package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser;

import java.util.List;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;

/**
 *
 * @author Akin Gunay
 */
public interface EventTraceParser {
    void parse(EventTraceValidator validator) throws EventTraceParseException, InvalidEventTraceException;
    List<EventLog> getEventLogs();
    Set<Event> getEvents();
}
