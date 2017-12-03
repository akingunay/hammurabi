package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.Objects;

/**
 * This class represents a basic event that may happen in a system.  
 * 
 * @author Akin Gunay
 */
public class Event {
    
    public static final Event TICK;
    
    static {
        TICK = new Event("TICK");
    }
    
    private final String label;
    
    static Event newEvent(String label) {
        // TODO validation
        return new Event(label);
    }
    
    private Event(String label) {
        this.label = label;
    }

    String getEventLabel() {
        return label;
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
        return label;
    }
    
}
