package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser;

import java.util.Set;
import org.junit.Test;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser.CompoundPropertyToken;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocolParserTest {
    
    
    public SocialProtocolParserTest() {
    }

    
    @Test
    public void testValidInput() throws ParseException, tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser.ParseException {
//        EventTrace eventTrace = EventTrace.newEventTrace(getClass().getResourceAsStream("/event/testValidInput.etr"), 100);
//        Set<CompoundPropertyToken> cpts = SocialProtocolParser.parse(getClass().getResourceAsStream("/protocol/testValidInput.sop"));
//        for (CompoundPropertyToken cpt : cpts) {
//            System.out.println(cpt.getCompoundPropertyInstance(eventTrace));
//        }
    }
    
    @Test(expected=ParseException.class)
    public void testEmptyInput() throws ParseException {
        SocialProtocolParser.parse(getClass().getResourceAsStream("/protocol/testInvalidInput.sop"));
        
    }
    
    @Test(expected=ParseException.class)
    public void testInvalidInput() throws ParseException {
        SocialProtocolParser.parse(getClass().getResourceAsStream("/protocol/testEmptyInput.sop"));
    }
}