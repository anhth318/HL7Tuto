package com.tran.hl7tutorial.parsers;

import com.tran.hl7tutorial.parsers.custommodel.v25.message.ZDT_A01;
import com.tran.hl7tutorial.parsers.custommodel.v25.segment.*;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.parser.CustomModelClassFactory;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.parser.Parser;

public class HapiParserCustomMessageModelExample {

	private static HapiContext context = new DefaultHapiContext();
	
    public static void main(String[] args) throws HL7Exception {

        Parser parser = context.getPipeParser();

        ModelClassFactory ourCustomModelClassFactory = new CustomModelClassFactory("com.tran.hl7tutorial.parsers.custommodel");
        context.setModelClassFactory(ourCustomModelClassFactory);

        String messageText = "MSH|^~\\&|IRIS|SANTER|AMB_R|SANTER|200803051508||ZDT^A01|263206|P|2.5\r"
				+ "EVN||200803051509||||200803031508\r"
				+ "PID|||5520255^^^PK^PK~ZZZZZZ83M64Z148R^^^CF^CF~ZZZZZZ83M64Z148R^^^SSN^SSN^^20070103^99991231~^^^^TEAM||ZZZ^ZZZ||19830824|F||||||||||||||||||||||N\r"
                + "ZPV|Some Custom Notes|Additional custom description of the visit goes here\r"
				+ "ZNK|Nathalie|15 Janvier 1984|0033 45 67 36 54";
		
        //parse this message information into our ZDT custom message
        System.out.println("Attempting to parse message string into HL7 message object...");
        ZDT_A01 zdtA01Message = (ZDT_A01) parser.parse(messageText);
        System.out.println("ZDT^A01 message was parsed successfully" + "\n");
        
        //extract the ZPV Z-segment from this parsed message
        System.out.println("Retrieving the ZPV-segment from this message...");
        ZPV zpvSegment = zdtA01Message.getZPVSegment();
        System.out.println("Z-segment ZPV was retrieved successfully..." + "\n");
        //print the extracted custom fields from the Z-segment below
        System.out.println("Custom Notes retrieved from ZPV segment was -> " + zpvSegment.getCustomNotes()[0].encode()); // Print custom notes
        System.out.println("Custom Description retrieved from ZPV segment was -> " + zpvSegment.getCustomDescription()[0].encode()); // Print custom phone number

        //extract the ZNK Z-segment from this parsed message
        System.out.println("Retrieving the ZNK-segment from this message...");
        ZNK znkSegment = zdtA01Message.getZNKSegment();
        System.out.println("Z-segment ZNK was retrieved successfully..." + "\n");
        //print the extracted custom fields from the Z-segment below
        System.out.println("NOK name was -> " + znkSegment.getNOKName()[0].encode()); // Print Next of Kin name
        System.out.println("NOk DOB was -> " + znkSegment.getNOKDOB()[0].encode()); // Print NoK DoB
        System.out.println("NOk NOK tel -> " + znkSegment.getNOKTEL()[0].encode()); // Print NoK tel
        
    }

}

