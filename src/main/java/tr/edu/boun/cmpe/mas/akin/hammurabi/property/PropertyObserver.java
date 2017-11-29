package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public interface PropertyObserver {
    void update(Property property, PropertyState propertyState);
}
