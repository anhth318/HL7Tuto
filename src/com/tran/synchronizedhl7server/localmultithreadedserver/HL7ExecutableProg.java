package com.tran.synchronizedhl7server.localmultithreadedserver;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.parser.Parser;

public class HL7ExecutableProg {
	
	private static HapiContext context = new DefaultHapiContext();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HL7ResourceExploiterWithLock rsc = new HL7ResourceExploiterWithLock();
		
		/*HL7ThreadedWorkerWithoutSync worker1 = new HL7ThreadedWorkerWithoutSync(rsc);
		HL7ThreadedWorkerWithoutSync worker2 = new HL7ThreadedWorkerWithoutSync(rsc);
		HL7ThreadedWorkerWithoutSync worker3 = new HL7ThreadedWorkerWithoutSync(rsc);*/
		
		/*HL7ThreadedWorkerWithSync worker1 = new HL7ThreadedWorkerWithSync(rsc);
		HL7ThreadedWorkerWithSync worker2 = new HL7ThreadedWorkerWithSync(rsc);
		HL7ThreadedWorkerWithSync worker3 = new HL7ThreadedWorkerWithSync(rsc);*/
		
		HL7ThreadedWorkerWithLock worker1 = new HL7ThreadedWorkerWithLock(rsc);
		HL7ThreadedWorkerWithLock worker2 = new HL7ThreadedWorkerWithLock(rsc);
		HL7ThreadedWorkerWithLock worker3 = new HL7ThreadedWorkerWithLock(rsc);
		
		worker1.start();
		worker2.start();
		worker3.start();
		
		try
        { 
			worker1.join(); 
			worker2.join();
			worker3.join();
        } 
        catch(Exception e) 
        { 
            System.out.println("Interrupted"); 
        }
		
		Parser pipeParser = context.getPipeParser();
		try {
			System.out.println(pipeParser.encode(rsc.getMsg()));
		} catch (HL7Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
