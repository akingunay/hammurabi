package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public enum DetachedCommitmentState implements NormState<Commitment> {
    DETACHED;

    @Override
    public NormState progress(Commitment commitment, CompoundProperty property, PropertyState propertyState) {
        if (property.equals(commitment.getConsequent())) {
            return handleConsequentUpdate(propertyState);
        } else {
            return DETACHED;
        }
    }
    
    private NormState handleConsequentUpdate(PropertyState propertyState) {
        if (propertyState.equals(PropertyState.SATISFIED)) {
            return DischargedCommitmentState.DISCHARGED;
        } else if (propertyState.equals(PropertyState.FAILED)) {
            return ViolatedCommitmentState.VIOLATED;
        } else {
            return DETACHED;
        }
    }
}
