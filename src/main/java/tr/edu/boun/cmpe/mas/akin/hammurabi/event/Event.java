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
    
    public Event(String label) {
        ArgumentValidator.validateStringIsLegal(label, "argument \"label\" cannot be null or empty");
        this.label = label;
    }

    public String getEventLabel() {
        return label;
    }
    
    @Override
    public int hashCode() {
        return label.hashCode();
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
        return Objects.equals(this.label, other.label);
    }

    @Override
    public String toString() {
        return label;
    }
    
}
