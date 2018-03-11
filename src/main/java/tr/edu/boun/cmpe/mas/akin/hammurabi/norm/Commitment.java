package tr.edu.boun.cmpe.mas.akin.hammurabi.norm;

import tr.edu.boun.cmpe.mas.akin.hammurabi.property.CompoundProperty;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.PropertyState;

/**
 *
 * @author Akin Gunay
 */
public class Commitment extends Norm<Commitment> {

    private final String debtor;
    private final String creditor;
    private final CompoundProperty antecedent;
    private final CompoundProperty consequent;

    public static Commitment newCommitment(String debtor, String creditor, CompoundProperty antecedent, CompoundProperty consequent, NormState<Commitment> initialState) {
        // TODO validate input
        
        // TODO the following does: if no state is provided, make conditional!
        // is this what we intend? should we represent it better?
        if (initialState == null) {
            initialState = ConditionalCommitmentState.CONDITIONAL;
        }
        Commitment commitment = new Commitment(debtor, creditor, antecedent, consequent, initialState);
        commitment.antecedent.registerObserver(commitment);
        commitment.consequent.registerObserver(commitment);
        return commitment;
    }
        
    private Commitment(String debtor, String creditor, CompoundProperty antecedent, CompoundProperty consequent, NormState<Commitment> initialState) {
        super(initialState);
        this.debtor = debtor;
        this.creditor = creditor;
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    CompoundProperty getAntecedent() {
        return antecedent;
    }

    CompoundProperty getConsequent() {
        return consequent;
    }

    @Override
    public String toString() {
        return "C(" + debtor + ", " + creditor + ", " + antecedent + ", " + consequent + ")<" + evaluate() + ">";
    }
    
    @Override
    protected void evaluate(CompoundProperty compoundProperty, PropertyState compoundPropertyState) {
        setState(getState().progress(this, compoundProperty, compoundPropertyState));
    }

}
