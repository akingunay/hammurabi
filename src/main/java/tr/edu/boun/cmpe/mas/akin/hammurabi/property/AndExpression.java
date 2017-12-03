package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;

/**
 * Conjunction of two property expressions.
 * 
 * @author Akin Gunay
 */
public class AndExpression implements PropertyExpression {

    private final PropertyExpression firstConjunct;
    private final PropertyExpression secondConjunct;

    // TODO static initializer
    
    public AndExpression(PropertyExpression firstConjunct, PropertyExpression secondConjunct) {
        // TODO validate input
        this.firstConjunct = firstConjunct;
        this.secondConjunct = secondConjunct;
    }
    
    @Override
    public PropertyState evaluate() {
        PropertyState firstConjunctState = firstConjunct.evaluate();
        PropertyState secondConjunctState = secondConjunct.evaluate();
        if (firstConjunctState.equals(PropertyState.FAILED) || secondConjunctState.equals(PropertyState.FAILED)) {
            return PropertyState.FAILED;
        } else if (firstConjunctState.equals(PropertyState.SATISFIED) && secondConjunctState.equals(PropertyState.SATISFIED)) {
            return PropertyState.SATISFIED;
        } else {
            return PropertyState.UNDETERMINED;
        }
    }

    @Override
    public Set<Property> getProperties() {
        Set<Property> properties = new HashSet<>(firstConjunct.getProperties());
        properties.addAll(secondConjunct.getProperties());
        return properties;
    }
    
    @Override
    public String toString() {
        return "(" + firstConjunct + ") & (" + secondConjunct + ")";
    }
    
}
