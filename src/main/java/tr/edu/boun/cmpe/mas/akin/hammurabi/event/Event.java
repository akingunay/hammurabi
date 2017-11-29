package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.Objects;

/**
 *
 * @author Akin Gunay
 */
public class Event {
    
    public static Event TICK;
    
    static {
        TICK = new Event("TICK", null); // TODO we need a better way for this null in TICK
    }
    
    private final String label;
    private final EventTrace eventTrace;
    
    static Event newEvent(String label, EventTrace eventTrace) {
        // TODO validation
        return new Event(label, eventTrace);
    }
    
    private Event(String label, EventTrace eventTrace) {
        this.label = label;
        this.eventTrace = eventTrace;
    }

    public void registerEventObserver(EventObserver eventObserver) {
        eventTrace.registerEventObserver(eventObserver, this);
    }

    public void removeEventObserver(EventObserver eventObserver) {
        eventTrace.removeEventObserver(eventObserver, this);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.label);
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
        final Event other = (Event) obj;
        return !Objects.equals(this.label, other.label);
    }

    @Override
    public String toString() {
        return "Event(" + label + ")";
    }
    
    
}
