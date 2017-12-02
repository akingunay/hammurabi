package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;

/**
 *
 * @author Akin Gunay
 */
public class MaintenanceProperty extends Property {

    private final Event requiredEvent;
    private final Event failureEvent;
    private final long intervalStart;
    private final long intervalEnd;
    private final EventTrace eventTrace;
    
    private boolean currenltyMaintaining;
    
    public static MaintenanceProperty newMaintenanceProperty(String requiredEventLabel, String failureEventLabel, long intervalStart, long intervalEnd, EventTrace eventTrace) {
        // TODO validate
        MaintenanceProperty maintenanceProperty = new MaintenanceProperty(eventTrace.getEventInstance(requiredEventLabel), eventTrace.getEventInstance(failureEventLabel), intervalStart, intervalEnd, eventTrace);
        eventTrace.registerEventObserver(maintenanceProperty, maintenanceProperty.requiredEvent);
        eventTrace.registerEventObserver(maintenanceProperty, maintenanceProperty.failureEvent);
        return maintenanceProperty;
    }
    
    private MaintenanceProperty(Event requiredEvent, Event failureEvent, long intervalStart, long intervalEnd, EventTrace eventTrace) {
        this.requiredEvent = requiredEvent;
        this.failureEvent = failureEvent;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.eventTrace = eventTrace;
        this.currenltyMaintaining = false;
    }

    // occurrence of both events at the same moment is undefined
    @Override
    protected void evaluate(EventLog eventOccurrence) {
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
        eventTrace.removeEventObserver(this, requiredEvent);
        eventTrace.removeEventObserver(this, failureEvent);
    }
    
    @Override
    public String toString() {
        return "M(" + requiredEvent + ", " + failureEvent + ")[" + intervalStart + ", " + intervalEnd + "]<" + getState() + ">";
    }
    
}
