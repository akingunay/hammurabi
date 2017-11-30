package tr.edu.boun.cmpe.mas.akin.hammurabi.property;

/**
 *
 * @author Akin Gunay
 */
public class AndExpression implements PropertyExpression {

    private final PropertyExpression firstConjunct;
    private final PropertyExpression secondConjunct;

    public AndExpression(PropertyExpression firstConjunct, PropertyExpression secondConjunct) {
        // validate
        this.firstConjunct = firstConjunct;
        this.secondConjunct = secondConjunct;
    }
    
    @Override
    public PropertyState evaluate() {
        PropertyState firstConjunctState = firstConjunct.evaluate();
        PropertyState secondConjunctState = secondConjunct.evaluate();
        if (firstConjunctState.equals(PropertyState.FAILED) || secondConjunctState.equals(PropertyState.FAILED)) {
            return PropertyState.FAILED;
        } else if (firstConjunctState.equals(PropertyState.SATISFIED) && secondConjunctState.equals(PropertyState.SATISFIED)) {
            return PropertyState.SATISFIED;
        } else {
            return PropertyState.UNDETERMINED;
        }
    }

}
