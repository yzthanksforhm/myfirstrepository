package com.bjsxt.multi.master.worker;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Worker implements Runnable {

	private ConcurrentLinkedDeque<Task> workQueue;
	private ConcurrentHashMap<String, Object> resultMap;

	@Override
	public void run() {
		while(true){
			Task input = this.workQueue.poll();//从队列中取出第一个元素且移除
			if(input==null){
				break;
			}
			//真正的去做业务处理
			Object output = MyWorker1.handle(input);
			
			this.resultMap.put(Integer.toString(input.getId()), output);
		}
		
	}

	/*private Object  handle(Task input) {
		Object output = null;
		try {
			//处理任务的耗时
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}*/
	private static  Object handle(Task input) {
		
		return null;
	}

	public void setWorkQueue(ConcurrentLinkedDeque<Task> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
