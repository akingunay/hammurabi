package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;

/**
 * A compound property combines a set of base properties into a single
 * expression using logical connectives. The state of a compound property is
 * evaluated with respect to the states of its contained properties and the
 * semantics of the logical connectives that connect them. If the evaluation
 * cannot determine the state of the compound expression due to UNDETERMINED
 * state of one or more involved properties, the result of the evaluation is 
 * UNDETERMINED.
 * 
 * A compound property acts as an observer for all the properties it includes.
 * 
 * @author Akin Gunay
 */
public class CompoundProperty implements PropertyExpression, PropertyObserver, CompoundPropertySubject {

    private final PropertyExpression expression;
    private final Set<CompoundPropertyObserver> compoundPropertyObservers;
    private PropertyState compoundPropertyState;
    
    
    public CompoundProperty(PropertyExpression expression) {
        // TODO validate input
        this.expression = expression;
        this.compoundPropertyObservers = new HashSet<>();
        this.compoundPropertyState = PropertyState.UNDETERMINED;
        for (Property property : expression.getProperties()) {
            property.registerPropertyObserver(this);
        }
    }
    
    @Override
    public PropertyState evaluate() {
        return compoundPropertyState;
    }

    // TODO since a compound property is effectively final with respect to the
    //      properties it involves, a set that contains all its properties 
    //      could be cretaed in the constructor for efficient updates.
    @Override
    public void update(Property property, PropertyState propertyState) {
        System.out.print("Notified about " + property + " is " + propertyState);
        compoundPropertyState = expression.evaluate();
        System.out.println(" and state of compound is " + compoundPropertyState);
        if (compoundPropertyState.equals(PropertyState.FAILED) || compoundPropertyState.equals(PropertyState.SATISFIED)) {
            notifyCompoundPropertyObservers();
            for (Property observedProperty : expression.getProperties()) {
                observedProperty.removePropertyObserver(this);
            }
        }
    }

    @Override
    public Set<Property> getProperties() {
        return expression.getProperties();
    }

    @Override
    public void registerCompoundPropertyObserver(CompoundPropertyObserver compoundPropertyObserver) {
        compoundPropertyObservers.add(compoundPropertyObserver);
    }

    @Override
    public void removeCompoundPropertyObserver(CompoundPropertyObserver compoundPropertyObserver) {
        compoundPropertyObservers.remove(compoundPropertyObserver);
    }

    @Override
    public void notifyCompoundPropertyObservers() {
        for (CompoundPropertyObserver compoundPropertyObserver : new HashSet<>(compoundPropertyObservers)) {
            System.out.println("Notifying " + compoundPropertyObserver);
            compoundPropertyObserver.update(this, compoundPropertyState);
        }
    }
    
    @Override
    public String toString() {
        return "(" + expression + ")<" + compoundPropertyState + ">";
    }
    
}
