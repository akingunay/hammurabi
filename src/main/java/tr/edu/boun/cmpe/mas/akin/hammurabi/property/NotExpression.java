package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public class NotExpression implements PropertyExpression {

    private final PropertyExpression expression;

    public NotExpression(PropertyExpression expression) {
        // validate
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
    
    
}
