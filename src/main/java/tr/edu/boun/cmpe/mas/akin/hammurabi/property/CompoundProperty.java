package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Observer;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Subject;

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
public class CompoundProperty implements PropertyExpression, Observer<Property, PropertyState>, Subject<Observer<CompoundProperty, PropertyState>> {

    private final PropertyExpression expression;
    private final Set<Observer<CompoundProperty, PropertyState>> observers;
    private PropertyState state;
    
    
    public CompoundProperty(PropertyExpression expression) {
        // TODO validate input
        this.expression = expression;
        this.observers = new HashSet<>();
        this.state = PropertyState.UNDETERMINED;
        for (Property property : expression.getProperties()) {
            property.registerObserver(this);
        }
    }
    
    @Override
    public PropertyState evaluate() {
        return state;
    }

    // TODO since a compound property is effectively final with respect to the
    //      properties it involves, a set that contains all its properties 
    //      could be cretaed in the constructor for efficient updates.
    @Override
    public void update(Property property, PropertyState propertyState) {
        System.out.print("Notified about " + property + " is " + propertyState);
        state = expression.evaluate();
        System.out.println(" and state of compound is " + state);
        if (state.equals(PropertyState.FAILED) || state.equals(PropertyState.SATISFIED)) {
            notifyObservers();
            for (Property observedProperty : expression.getProperties()) {
                observedProperty.removeObserver(this);
            }
        }
    }

    @Override
    public Set<Property> getProperties() {
        return expression.getProperties();
    }

    @Override
    public void registerObserver(Observer<CompoundProperty, PropertyState> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<CompoundProperty, PropertyState> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<CompoundProperty, PropertyState> compoundPropertyObserver : new HashSet<>(observers)) {
            System.out.println("Notifying " + compoundPropertyObserver);
            compoundPropertyObserver.update(this, state);
        }
    }
    
    @Override
    public String toString() {
        return "(" + expression + ")<" + state + ">";
    }
    
}
