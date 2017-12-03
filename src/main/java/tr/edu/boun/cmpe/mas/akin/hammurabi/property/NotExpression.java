package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.Set;

/**
 * Logical negation of property state. It only applies for satisfied and failed 
 * states.
 * 
 * @author Akin Gunay
 */
public class NotExpression implements PropertyExpression {

    private final PropertyExpression expression;

    // TODO static initilizer
    
    public NotExpression(PropertyExpression expression) {
        // TODO validate input
        this.expression = expression;
    }
    
    @Override
    public PropertyState evaluate() {
        PropertyState expressionState = expression.evaluate();
        if (expressionState.equals(PropertyState.UNDETERMINED)) {
            return PropertyState.UNDETERMINED;
        } else if (expressionState.equals(PropertyState.SATISFIED)) {
            return PropertyState.FAILED;
        } else {
            return PropertyState.FAILED;
        }
    }
    
    @Override
    public Set<Property> getProperties() {
        return expression.getProperties();
    }
    
}
