package tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyExpression;

/**
 *
 * @author Akin Gunay
 */
public abstract class PropertyExpressionToken {
    abstract PropertyExpression getPropertyExpression(EventTrace eventTrace);
}
