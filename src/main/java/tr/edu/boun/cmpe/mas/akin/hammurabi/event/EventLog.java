package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

/**
 *
 * @author Akin Gunay
 */
public class EventLog {

    private final Event event;
    private final long moment;

    public static EventLog newEventLog(Event event, long moment) {
        return new EventLog(event, moment);
    }
    
    private EventLog(Event event, long moment) {
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
