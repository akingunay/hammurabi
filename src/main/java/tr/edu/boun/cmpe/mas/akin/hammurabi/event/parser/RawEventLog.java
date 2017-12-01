package tr.edu.boun.cmpe.mas.akin.hammurabi.event.parser;

/**
 *
 * @author Akin Gunay
 */
public class RawEventLog {

    public final String eventLabel;
    public final long moment;

    public RawEventLog(String eventLabel, long moment) {
        // validate
        this.eventLabel = eventLabel;
        this.moment = moment;
    }
    
}
