package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;

/**
 *
 * @author Akin Gunay
 */
public class AchievementProperty extends Property {

    private final Event event;
    private final long intervalStart;
    private final long intervalEnd;
    private final EventTrace eventTrace;

    public static AchievementProperty newAchievementProperty(String eventLabel, long intervalStart, long intervalEnd, EventTrace eventTrace) {
        // TODO validate
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
    protected void evaluate(EventLog eventOccurrence) {
        // validation
        if (evaluate().equals(PropertyState.UNDETERMINED)) {
            if (eventOccurrence.getEvent().equals(Event.TICK)) {
                if (intervalEnd == eventOccurrence.getMoment()) {
                    setTerminalState(PropertyState.FAILED);
                }
            } else {
                if (intervalStart <= eventOccurrence.getMoment() && eventOccurrence.getMoment() < intervalEnd) {
                    setTerminalState(PropertyState.SATISFIED);
                }
            }
        }
    }

    protected void setTerminalState(PropertyState propertyState) {
        setState(propertyState);
        notifyPropertyObservers();
        eventTrace.removeEventObserver(this, event);
    }
}
