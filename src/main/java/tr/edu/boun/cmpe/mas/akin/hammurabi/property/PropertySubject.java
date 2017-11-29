package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public interface PropertySubject {
    void registerPropertyObserver(PropertyObserver propertyObserver);
    void removePropertyObserver(PropertyObserver propertyObserver);
    void notifyPropertyObservers();
}
