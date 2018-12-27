package com.tran.synchronizedhl7server.localmultithreadedserver;

public class HL7ThreadedWorkerWithoutSync extends Thread{
	private HL7ResourceExploiter rExp;
	
	public HL7ThreadedWorkerWithoutSync(HL7ResourceExploiter obj){
		rExp = obj;
	}
	
	public void run(){
		String threadName = Thread.currentThread().getName();
		
		for(int i=0;i<10;i++){
			rExp.increase();
	    }		
	    
	}
	

}
