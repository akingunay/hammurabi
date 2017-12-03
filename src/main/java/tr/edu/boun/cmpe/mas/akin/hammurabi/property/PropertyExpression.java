package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.Set;

/**
 * A property that has a state.
 * 
 * @author Akin Gunay
 */
public interface PropertyExpression {
    PropertyState evaluate();
    Set<Property> getProperties();
}
