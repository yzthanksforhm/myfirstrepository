package com.bjsxt.base.queue;

import java.util.concurrent.SynchronousQueue;

public class SynQueue {

	public static void main(String[] args) throws Exception {
		final SynchronousQueue<String> q = new SynchronousQueue<>();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println(q.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			
			public void run() {
				q.add("abc");
			}
		});
		//同步队列没有容量，必须先调用take方法进行阻塞之后，在调用add方法往里面加元素
		t1.start();
		Thread.sleep(1000);
		t2.start();
		
	}
}
