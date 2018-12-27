package com.tran.synchronizedhl7server.localmultithreadedserver;

public class HL7ThreadedWorkerWithSync extends Thread{
	private HL7ResourceExploiter rExp;
	
	public HL7ThreadedWorkerWithSync(HL7ResourceExploiter obj){
		rExp = obj;
	}
	
	public void run(){
		//String threadName = Thread.currentThread().getName();
		synchronized(rExp){
			for(int i=0;i<10;i++){
				rExp.increase();
			}		
		}
	}

}
