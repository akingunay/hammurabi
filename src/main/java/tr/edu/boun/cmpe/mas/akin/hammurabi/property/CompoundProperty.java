package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public class CompoundProperty implements PropertyExpression, PropertyObserver {

    private final PropertyExpression expression;
    private PropertyState state;
    
    public CompoundProperty(PropertyExpression expression) {
        this.expression = expression;
        state = PropertyState.UNDETERMINED;
    }
    
    @Override
    public PropertyState evaluate() {
        return state;
    }

    @Override
    public void update(Property property, PropertyState propertyState) {
        state = expression.evaluate();
    }

    @Override
    public String toString() {
        return "(" + expression + ")<" + state + ">";
    }
    
    
}
