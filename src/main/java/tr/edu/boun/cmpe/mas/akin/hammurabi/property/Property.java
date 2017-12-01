package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventObserver;

/**
 *
 * @author Akin Gunay
 */
public abstract class Property implements PropertyExpression, EventObserver, PropertySubject {

    private final Set<PropertyObserver> propertyObservers;
    private PropertyState propertyState;
    
    protected Property() {
        this.propertyState = PropertyState.UNDETERMINED;
        this.propertyObservers = new HashSet<>();
    }

    @Override
    public PropertyState evaluate() {
        return propertyState;
    }

    @Override
    public void registerPropertyObserver(PropertyObserver propertyObserver) {
        // validate
        propertyObservers.add(propertyObserver);
    }

    @Override
    public void removePropertyObserver(PropertyObserver propertyObserver) {
        // validate
        propertyObservers.remove(propertyObserver);
    }

    @Override
    public void notifyPropertyObservers() {
        Set<PropertyObserver> copyPropertyObservers = new HashSet<>(propertyObservers);
        for (PropertyObserver propertyObserver : copyPropertyObservers) {
            propertyObserver.update(this, propertyState);
        }
    }
    
    @Override
    public void update(EventLog eventOccurrence) {
        evaluate(eventOccurrence);
    }
    
    protected void setState(PropertyState propertyState) {
        this.propertyState = propertyState;
    }
    
    protected abstract void evaluate(EventLog eventOccurrence);  
}
