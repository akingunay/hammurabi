package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundPropertyObserver;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public class Commitment extends Norm implements CompoundPropertyObserver {

    private final String debtor;
    private final String creditor;
    private final CompoundProperty antecedent;
    private final CompoundProperty consequent;

    public static Commitment newCommitment(String debtor, String creditor, CompoundProperty antecedent, CompoundProperty consequent) {
        // validate
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
    
    // TODO decide whether to write the evaluation in a compact or eplicit way
    @Override
    protected void evaluate(CompoundProperty compoundProperty, PropertyState compoundPropertyState) {
        if (evaluate().equals(NormState.CONDITIONAL)) {
            if (compoundProperty.equals(antecedent)) {
                if (compoundPropertyState.equals(PropertyState.SATISFIED)) {
                    setNormState(NormState.ACTIVE);
                    // TODO we may stop observing antecedent
                } else {
                    setNormState(NormState.EXPIRED);
                    // TODO remove observers
                }
            } else if (compoundProperty.equals(consequent)) {
                if (compoundPropertyState.equals(PropertyState.SATISFIED)) {
                    setNormState(NormState.FULFILLED);
                    // TODO remove observers
                }
            }
        } else if(evaluate().equals(NormState.ACTIVE)) {
            if (compoundProperty.equals(consequent)) {
                if (compoundPropertyState.equals(PropertyState.SATISFIED)) {
                    setNormState(NormState.FULFILLED);
                    // TODO remove observers
                } else {
                    setNormState(NormState.VIOLATED);
                    // TODO remove observers
                }
            }
        }
    }


}
