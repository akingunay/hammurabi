package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.Property;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser.CompoundPropertyToken;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.SocialProtocolParser;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocol {

    private final Set<Property> properties;
    private final EventTrace eventTrace;

    public SocialProtocol(InputStream socialProtocolPath, EventTrace eventTrace) throws ParseException {
        this.eventTrace = eventTrace;
        this.properties = new HashSet<>();
        // TODO encapsulate in a wrapper class for SocialProtocolParser and
        //      reduce access of all classes in parser package 
        Set<CompoundPropertyToken> compoundPropertyTokens = SocialProtocolParser.parse(socialProtocolPath);
        for (CompoundPropertyToken compoundPropertyToken : compoundPropertyTokens) {
            this.properties.addAll(compoundPropertyToken.getCompoundPropertyInstance(eventTrace).getProperties());
        }
        for (Property property : this.properties) {
            System.out.println(property);
        }
    }
    
    
    
}
