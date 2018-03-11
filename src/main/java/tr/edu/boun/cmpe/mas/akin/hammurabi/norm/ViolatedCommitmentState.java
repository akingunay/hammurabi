package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public enum ViolatedCommitmentState implements NormState<Commitment> {
    VIOLATED;

    @Override
    public NormState progress(Commitment norm, CompoundProperty property, PropertyState propertyState) {
        return VIOLATED;
    }
    
    
}
