package tr.edu.boun.cmpe.mas.akin.hammurabi.util;

/**
 *
 * @author Akin Gunay
 */
public class ArgumentValidator {

    private ArgumentValidator() {
    }
    
    public static void validateObjectIsNotNull(Object obj, String msg) {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }
    
    public static void validateStringIsLegal(String str, String msg) {
        validateObjectIsNotNull(str, msg);
        if (str.isEmpty()) {
            throw new IllegalArgumentException(msg);
        }
    }
    
}
