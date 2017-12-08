package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.etr;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParser;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceValidator;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.InvalidEventTraceException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.ArgumentValidator;

// TODO make othese classes package access only if possibble

public class ETREventTraceParser implements EventTraceParser {
    
    private final InputStream eventTraceStream;
    private final List<EventLog> eventLogs;
    private final Set<Event> events;
    private boolean parsingDone;
    
    public ETREventTraceParser(InputStream eventTraceStream) {
        ArgumentValidator.validateObjectIsNotNull(eventTraceStream, "argument \"eventTraceStream\" cannot be null");
        this.eventTraceStream = eventTraceStream;
        eventLogs = new ArrayList<>();
        events = new HashSet<>();
        parsingDone = false;
    }
    
    @Override
    public void parse(EventTraceValidator validator) throws EventTraceParseException, InvalidEventTraceException {
        ArgumentValidator.validateObjectIsNotNull(validator, "argument \"validator\" cannot be null");
        List<RawEventLog> rawEventLogs;
        try {
            rawEventLogs = Parser.parse(eventTraceStream);
        } catch (ParseException e) {
            throw new EventTraceParseException(e.getMessage());
        }
        List<EventLog> tmpEventLogs = new ArrayList<>();
        List<Event> tmpEvents = new ArrayList<>();
        for (RawEventLog rawEventLog : rawEventLogs) {
            Event event = Event.newEvent(rawEventLog.eventLabel);
            tmpEventLogs.add(EventLog.newEventLog(event, rawEventLog.moment));
            tmpEvents.add(event);
        }
        validator.validate(tmpEventLogs, tmpEvents);
        eventLogs.addAll(tmpEventLogs);
        events.addAll(tmpEvents);
        parsingDone = true;
    }
    
    @Override
    public List<EventLog> getEventLogs() {
        if (parsingDone) {
            return Collections.unmodifiableList(eventLogs);
        } else {
            throw new IllegalStateException("parse() method must be called before accessing event logs.");
        }
    }
    
    @Override
    public Set<Event> getEvents() {
        if (parsingDone) {
            return Collections.unmodifiableSet(events);
        } else {
            throw new IllegalStateException("parse() method must be called before accessing events.");
        }
    }
}
