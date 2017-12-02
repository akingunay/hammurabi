/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Akin Gunay
 */
public class EventTraceParserTest {
    
    private static final long NUMBER_OF_EVENTS_IN_VALID_INPUT = 10;
    
    public EventTraceParserTest() {
    }

    @Test
    public void testValidInput() throws ParseException {
        List<RawEventLog> rawEventLogs = EventTraceParser.parse(getClass().getResourceAsStream("/event/testValidInput.etr"));
        long eventNumber = 1;
        for (RawEventLog rawEventLog : rawEventLogs) {
            assertEquals("event" + eventNumber, rawEventLog.eventLabel);
            assertEquals(eventNumber, rawEventLog.moment);
            eventNumber++;
        }
        assertEquals(NUMBER_OF_EVENTS_IN_VALID_INPUT + 1, eventNumber);
    }
    
    @Test(expected=ParseException.class)
    public void testEmptyInput() throws ParseException {
        EventTraceParser.parse(getClass().getResourceAsStream("/event/testEmptyInput.etr"));
    }
    
    @Test(expected=ParseException.class)
    public void testInvalidInput() throws ParseException {
        EventTraceParser.parse(getClass().getResourceAsStream("/event/testInvalidInput.etr"));
    }
}
