package tr.edu.boun.cmpe.mas.akin.hammurabi.event;

/**
 *
 * @author Akin Gunay
 */
public interface EventSubject {
    void registerEventObserver(EventObserver eventObserver, Event event);
    void removeEventObserver(EventObserver eventObserver, Event event);
    void notifyEventObservers(EventLog eventLog);
}
