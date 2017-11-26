package com.bjsxt.base.queue;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {
	
	public static void main(String[] args) throws Exception {
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<>();
		Task t3 = new Task();
		t3.setId(3);
		t3.setName("id为3");
		Task t4 = new Task();
		t4.setId(4);
		t4.setName("id为4");
		Task t1 = new Task();
		t1.setId(1);
		t1.setName("id为1");
		
		q.add(t1);
		q.add(t4);
		q.add(t3);
		System.out.println(q);
		
		//调用一次take方法之后就会排序，不需要多次调用
		Task task = q.take();
		System.out.println(task);
		System.out.println(q);
		
		/*Task t1 = new Task();
		t1.setId(3);
		t1.setName("id为3");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("id为4");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("id为1");
		
		//return this.id > task.id ? 1 : 0;
		q.add(t1);	//3
		q.add(t2);	//4
		q.add(t3);  //1
		
		// 1 3 4
		System.out.println("容器：" + q);
		System.out.println(q.take().getId());
		System.out.println("容器：" + q);
//		System.out.println(q.take().getId());
//		System.out.println(q.take().getId());
*/	}

}
