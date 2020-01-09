package com.tran.hl7tutorial.parsers.custommodel.v25.segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.AbstractSegment;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.model.v25.datatype.ST;
import ca.uhn.hl7v2.parser.ModelClassFactory;

public class ZNK  extends AbstractSegment {
	private static final long serialVersionUID = 2;
	
	public ZNK(Group parent, ModelClassFactory factory) {
        super(parent, factory);
        init(factory);
     }
	
	private void init(ModelClassFactory factory) {
        try {
        	//initialize the segment with the two custom fields we have need to include
        	addFieldToSegment("NOK name",true,0,100);
        	addFieldToSegment("NOK DOB",true,0,100);
        	addFieldToSegment("NOK tel",true,0,100);
        	
        } catch (HL7Exception e) {
            log.error("There was an error creating the custom segment ZNK.", e);
        }
     }
	
	private void addFieldToSegment(String fieldName, boolean isFieldMandatory, int numberOfFieldRepetitions, int fieldLength) throws HL7Exception {
		add(ST.class, isFieldMandatory, numberOfFieldRepetitions, fieldLength, new Object[]{ getMessage() }, fieldName);
	}
	
	public ST[] getNOKName() throws HL7Exception {
    	return getTypedStringTextFieldFromFieldIndexPosition(1);
    }
    
    public ST[] getNOKDOB() throws HL7Exception {
    	return getTypedStringTextFieldFromFieldIndexPosition(2);
    }
    
    public ST[] getNOKTEL() throws HL7Exception {
    	return getTypedStringTextFieldFromFieldIndexPosition(3);
    }

	private ST[] getTypedStringTextFieldFromFieldIndexPosition(int index) {
		return getTypedField(index,new ST[0]);
	}
	
    @Override
    protected Type createNewTypeWithoutReflection(int field) {
        return null;
    }

}
