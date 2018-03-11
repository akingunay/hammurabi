package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParser;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.InvalidEventTraceException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.Norm;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.NormState;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Observer;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocolMonitor implements Observer<Norm, NormState> {

    private final EventTrace eventTrace;
    private final SocialProtocol socialProtocol;

    public static SocialProtocolMonitor newSocialProtocolMonitor(String pathOfSocialProtocol, EventTraceParser eventTraceParser, long lastMoment)
            throws FileNotFoundException, EventTraceParseException, InvalidEventTraceException, ParseException {
        FileInputStream socialProtocolInputStream = new FileInputStream(pathOfSocialProtocol);
        return new SocialProtocolMonitor(socialProtocolInputStream, eventTraceParser, lastMoment);
    }
    
    public SocialProtocolMonitor newSocialProtocolMonitor(InputStream socialProtocolInputStream, EventTraceParser eventTraceParser, long lastMoment)
            throws EventTraceParseException, InvalidEventTraceException, ParseException {
        return new SocialProtocolMonitor(socialProtocolInputStream, eventTraceParser, lastMoment);
    }
    
    public SocialProtocolMonitor(InputStream socialProtocolInputStream, EventTraceParser eventTraceParser, long lastMoment) 
            throws EventTraceParseException, InvalidEventTraceException, ParseException {
        eventTrace = EventTrace.newEventTrace(eventTraceParser, lastMoment);
        socialProtocol = new SocialProtocol(socialProtocolInputStream, eventTrace);
    }
    
    public void execute() {
        socialProtocol.registerObserverForAllNorms(this);
        eventTrace.execute();
    }

    @Override
    public void update(Norm norm, NormState normState) {
        System.out.println("State of " + norm + " has changed to " + normState + ".");
    }

    @Override
    public String toString() {
        return "SocialProtocolMonitor";
    }

}
