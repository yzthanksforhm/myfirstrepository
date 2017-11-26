package com.bjsxt.concurrent.utils.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class Temp extends Thread{
		public void run(){
			System.out.println("run...");
		}
	}
	
	public class ScheduledPool{
		public static void main(String[] args) {
			Temp command = new Temp();
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(command, 1, 3, TimeUnit.SECONDS);
			
		}
	}

