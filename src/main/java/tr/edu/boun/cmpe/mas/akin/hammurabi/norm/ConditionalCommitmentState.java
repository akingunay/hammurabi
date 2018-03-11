package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public enum ConditionalCommitmentState implements NormState<Commitment> {
    CONDITIONAL;

    @Override
    public NormState progress(Commitment commitment, CompoundProperty property, PropertyState propertyState) {
        if (property.equals(commitment.getAntecedent())) {
            return handleAntecedentUpdate(propertyState);
        } else if (property.equals(commitment.getConsequent())) {
            return handleConsequentUpdate(propertyState);
        } else {
            return CONDITIONAL;
        }
    }
    
    private NormState handleAntecedentUpdate(PropertyState propertyState) {
        if (propertyState.equals(PropertyState.SATISFIED)) {
            return DetachedCommitmentState.DETACHED;
        } else if (propertyState.equals(PropertyState.FAILED)) {
            // TODO stop observing the properties
            return ExpiredCommitmentState.EXPIRED;
        } else {
            return CONDITIONAL;
        }
    }
    
    private NormState handleConsequentUpdate(PropertyState propertyState) {
        return propertyState.equals(PropertyState.SATISFIED) ? DischargedCommitmentState.DISCHARGED : CONDITIONAL;
    }
}
