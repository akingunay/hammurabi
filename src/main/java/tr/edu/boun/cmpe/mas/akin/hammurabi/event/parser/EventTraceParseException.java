package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.etr.ParseException;

/**
 *
 * @author Akin Gunay
 */
public class EventTraceParseException extends ParseException {

    public EventTraceParseException() {
    }

    public EventTraceParseException(String msg) {
        super(msg);
    }
}
