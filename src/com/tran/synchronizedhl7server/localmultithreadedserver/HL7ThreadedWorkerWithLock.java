package com.tran.synchronizedhl7server.localmultithreadedserver;

public class HL7ThreadedWorkerWithLock extends Thread{
	private HL7ResourceExploiterWithLock rExp;
	
	public HL7ThreadedWorkerWithLock(HL7ResourceExploiterWithLock obj){
		rExp = obj;
	}
	
	public void run(){
		//String threadName = Thread.currentThread().getName();
		
		for(int i=0;i<10;i++){
			rExp.increase();
	    }		
	    
	}

}
