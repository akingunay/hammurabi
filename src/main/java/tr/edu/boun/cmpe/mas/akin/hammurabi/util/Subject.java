package tr.edu.boun.cmpe.mas.akin.hammurabi.util;

/**
 *
 * @author Akin Gunay
 * 
 * @param <O>
 * 
 */
public interface Subject<O> {
    void registerObserver(O observer);
    void removeObserver(O observer);
    void notifyObservers();
}
