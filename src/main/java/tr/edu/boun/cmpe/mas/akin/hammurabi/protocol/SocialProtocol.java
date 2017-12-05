package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.Norm;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.NormObserver;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.NormToken;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.ParseException;
import tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser.SocialProtocolParser;

/**
 *
 * @author Akin Gunay
 */
public class SocialProtocol {

    private final Set<Norm> norms;
    private final EventTrace eventTrace;

    public SocialProtocol(InputStream socialProtocolPath, EventTrace eventTrace) throws ParseException {
        this.eventTrace = eventTrace;
        this.norms = new HashSet<>();
        // TODO encapsulate in a wrapper class for SocialProtocolParser and
        //      reduce access of all classes in parser package 
        Set<NormToken> normTokens = SocialProtocolParser.parse(socialProtocolPath);
        for (NormToken normToken : normTokens) {
            norms.add(normToken.getNormInstance(eventTrace));
        }
    }

    public void registerObserverToAllNorms(NormObserver normObserver) {
        // TODO validate input
        for (Norm norm : norms) {
            norm.registerNormObserver(normObserver);
        }
    }
    
    
    
}
