package tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.AndExpression;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.OrExpression;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyExpression;

/**
 *
 * @author Akin Gunay
 */
public class BinaryExpressionToken extends PropertyExpressionToken {

    public static final String AND = "&";
    public static final String OR = "|";
    
    private final String type;
    private final PropertyExpressionToken firstOperand;
    private final PropertyExpressionToken secondOperand;

    public BinaryExpressionToken(String type, PropertyExpressionToken firstOperand, PropertyExpressionToken secondOperand) {
        this.type = type;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    @Override
    PropertyExpression getPropertyExpression(final EventTrace eventTrace) {
        if (type.equals(AND)) {
            return new AndExpression(firstOperand.getPropertyExpression(eventTrace), secondOperand.getPropertyExpression(eventTrace));
        }
        if (type.equals(OR)) {
            return new OrExpression(firstOperand.getPropertyExpression(eventTrace), secondOperand.getPropertyExpression(eventTrace));
        }
        // TODO we should never reach to this point
        return null;
    }
}
