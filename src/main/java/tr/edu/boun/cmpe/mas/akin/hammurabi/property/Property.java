package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventObserver;

/**
 * A base class for all property types. It encapsulates both observation of
 * event happenings and notification of property observers for concrete
 * properties. However, concrete properties should register themselves to
 * events.
 * 
 * @author Akin Gunay
 */
public abstract class Property implements PropertyExpression, EventObserver, PropertySubject {

    private final Set<PropertyObserver> propertyObservers;
    private PropertyState propertyState;
    
    protected Property() {
        propertyState = PropertyState.UNDETERMINED;
        propertyObservers = new HashSet<>();
    }

    protected void setState(PropertyState propertyState) {
        this.propertyState = propertyState;
    }
    
    @Override
    public PropertyState evaluate() {
        return propertyState;
    }
    
    @Override
    public Set<Property> getProperties() {
        Set<Property> properties = new HashSet<>(1);
        properties.add(this);
        return properties;
    }
    
    @Override
    public void registerPropertyObserver(PropertyObserver propertyObserver) {
        propertyObservers.add(propertyObserver);
    }

    @Override
    public void removePropertyObserver(PropertyObserver propertyObserver) {
        propertyObservers.remove(propertyObserver);
    }

    @Override
    public void notifyPropertyObservers() {
        for (PropertyObserver propertyObserver : new HashSet<>(propertyObservers)) {
            propertyObserver.update(this, propertyState);
        }
    }
    
    @Override
    public void update(EventLog eventLog) {
        //System.out.println(this + ": notified about event " + eventLog + ".");
        evaluate(eventLog);
    }
    
    protected abstract void evaluate(EventLog eventLog);
    
}