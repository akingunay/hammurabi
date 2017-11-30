package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public class OrExpression implements PropertyExpression {

    private final PropertyExpression firstDisjunct;
    private final PropertyExpression secondDisjunct;

    public OrExpression(PropertyExpression firstDisjunct, PropertyExpression secondDisjunct) {
        this.firstDisjunct = firstDisjunct;
        this.secondDisjunct = secondDisjunct;
    }
    
    @Override
    public PropertyState evaluate() {
        PropertyState firstDisjunctState = firstDisjunct.evaluate();
        PropertyState secondDisjunctState = secondDisjunct.evaluate();
        if (firstDisjunctState.equals(PropertyState.SATISFIED) || secondDisjunctState.equals(PropertyState.SATISFIED)) {
            return PropertyState.SATISFIED;
        } else if (firstDisjunctState.equals(PropertyState.FAILED) && secondDisjunctState.equals(PropertyState.FAILED)) {
            return PropertyState.SATISFIED;
        } else {
            return PropertyState.UNDETERMINED;
        }
    }
}
