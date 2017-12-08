package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Test;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.EventTraceParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.InvalidEventTraceException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.etr.ETREventTraceParser;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocolMonitorTest {
    
    public SocialProtocolMonitorTest() {
    }
    
    @Test
    public void testSocialProtocolMonitor() throws InvalidEventTraceException, EventTraceParseException, ParseException {
        InputStream socialProtocolInputStream = getClass().getResourceAsStream("/protocol/testCommitmentSatisfaction.sop");
        InputStream eventTraceInputStream = getClass().getResourceAsStream("/event/testCommitmentSatisfaction.etr");
        ETREventTraceParser eventTraceParser = new ETREventTraceParser(eventTraceInputStream);
        SocialProtocolMonitor monitor = new SocialProtocolMonitor(socialProtocolInputStream, eventTraceParser, 60);
        monitor.execute();
    }
}
