package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public interface CompoundPropertyObserver {
    void update(CompoundProperty property, PropertyState propertyState);
}
