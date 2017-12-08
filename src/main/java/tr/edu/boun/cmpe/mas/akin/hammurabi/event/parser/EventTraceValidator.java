package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser;

import java.util.List;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;

/**
 *
 * @author Akin Gunay
 */
public interface EventTraceValidator {
    void validate(List<EventLog> eventLogs, List<Event> events) throws InvalidEventTraceException;
}
