package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventLog;
import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventObserver;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Observer;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Subject;

/**
 * A base class for all property types. It encapsulates both observation of
 * event happenings and notification of property observers for concrete
 * properties. However, concrete properties should register themselves to
 * events.
 * 
 * @author Akin Gunay
 */
public abstract class Property implements PropertyExpression, EventObserver, Subject<Observer<Property, PropertyState>> {

    private final Set<Observer<Property, PropertyState>> observers;
    private PropertyState state;
    
    protected Property() {
        state = PropertyState.UNDETERMINED;
        observers = new HashSet<>();
    }

    protected void setState(PropertyState propertyState) {
        this.state = propertyState;
    }
    
    @Override
    public PropertyState evaluate() {
        return state;
    }
    
    @Override
    public Set<Property> getProperties() {
        Set<Property> properties = new HashSet<>(1);
        properties.add(this);
        return properties;
    }
    
    @Override
    public void registerObserver(Observer<Property, PropertyState> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Property, PropertyState> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<Property, PropertyState> observer : new HashSet<>(observers)) {
            observer.update(this, state);
        }
    }
    
    @Override
    public void update(EventLog eventLog) {
        //System.out.println(this + ": notified about event " + eventLog + ".");
        evaluate(eventLog);
    }
    
    protected abstract void evaluate(EventLog eventLog);
    
}