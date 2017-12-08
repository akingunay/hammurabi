/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.DefaultEventTraceValidator;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;

/**
 *
 * @author Akin Gunay
 */
public class EventTraceParserTest {
    
    private static final long NUMBER_OF_EVENTS_IN_VALID_INPUT;
    private static final long ALLOWED_FIRST_MOMENT;
    private static final long ALLOWED_LAST_MOMENT;
    private static final Set<Event> RESERVED_EVENTS;
    
    static {
        NUMBER_OF_EVENTS_IN_VALID_INPUT = 10;
        ALLOWED_FIRST_MOMENT = 1;
        ALLOWED_LAST_MOMENT = 100;
        RESERVED_EVENTS = new HashSet<>();
        RESERVED_EVENTS.add(Event.TICK);
    }

    @Test
    public void testValidInput() throws EventTraceParseException, InvalidEventTraceException {
        EventTraceParser parser = new EventTraceParser(getClass().getResourceAsStream("/event/testValidInput.etr"));
        parser.parse(new DefaultEventTraceValidator(RESERVED_EVENTS, ALLOWED_FIRST_MOMENT, ALLOWED_LAST_MOMENT));
        long eventNumber = 1;
        for (EventLog eventLog : parser.getEventLogs()) {
            assertEquals("event" + eventNumber, eventLog.getEvent().getEventLabel());
            assertEquals(eventNumber, eventLog.getMoment());
            eventNumber++;
        }
        assertEquals(NUMBER_OF_EVENTS_IN_VALID_INPUT + 1, eventNumber);
        Set<Event> events = new HashSet<>();
        for (int i = 1 ; i <= NUMBER_OF_EVENTS_IN_VALID_INPUT ; i++) {
            events.add(Event.newEvent("event" + i));
        }
        assertEquals(events, parser.getEvents());
    }
    
    @Test(expected=EventTraceParseException.class)
    public void testEmptyInput()  throws EventTraceParseException, InvalidEventTraceException  {
        EventTraceParser parser = new EventTraceParser(getClass().getResourceAsStream("/event/testEmptyInput.etr"));
        parser.parse(new DefaultEventTraceValidator(RESERVED_EVENTS, ALLOWED_FIRST_MOMENT, ALLOWED_LAST_MOMENT));
    }
    
    @Test(expected=EventTraceParseException.class)
    public void testInvalidInput()  throws EventTraceParseException, InvalidEventTraceException  {
        EventTraceParser parser = new EventTraceParser(getClass().getResourceAsStream("/event/testInvalidInput.etr"));
        parser.parse(new DefaultEventTraceValidator(RESERVED_EVENTS, ALLOWED_FIRST_MOMENT, ALLOWED_LAST_MOMENT));
    }
}
