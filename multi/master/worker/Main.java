package com.bjsxt.multi.master.worker;

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("当前可用线程数为："+Runtime.getRuntime().availableProcessors());
		Master master = new Master(new MyWorker1(), Runtime.getRuntime().availableProcessors());
		Random r = new Random();
		for(int i=1;i<=100;i++){
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			t.setName("任务:"+i);
			master.submit(t);
		}
		master.execute();
		
		long start = System.currentTimeMillis();
		while(true){
			if(master.isComplete1()){
				long endTime = System.currentTimeMillis() - start;
				int ret = master.getResult();
				System.out.println("执行结果:"+ret);
				System.out.println("执行时间："+endTime);
				break;
			}
		}
	}

}
