package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventOccurrence;

/**
 *
 * @author Akin Gunay
 */
public class MaintenanceProperty extends Property {

    private final Event requiredEvent;
    private final Event failureEvent;
    private final long intervalStart;
    private final long intervalEnd;
    
    private boolean currenltyMaintaining;
    
    public static MaintenanceProperty newMaintenanceProperty(Event requiredEvent, Event failureEvent, long intervalStart, long intervalEnd) {
        // TODO validate
        MaintenanceProperty maintenanceProperty = new MaintenanceProperty(requiredEvent, failureEvent, intervalStart, intervalEnd);
        requiredEvent.registerEventObserver(maintenanceProperty);
        failureEvent.registerEventObserver(maintenanceProperty);
        return maintenanceProperty;
    }
    
    private MaintenanceProperty(Event requiredEvent, Event failureEvent, long intervalStart, long intervalEnd) {
        this.requiredEvent = requiredEvent;
        this.failureEvent = failureEvent;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.currenltyMaintaining = false;
    }

    // occurrence of both events at the same moment is undefined
    @Override
    protected void evaluate(EventOccurrence eventOccurrence) {
        // validation
        if (eventOccurrence.getEvent().equals(Event.TICK)) {
            if (intervalStart + 1 == eventOccurrence.getMoment() && !currenltyMaintaining) {
                setTerminalState(PropertyState.FAILED);
            }
            if (intervalEnd == eventOccurrence.getMoment()) {
                setTerminalState(PropertyState.SATISFIED);
            }
        } else if (eventOccurrence.getEvent().equals(requiredEvent)){
            if (intervalStart == eventOccurrence.getMoment()) {
                currenltyMaintaining = true;
            }
        } else if (eventOccurrence.getEvent().equals(failureEvent)) {
            if (intervalStart <= eventOccurrence.getMoment() && eventOccurrence.getMoment() < intervalEnd) {
                setTerminalState(PropertyState.FAILED);
            }
        }
    }
    
    protected void setTerminalState(PropertyState propertyState) {
        setState(propertyState);
        notifyPropertyObservers();
        requiredEvent.removeEventObserver(this);
        failureEvent.removeEventObserver(this);
    }

}
