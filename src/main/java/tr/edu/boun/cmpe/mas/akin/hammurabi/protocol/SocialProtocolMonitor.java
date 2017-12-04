package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundPropertyObserver;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocolMonitor implements CompoundPropertyObserver {

    private final EventTrace eventTrace;
    private final SocialProtocol socialProtocol;

    public static SocialProtocolMonitor newSocialProtocolMonitor(String pathOfSocialProtocol, String pathOfEventTrace, long lastMoment)
            throws FileNotFoundException,
            tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException,
            tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException {
        FileInputStream socialProtocolInputStream = new FileInputStream(pathOfSocialProtocol);
        FileInputStream eventTraceInputStream = new FileInputStream(pathOfEventTrace);
        return new SocialProtocolMonitor(socialProtocolInputStream, eventTraceInputStream, lastMoment);
    }
    
    public SocialProtocolMonitor newSocialProtocolMonitor(InputStream socialProtocolInputStream, InputStream eventTraceInputStream, long lastMoment)
            throws tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException, tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException {
        return new SocialProtocolMonitor(socialProtocolInputStream, eventTraceInputStream, lastMoment);
    }
    
    public SocialProtocolMonitor(InputStream socialProtocolInputStream, InputStream eventTraceInputStream, long lastMoment) 
            throws tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException, tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException {
        eventTrace = EventTrace.newEventTrace(eventTraceInputStream, lastMoment);
        socialProtocol = new SocialProtocol(socialProtocolInputStream, eventTrace);
        // TODO replace this with norms when they are implemented
        for (CompoundProperty compoundProperty : socialProtocol.getProperties()) {
            compoundProperty.registerCompoundPropertyObserver(this);
        }
    }
    
    public void execute() {
        eventTrace.execute();
    }

    @Override
    public void update(CompoundProperty property, PropertyState propertyState) {
        System.out.println("State of " + property + " has changed to " + propertyState + ".");
    }

    @Override
    public String toString() {
        return "SocialProtocolMonitor";
    }

}
