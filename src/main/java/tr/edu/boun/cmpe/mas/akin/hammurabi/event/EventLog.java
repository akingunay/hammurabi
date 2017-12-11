package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.Objects;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.ArgumentValidator;

/**
 * This class represents happening of an event in an event trace.
 * 
 * Note: Natural ordering of this class (i.e., ascending order of moments) is
 * inconsistent with equals. For instance, for the EventLog objects 
 * logA = EventLog(new Event("A"), 1) and logB = EventLog(new Event("B"), 1),
 * logA.equals(logB) returns false while logA.compareTo(logB) returns 0.
 * 
 * @author Akin Gunay
 */
public class EventLog implements Comparable<EventLog> {

    private final Event event;
    private final long moment;

    public EventLog(Event event, long moment) {
        ArgumentValidator.validateObjectIsNotNull(event, "argument \"event\" cannot be null");
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
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.event);
        hash = 11 * hash + (int) (this.moment ^ (this.moment >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EventLog other = (EventLog) obj;
        if (this.moment != other.moment) {
            return false;
        }
        return Objects.equals(this.event, other.event);
    }

    @Override
    public String toString() {
        return event + " @ " + moment;
    }

    @Override
    public int compareTo(EventLog otherEventLog) {
        ArgumentValidator.validateObjectIsNotNull(otherEventLog, "otherEventLog");
        if (this.moment < otherEventLog.moment) {
            return -1;
        } else if (otherEventLog.moment < this.moment) {
            return 1;
        } else {
            return 0;
        }
    }

}
