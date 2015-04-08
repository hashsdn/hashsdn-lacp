package org.opendaylight.lacp.timer;

/*
 * import java.text.DateFormat;
 * import java.text.SimpleDateFormat;
 * import java.util.Date;
 * */

import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.opendaylight.lacp.queue.LacpTimerQueue;


public class PortPeriodicTimerRegister extends BasePortTimerRegister implements TimerTask  {
	
	public PortPeriodicTimerRegister(short pid, long sysid){
		super(pid,sysid);
	}
	
	@Override
	public void run(Timeout timeoutHandle) throws Exception {
		//identify the right timer queue using systemid as key and then enque the message 
		//System.out.println("Waitwhile - Timeout occured for port:" + this.getPortID() + " at " + getTime() + " and time in ms is " + System.currentTimeMillis());
                System.out.println("PortPeriodicTimerRegister - Timeout occured for port:" + this.getPortID());

		long swid = this.getSystemID();

		TimerExpiryMessage obj = new TimerExpiryMessage(swid, this.getPortID(),Utils.timerWheeltype.PERIODIC_TIMER);
		LacpTimerQueue objT = LacpTimerQueue.getLacpTimerQueueInstance();
		System.out.println("PortPeriodicTimerRegister Before enqueing the object");
		objT.enqueue(swid, obj);
		System.out.println("PortPeriodicTimerRegister After enqueing the object");
	}

}
