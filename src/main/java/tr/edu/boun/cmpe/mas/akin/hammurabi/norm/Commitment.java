package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public class Commitment extends Norm {

    private final String debtor;
    private final String creditor;
    private final CompoundProperty antecedent;
    private final CompoundProperty consequent;

    public static Commitment newCommitment(String debtor, String creditor, CompoundProperty antecedent, CompoundProperty consequent) {
        // TODO validate
        Commitment commitment = new Commitment(debtor, creditor, antecedent, consequent);
        commitment.antecedent.registerCompoundPropertyObserver(commitment);
        commitment.consequent.registerCompoundPropertyObserver(commitment);
        return commitment;
    }
        
    private Commitment(String debtor, String creditor, CompoundProperty antecedent, CompoundProperty consequent) {
        this.debtor = debtor;
        this.creditor = creditor;
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    @Override
    public String toString() {
        return "C(" + debtor + ", " + creditor + ", " + antecedent + ", " + consequent + ")<" + evaluate() + ">";
    }
    
    @Override
    protected void evaluate(CompoundProperty compoundProperty, PropertyState compoundPropertyState) {
        if (evaluate().equals(NormState.CONDITIONAL)) {
            setNormState(conditional(compoundProperty, compoundPropertyState));
        } else if (evaluate().equals(NormState.ACTIVE)) {
            setNormState(active(compoundProperty, compoundPropertyState));
        } else {
            // no state change
        }
    }

    private NormState conditional(CompoundProperty compoundProperty, PropertyState compoundPropertyState) {
        NormState normState = evaluate();
        if (compoundProperty.equals(antecedent)) {
            if (compoundPropertyState.equals(PropertyState.SATISFIED)) {
                normState = NormState.ACTIVE;
                // TODO we may stop observing antecedent
            } else {
                normState = NormState.EXPIRED;
                // TODO remove observers
            }
        } else if (compoundProperty.equals(consequent)) {
            if (compoundPropertyState.equals(PropertyState.SATISFIED)) {
                normState = NormState.FULFILLED;
                // TODO remove observers
            }
        } else {
            throw new AssertionError();
        }
        return normState;
    }
    
    private NormState active(CompoundProperty compoundProperty, PropertyState compoundPropertyState) {
        NormState normState = evaluate();
        if (compoundProperty.equals(consequent)) {
            if (compoundPropertyState.equals(PropertyState.SATISFIED)) {
                normState = NormState.FULFILLED;
                // TODO remove observers
            } else {
                normState = NormState.VIOLATED;
                // TODO remove observers
            }
        } else {
            throw new AssertionError();
        }
        return normState;
    }
    
}
