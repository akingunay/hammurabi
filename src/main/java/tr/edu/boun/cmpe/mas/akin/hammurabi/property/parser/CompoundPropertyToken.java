package tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;

/**
 *
 * @author Akin Gunay
 */
public class CompoundPropertyToken {

    private final PropertyExpressionToken expression;

    public CompoundPropertyToken(PropertyExpressionToken expression) {
        this.expression = expression;
    }
    
    public CompoundProperty getCompoundPropertyInstance(EventTrace eventTrace) {
        return new CompoundProperty(expression.getPropertyExpression(eventTrace));
    }
}
