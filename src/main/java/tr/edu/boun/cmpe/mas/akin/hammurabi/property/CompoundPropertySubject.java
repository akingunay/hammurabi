package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public interface CompoundPropertySubject {
    void registerCompoundPropertyObserver(CompoundPropertyObserver compoundPropertyObserver);
    void removeCompoundPropertyObserver(CompoundPropertyObserver compoundPropertyObserver);
    void notifyCompoundPropertyObservers();
}
