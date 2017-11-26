package com.bjsxt.multi.provider.consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	// 内存缓冲区
	BlockingQueue<Data> queue;

	//随即对象
	private Random r = new Random();
	
	public Consumer(BlockingQueue<Data> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while(true){
			try {
				//获取数据，如果队列为空，则进入阻塞状态
				Data data = this.queue.take();
				//进行数据处理：休眠
				Thread.sleep(r.nextInt(1000));
				System.out.println("当前消费线程："+Thread.currentThread().getName()+"，消费成功，消费数据为："+data.getId());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
