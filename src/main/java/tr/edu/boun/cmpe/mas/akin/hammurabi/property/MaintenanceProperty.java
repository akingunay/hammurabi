package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;

/**
 * Represents an maintenance property.
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
        // TODO validate input
        // TODO validate eventTrace.getEventInstance(eventLabel) does not return null, which occurs when eventLabel does not correspond to an event in the event trace.
        MaintenanceProperty maintenanceProperty = new MaintenanceProperty(eventTrace.getEventInstance(requiredEventLabel), eventTrace.getEventInstance(failureEventLabel), intervalStart, intervalEnd, eventTrace);
        eventTrace.registerEventObserver(maintenanceProperty, Event.TICK);
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
    protected void evaluate(EventLog eventLog) {
        if (eventLog.getEvent().equals(Event.TICK)) {
            if (intervalStart + 1 == eventLog.getMoment() && !currenltyMaintaining) {
                setTerminalState(PropertyState.FAILED);
            }
            if (intervalEnd == eventLog.getMoment()) {
                setTerminalState(PropertyState.SATISFIED);
            }
        } else if (eventLog.getEvent().equals(requiredEvent)){
            if (intervalStart == eventLog.getMoment()) {
                currenltyMaintaining = true;
            }
        } else if (eventLog.getEvent().equals(failureEvent)) {
            if (intervalStart <= eventLog.getMoment() && eventLog.getMoment() < intervalEnd) {
                setTerminalState(PropertyState.FAILED);
            }
        }
    }
    
    protected void setTerminalState(PropertyState propertyState) {
        setState(propertyState);
        notifyPropertyObservers();  // TODO move into Property
        eventTrace.removeEventObserver(this, Event.TICK);
        eventTrace.removeEventObserver(this, requiredEvent);
        eventTrace.removeEventObserver(this, failureEvent);
    }
    
    @Override
    public String toString() {
        return "M(" + requiredEvent + ", " + failureEvent + ")[" + intervalStart + ", " + intervalEnd + "]<" + evaluate() + ">";
    }
    
}
