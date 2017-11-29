package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

/**
 *
 * @author Akin Gunay
 */
public class EventOccurrence {

    private final Event event;
    private final long moment;

    public EventOccurrence(Event event, long moment) {
        // validation
        this.event = event;
        this.moment = moment;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public long getMoment() {
        return moment;
    }
}
