package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public class CompoundProperty implements PropertyExpression, PropertyObserver {

    private final PropertyExpression expression;
    private PropertyState state;
    
    public CompoundProperty() {
        expression = parse();
        state = PropertyState.UNDETERMINED;
    }
    
    private PropertyExpression parse() {return null;}
    
    @Override
    public PropertyState evaluate() {
        return state;
    }

    @Override
    public void update(Property property, PropertyState propertyState) {
        state = expression.evaluate();
    }
    

}
