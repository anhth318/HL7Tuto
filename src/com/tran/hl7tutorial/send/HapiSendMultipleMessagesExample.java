package com.tran.hl7tutorial.send;

import java.io.FileReader;
import java.io.IOException;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.util.Hl7InputStreamMessageIterator;

public class HapiSendMultipleMessagesExample {

	// In HAPI, almost all things revolve around a context object
	private static HapiContext context = new DefaultHapiContext();
	
	// change this to whatever your port number is
	private static final int PORT_NUMBER = 37073;

	public static void main(String[] args) throws Exception {

		try {

			FileReader reader = new FileReader("/Users/haianhtran/eclipse-workspace/HL7Tuto/TestHL7MessageFiles/FileWithLotsOfHl7Messages.txt");
			
			//create an iterator to read through the HL7 messages in the file
			Hl7InputStreamMessageIterator messageIterator = new Hl7InputStreamMessageIterator(reader);
			
			Connection connectionWithServer = null;
			
			while (messageIterator.hasNext()) {
				
				if (connectionWithServer == null) {
					boolean useSecureTlsConnection = false;
					connectionWithServer = context.newClient("192.168.2.122", PORT_NUMBER, useSecureTlsConnection);
				}
				
				try {
					Message nextMessage = messageIterator.next();
					Message messageResponse = connectionWithServer.getInitiator().sendAndReceive(nextMessage);
					System.out.println("Response received from server was " + messageResponse.encode());
				} catch (IOException e) {
					e.printStackTrace(); //in real-life, you need to handle these exceptions 
					connectionWithServer.close();
					connectionWithServer = null;
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
