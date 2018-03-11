package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 * @param <N>
 */
public interface NormState<N extends Norm> {
    
    NormState<N> progress(N norm, CompoundProperty property, PropertyState propertyState);
}
