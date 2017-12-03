package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;

/**
 * Represents an achievement property.
 * 
 * @author Akin Gunay
 */
public class AchievementProperty extends Property {

    private final Event event;
    private final long intervalStart;
    private final long intervalEnd;
    private final EventTrace eventTrace;

    public static AchievementProperty newAchievementProperty(String eventLabel, long intervalStart, long intervalEnd, EventTrace eventTrace) {
        // TODO validate input
        // TODO validate eventTrace.getEventInstance(eventLabel) does not return null, which occurs when eventLabel does not correspond to an event in the event trace.
        AchievementProperty achievementProperty = new AchievementProperty(eventTrace.getEventInstance(eventLabel), intervalStart, intervalEnd, eventTrace);
        eventTrace.registerEventObserver(achievementProperty, achievementProperty.event);
        return achievementProperty;
    }
    
    private AchievementProperty(Event event, long intervalStart, long intervalEnd, EventTrace eventTrace) {
        this.event = event;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.eventTrace = eventTrace;
    }

    @Override
    protected void evaluate(EventLog eventLog) {
        if (evaluate().equals(PropertyState.UNDETERMINED)) {
            if (eventLog.getEvent().equals(Event.TICK)) {
                if (intervalEnd == eventLog.getMoment()) {
                    setTerminalState(PropertyState.FAILED);
                }
            } else {
                if (intervalStart <= eventLog.getMoment() && eventLog.getMoment() < intervalEnd) {
                    setTerminalState(PropertyState.SATISFIED);
                }
            }
        }
    }

    private void setTerminalState(PropertyState propertyState) {
        setState(propertyState);
        notifyPropertyObservers();
        eventTrace.removeEventObserver(this, event);
    }

    @Override
    public String toString() {
        return "A(" + event + ")[" + intervalStart + ", " + intervalEnd + "]<" + evaluate() + ">";
    }
    
}
