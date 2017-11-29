package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventOccurrence;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.Event;

/**
 *
 * @author Akin Gunay
 */
public class AchievementProperty extends Property {

    private final Event event;
    private final long intervalStart;
    private final long intervalEnd;

    public static AchievementProperty newAchievementProperty(Event event, long intervalStart, long intervalEnd) {
        // TODO validate
        AchievementProperty achievementProperty = new AchievementProperty(event, intervalStart, intervalEnd);
        event.registerEventObserver(achievementProperty);
        return achievementProperty;
    }
    
    private AchievementProperty(Event event, long intervalStart, long intervalEnd) {
        this.event = event;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }

    @Override
    protected void evaluate(EventOccurrence eventOccurrence) {
        // validation
        if (getState().equals(PropertyState.UNDETERMINED)) {
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
        event.removeEventObserver(this);
    }
}
