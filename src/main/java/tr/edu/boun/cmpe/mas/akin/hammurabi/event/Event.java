package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

import java.util.Objects;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.ArgumentValidator;

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
    
    // TODO can we do this package access
    public static Event newEvent(String label) {
        ArgumentValidator.validateStringIsLegal(label, "argument \"label\" cannot be null or empty");
        return new Event(label);
    }
    
    private Event(String label) {
        this.label = label;
    }

    public String getEventLabel() {
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
        return this.label.equals(other.label);
    }

    @Override
    public String toString() {
        return label;
    }
    
}
