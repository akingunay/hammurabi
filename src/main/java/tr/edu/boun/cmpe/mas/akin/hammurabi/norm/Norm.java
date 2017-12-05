package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import java.util.HashSet;
import java.util.Set;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundPropertyObserver;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;


/**
 *
 * @author Akin Gunay
 */
public abstract class Norm implements NormExpression, CompoundPropertyObserver, NormSubject {
    
    private final Set<NormObserver> normObservers;
    private NormState normState;
    
    protected Norm() {
        normObservers = new HashSet<>();
        normState = NormState.CONDITIONAL;
    }

    protected void setNormState(NormState normState) {
        this.normState = normState;
        notifyNormObservers();
    }

    @Override   // NormExpression
    public NormState evaluate() {
        return normState;
    }
    
    @Override   // CompoundPropertyObserver
    public void update(CompoundProperty compoundProperty, PropertyState compoundPropertyState) {
        evaluate(compoundProperty, compoundPropertyState);
    }

    @Override   // NormSubject
    public void registerNormObserver(NormObserver normObserver) {
        normObservers.add(normObserver);
    }

    @Override // NormSubject
    public void removeNormObserver(NormObserver normObserver) {
        normObservers.remove(normObserver);
    }

    @Override // NormSubject
    public void notifyNormObservers() {
        for (NormObserver normObserver : new HashSet<>(normObservers)) {
            normObserver.update(this, normState);
        }
    }
    
    protected abstract void evaluate(CompoundProperty property, PropertyState propertyState);
}
