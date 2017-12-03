package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

/**
 * This class represents happening of an event in an event trace.
 * 
 * @author Akin Gunay
 */
public class EventLog {

    // TODO consider implementing comperable
    
    private final Event event;
    private final long moment;

    public static EventLog newEventLog(Event event, long moment) {
        // TODO validation
        return new EventLog(event, moment);
    }
    
    private EventLog(Event event, long moment) {
        this.event = event;
        this.moment = moment;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public long getMoment() {
        return moment;
    }

    @Override
    public String toString() {
        return event + " @ " + moment;
    }
    
    
}
