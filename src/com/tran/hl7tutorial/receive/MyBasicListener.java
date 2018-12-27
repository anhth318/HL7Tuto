package com.tran.hl7tutorial.receive;

import com.tran.hl7tutorial.receive.helpers.OurSimpleApplication;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.HL7Service;


public class MyBasicListener {

	// change this to whatever your port number is
	private static final int PORT_NUMBER = 54644;

	// In HAPI, almost all things revolve around a context object
	private static HapiContext context = new DefaultHapiContext();

	public static void main(String[] args) throws Exception {

		try {
			boolean useSecureConnection = false; // are you using TLS/SSL?

			// Set up a connection and a initiator purely for testing the server that we are
			// configuring
			//Connection ourConnection = context.newClient("localhost", PORT_NUMBER, useSecureConnection);
			//Initiator initiator = ourConnection.getInitiator();

			HL7Service ourHl7Server = context.newServer(PORT_NUMBER, useSecureConnection);

			// You can set up routing rules for your HL7 listener by extending the
			// AppRoutingData class like this
			ourHl7Server.registerApplication(new RegistrationEventRoutingData(), new OurSimpleApplication());

			// You can also set up the same routing logic like below
			// Try several applications all processing different message versions, types and
			// trigger events on your own
			//AppRoutingDataImpl ourRouter = new AppRoutingDataImpl("ADT", "A0.", "*","2.5");
			//ourHl7Server.registerApplication(ourRouter, new OurSimpleApplication());

			ourHl7Server.startAndWait();

			// close our test connection
			//ourConnection.close();

			// stop our HL7 listener
			//ourHl7Server.stopAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}



