package com.bjsxt.multi.master.worker;

public class MyWorker1 extends Worker {

	public static Object handle(Task input) {
		Object output = null;
		try {
			// 处理任务的耗时
			Thread.sleep(500);
			output = input.getPrice();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output;
	}

}
