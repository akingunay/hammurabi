package tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser;

import java.util.List;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.AchievementProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.MaintenanceProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyExpression;

/**
 *
 * @author Akin Gunay
 */
public class PropertyToken extends PropertyExpressionToken {

    public static final String ACHIEVEMENT = "Achieve";
    public static final String MAINTENANCE = "Maintain";
    
    private final String type;
    private final List<String> eventLabels;
    private final long intervalStart;
    private final long intervalEnd;

    public PropertyToken(String type, List<String> eventLabels, long intervalStart, long intervalEnd) {
        this.type = type;
        this.eventLabels = eventLabels;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
    }
    
    @Override
    PropertyExpression getPropertyExpression(final EventTrace eventTrace) {
        // TODO validate input
        if (type.equals(ACHIEVEMENT)) {
            return AchievementProperty.newAchievementProperty(eventLabels.get(0), intervalStart, intervalEnd, eventTrace);
        }
        if (type.equals(MAINTENANCE)) {
            return MaintenanceProperty.newMaintenanceProperty(eventLabels.get(0), eventLabels.get(1), intervalStart, intervalEnd, eventTrace);
        }
        // TODO we should never reach to this point
        return null;
    }
}
