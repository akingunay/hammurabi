package tr.edu.boun.cmpe.mas.akin.hammurabi.util;

/**
 *
 * @author Akin Gunay
 * 
 * @param <S>
 * @param <C>
 * 
 */
public interface Observer<S, C> {
    void update(S subject, C state);
}
