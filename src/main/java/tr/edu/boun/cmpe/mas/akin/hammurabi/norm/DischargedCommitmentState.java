package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public enum DischargedCommitmentState implements NormState<Commitment> {
    DISCHARGED;

    @Override
    public NormState progress(Commitment norm, CompoundProperty property, PropertyState propertyState) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
