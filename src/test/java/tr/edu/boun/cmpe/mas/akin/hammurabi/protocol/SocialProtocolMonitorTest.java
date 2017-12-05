package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.FileNotFoundException;
import java.io.InputStream;
import org.junit.Test;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocolMonitorTest {
    
    public SocialProtocolMonitorTest() {
    }
    
    @Test
    public void testSocialProtocolMonitor() throws FileNotFoundException, 
            tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException,
            tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException{
        InputStream socialProtocolInputStream = getClass().getResourceAsStream("/protocol/testCommitmentSatisfaction.sop");
        InputStream eventTraceInputStream = getClass().getResourceAsStream("/event/testCommitmentSatisfaction.etr");
        SocialProtocolMonitor monitor = new SocialProtocolMonitor(socialProtocolInputStream, eventTraceInputStream, 60);
        monitor.execute();
    }
}
