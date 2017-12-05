/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.edu.boun.cmpe.mas.akin.hammurabi.protocol.parser;

import tr.edu.boun.cmpe.mas.akin.hammurabi.event.EventTrace;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.Commitment;
import tr.edu.boun.cmpe.mas.akin.hammurabi.norm.Norm;
import tr.edu.boun.cmpe.mas.akin.hammurabi.property.parser.CompoundPropertyToken;

/**
 *
 * @author Akin Gunay
 */
public class NormToken {
    
    public static final String COMMITMENT = "Commitment";
    public static final String PROHIBITION = "Prohibition";
    public static final String AUTHORIZATION = "Authorization";
    
    private final String type;
    private final String debtor;
    private final String creditor;
    private final CompoundPropertyToken antecedent;
    private final CompoundPropertyToken consequent;

    public NormToken(String type, String debtor, String creditor, CompoundPropertyToken antecedent, CompoundPropertyToken consequent) {
        this.type = type;
        this.debtor = debtor;
        this.creditor = creditor;
        this.antecedent = antecedent;
        this.consequent = consequent;
    }
    
    public Norm getNormInstance(EventTrace eventTrace) {
        // TODO validate input
        if (type.equals(COMMITMENT)) {
            return Commitment.newCommitment(debtor, creditor, antecedent.getCompoundPropertyInstance(eventTrace), consequent.getCompoundPropertyInstance(eventTrace));
        }
        if (type.equals(PROHIBITION)) {
            return null;
        }
        if (type.equals(AUTHORIZATION)) {
            return null;
        }
        // TODO we should never reach to this point
        return null;
    }
}
