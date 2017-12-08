package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.InvalidEventTraceException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.Norm;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.NormObserver;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.NormState;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocolMonitor implements NormObserver {

    private final EventTrace eventTrace;
    private final SocialProtocol socialProtocol;

    public static SocialProtocolMonitor newSocialProtocolMonitor(String pathOfSocialProtocol, String pathOfEventTrace, long lastMoment)
            throws FileNotFoundException, EventTraceParseException, InvalidEventTraceException, ParseException {
        FileInputStream socialProtocolInputStream = new FileInputStream(pathOfSocialProtocol);
        FileInputStream eventTraceInputStream = new FileInputStream(pathOfEventTrace);
        return new SocialProtocolMonitor(socialProtocolInputStream, eventTraceInputStream, lastMoment);
    }
    
    public SocialProtocolMonitor newSocialProtocolMonitor(InputStream socialProtocolInputStream, InputStream eventTraceInputStream, long lastMoment)
            throws EventTraceParseException, InvalidEventTraceException, ParseException {
        return new SocialProtocolMonitor(socialProtocolInputStream, eventTraceInputStream, lastMoment);
    }
    
    public SocialProtocolMonitor(InputStream socialProtocolInputStream, InputStream eventTraceInputStream, long lastMoment) 
            throws EventTraceParseException, InvalidEventTraceException, ParseException {
        eventTrace = EventTrace.newEventTrace(eventTraceInputStream, lastMoment);
        socialProtocol = new SocialProtocol(socialProtocolInputStream, eventTrace);
    }
    
    public void execute() {
        socialProtocol.registerObserverToAllNorms(this);
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
