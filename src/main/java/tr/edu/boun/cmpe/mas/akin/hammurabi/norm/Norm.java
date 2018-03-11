package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Observer;
import tr.edu.boun.cmpe.mas.akin.hammurabi.util.Subject;


/**
 *
 * @author Akin Gunay
 * @param <N>
 */
public abstract class Norm<N extends Norm> implements NormExpression, Observer<CompoundProperty, PropertyState>, Subject<Observer<Norm, NormState>> {
    
    private final Set<Observer<Norm, NormState>> observers;
    private NormState<N> state;
    
    protected Norm(NormState<N> initialState) {
        observers = new HashSet<>();
        state = initialState;
    }

    protected NormState getState() {
        return state;
    }
    
    protected void setState(NormState state) {
        this.state = state;
        notifyObservers();
    }

    @Override   // NormExpression
    public final NormState evaluate() {
        return state;
    }
    
    @Override   // CompoundPropertyObserver
    public void update(CompoundProperty property, PropertyState state) {
        evaluate(property, state);
    }

    @Override   // NormSubject
    public void registerObserver(Observer<Norm, NormState> observer) {
        observers.add(observer);
    }

    @Override // NormSubject
    public void removeObserver(Observer<Norm, NormState> observer) {
        observers.remove(observer);
    }

    @Override // NormSubject
    public void notifyObservers() {
        for (Observer<Norm, NormState> observer : new HashSet<>(observers)) {
            observer.update(this, state);
        }
    }
    
    protected abstract void evaluate(CompoundProperty property, PropertyState state);
}
