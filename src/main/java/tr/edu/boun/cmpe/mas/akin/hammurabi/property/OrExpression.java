package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;

/**
 * Disjunction of two property expressions.
 * 
 * @author Akin Gunay
 */
public class OrExpression implements PropertyExpression {

    private final PropertyExpression firstDisjunct;
    private final PropertyExpression secondDisjunct;

    // TODO static initializer
    
    public OrExpression(PropertyExpression firstDisjunct, PropertyExpression secondDisjunct) {
        // TODO validate input
        this.firstDisjunct = firstDisjunct;
        this.secondDisjunct = secondDisjunct;
    }
    
    @Override
    public PropertyState evaluate() {
        PropertyState firstDisjunctState = firstDisjunct.evaluate();
        PropertyState secondDisjunctState = secondDisjunct.evaluate();
        if (firstDisjunctState.equals(PropertyState.SATISFIED) || secondDisjunctState.equals(PropertyState.SATISFIED)) {
            return PropertyState.SATISFIED;
        } else if (firstDisjunctState.equals(PropertyState.FAILED) && secondDisjunctState.equals(PropertyState.FAILED)) {
            return PropertyState.FAILED;
        } else {
            return PropertyState.UNDETERMINED;
        }
    }
    
    @Override
    public Set<Property> getProperties() {
        Set<Property> properties = new HashSet<>(firstDisjunct.getProperties());
        properties.addAll(secondDisjunct.getProperties());
        return properties;
    }
    
    @Override
    public String toString() {
        return "(" + firstDisjunct + ") & (" + secondDisjunct + ")";
    }
}
